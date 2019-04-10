package ch.heigvd.res.mailbot.model.mail;

import java.util.ArrayList;

public class Mail {

    private Person sender;
    private ArrayList<Person> recipients;
    private String message;

    public Mail(Person sender, ArrayList<Person> recipients, String message) {
        this.sender = sender;
        this.recipients = recipients;
        this.message = message;
    }

    public String getSenderAddress() {
        return sender.getMailAddress();
    }

    public ArrayList<String> getRecipientsAddresses() {
        // TODO
        return null;
    }

    public String getMessage() {
        return message;
    }
}
