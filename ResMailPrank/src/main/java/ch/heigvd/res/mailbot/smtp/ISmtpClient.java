package ch.heigvd.res.mailbot.smtp;

import ch.heigvd.res.mailbot.model.mail.Mail;

import java.io.IOException;

public interface ISmtpClient {
    void sendMail(Mail mail) throws IOException;
}
