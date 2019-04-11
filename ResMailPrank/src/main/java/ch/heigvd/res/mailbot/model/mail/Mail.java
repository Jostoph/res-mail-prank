package ch.heigvd.res.mailbot.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Mail
 *
 * Class representing an email with a sender, a list of recipients
 * and a message
 *
 * @author Jostoph
 * @version 1.0
 */
public class Mail {

    // the sender
    private Person sender;
    // the list of rcpt
    private List<Person> recipients;
    // the message to send
    private String message;

    /**
     * Constructor
     *
     * @param sender the sender
     * @param recipients the rcpt list
     * @param message the message to send
     */
    public Mail(Person sender, List<Person> recipients, String message) {
        this.sender = sender;
        this.recipients = recipients;
        this.message = message;
    }

    public String getSenderAddress() {
        return sender.getMailAddress();
    }

    /**
     * @return the email address (String) of the rcpt Person (people)
     */
    public List<String> getRecipientsAddresses() {
        List<String> addresses = new ArrayList<>();
        recipients.forEach(p -> addresses.add(p.getMailAddress()));
        return addresses;
    }

    public String getMessage() {
        return message;
    }
}
