package ch.heigvd.res.mailbot.smtp;

import ch.heigvd.res.mailbot.model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Class SmtpClient
 *
 * Class representing a concrete implementation of an SMTP client using the
 * ISmtpClient interface
 *
 * @author Jostoph
 * @version 1.0
 *
 * Inspired by the Labo SMTP, part 4 video from professor Olivier Liechti
 * in the context of the RES course at the heig-vd
 *
 * https://www.youtube.com/watch?v=OrSdRCt_6YQ
 */
public class SmtpClient  implements ISmtpClient {

    private static final Logger LOGGER = Logger.getLogger(SmtpClient.class.getName());

    // the SMTP server address
    private String serverAddress;
    // the SMTP server port
    private int port;

    /**
     * Constructor
     *
     * @param serverAddress the smtp server address
     * @param port the smtp port
     */
    public SmtpClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    @Override
    public void sendMail(Mail mail) throws IOException {

        LOGGER.info("Connecting to SMTP server...");

        // creating client socket
        Socket socket = new Socket(serverAddress, port);

        LOGGER.info("Connected to " + serverAddress);

        String line;
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        line = reader.readLine();
        LOGGER.info(line);

        // identification to the SMTP server to initiate the conversation
        writer.printf("EHLO localhost\r\n");

        line = reader.readLine();
        LOGGER.info(line);

        // receiving the server response
        if(!line.startsWith("250")) {
            throw new IOException("Error : " + line);
        } else {
            while (line.startsWith("250-")) {
                line = reader.readLine();
                LOGGER.info(line);
            }
        }
        // sending the email address of the sender
        writer.printf("MAIL FROM:<" + mail.getSenderAddress() + ">\r\n");

        line = reader.readLine();
        LOGGER.info(line);

        // sending the recipients that will get the message
        for(String rcpt : mail.getRecipientsAddresses()) {
            writer.printf("RCPT TO:<" + rcpt + ">\r\n");
            line = reader.readLine();
            LOGGER.info(line);
        }

        // starts the message body transfer
        writer.printf("DATA\r\n");

        line = reader.readLine();
        LOGGER.info(line);

        // setting content type
        writer.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");
        writer.write("From: " + mail.getSenderAddress() + "\r\n");

        // sending data
        writer.write("To: " + mail.getRecipientsAddresses().get(0));
        for(int i = 1; i < mail.getRecipientsAddresses().size(); ++i) {
            writer.write(", " + mail.getRecipientsAddresses().get(i));
        }
        writer.write("\r\n");
        writer.flush();

        LOGGER.info(mail.getMessage());
        writer.printf(mail.getMessage() + "\r\n.\r\n");

        line = reader.readLine();
        LOGGER.info(line);

        // stop the conversation and quit
        writer.printf("QUIT\r\n");

        writer.close();
        reader.close();

        socket.close();
    }
}
