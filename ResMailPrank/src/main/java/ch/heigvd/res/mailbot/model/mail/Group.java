package ch.heigvd.res.mailbot.model.mail;

import java.util.LinkedList;

public class Group {

    private Person sender;
    private LinkedList<Person> victims;

    public Group(Person sender, LinkedList<Person> victims) throws RuntimeException {

        if(sender == null || victims.size() < 2) {
            throw new RuntimeException("A group must have a sender and at least 2 victims");
        }
        this.sender = sender;
        this.victims = victims;
    }

    public Person getSender() {
        return sender;
    }

    public LinkedList<Person> getVictims() {
        return victims;
    }
}
