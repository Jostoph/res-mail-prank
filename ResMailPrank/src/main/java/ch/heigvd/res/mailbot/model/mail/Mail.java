package ch.heigvd.res.mailbot.model.mail;

import java.util.ArrayList;
import java.util.List;

public class Mail {

    private Person sender;
    private List<Person> recipients;
    private String message;

    public Mail(Person sender, List<Person> recipients, String message) {
        this.sender = sender;
        this.recipients = recipients;
        this.message = message;
    }

    public String getSenderAddress() {
        return sender.getMailAddress();
    }

    public List<String> getRecipientsAddresses() {
        List<String> addresses = new ArrayList<>();
        recipients.forEach(p -> addresses.add(p.getMailAddress()));
        return addresses;
    }

    public String getMessage() {
        return message;
    }
}
