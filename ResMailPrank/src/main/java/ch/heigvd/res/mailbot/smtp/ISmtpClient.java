package ch.heigvd.res.mailbot.smtp;

import ch.heigvd.res.mailbot.model.mail.Mail;

import java.io.IOException;

/**
 * Interface SmtpClient
 *
 * Interface that provides a method to send a Mail
 */
public interface ISmtpClient {
    /**
     * sends a given Mail
     *
     * @param mail the mail to send
     * @throws IOException if there is an error while sending the Mail
     */
    void sendMail(Mail mail) throws IOException;
}
