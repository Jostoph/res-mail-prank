package ch.heigvd.res.mailbot.smtp;

import ch.heigvd.res.mailbot.model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient  implements ISmtpClient {

    private static final Logger LOGGER = Logger.getLogger(SmtpClient.class.getName());

    private String serverAddress;
    private int port;

    private PrintWriter writer;
    private BufferedReader reader;


    public SmtpClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    @Override
    public void sendMail(Mail mail) throws IOException {

        LOGGER.info("Connecting to SMTP server...");

        Socket socket = new Socket(serverAddress, port);

        LOGGER.info("Connected to " + serverAddress);

        String line;
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        line = reader.readLine();
        LOGGER.info(line);

        writer.printf("EHLO localhost\r\n");

        line = reader.readLine();
        LOGGER.info(line);

        if(!line.startsWith("250")) {
            throw new IOException("Error : " + line);
        } else {
            while (line.startsWith("250")) {
                line = reader.readLine();
                LOGGER.info(line);
            }
        }

        writer.printf("MAIL FROM: " + mail.getSenderAddress() + "\r\n");

        line = reader.readLine();
        LOGGER.info(line);

        for(String rcpt : mail.getRecipientsAddresses()) {
            writer.printf("RCPT TO: " + rcpt + "\r\n");
            line = reader.readLine();
            LOGGER.info(line);
        }

        writer.printf("DATA\r\n");

        line = reader.readLine();
        LOGGER.info(line);

        writer.write("To: " + mail.getSenderAddress());
        for(int i = 0; i < mail.getRecipientsAddresses().size(); ++i) {
            writer.write(", " + mail.getRecipientsAddresses().get(i));
        }
        writer.write("\r\n");
        writer.flush();

        LOGGER.info(mail.getMessage());
        writer.printf(mail.getMessage() + "\r\n.\r\n");

        line = reader.readLine();
        LOGGER.info(line);

        writer.printf("QUIT\r\n");

        writer.close();
        reader.close();

        socket.close();
    }
}
