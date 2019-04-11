package ch.heigvd.res.mailbot.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Group
 *
 * Class representing a group for a mail prank,
 * it is composed of a sender and a list of victims
 *
 * @author Jostoph
 * @version 1.0
 */
public class Group {

    // the sender
    private Person sender;
    // the victims
    private List<Person> victims;

    public Group() {
        this.victims = new ArrayList<>();
    }

    public Person getSender() {
        return sender;
    }

    public List<Person> getVictims() {
        return victims;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void addVictim(Person victim) {
        victims.add(victim);
    }
}
