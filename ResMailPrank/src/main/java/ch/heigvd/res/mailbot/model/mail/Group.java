package ch.heigvd.res.mailbot.model.mail;

import java.util.ArrayList;

public class Group {

    private Person sender;
    private ArrayList<Person> victims;

    public Group(Person sender, ArrayList<Person> victims) throws RuntimeException {

        if(sender == null || victims.size() < 2) {
            throw new RuntimeException("A group must have a sender and at least 2 victims");
        }
        this.sender = sender;
        this.victims = victims;
    }

    public Person getSender() {
        return sender;
    }

    public ArrayList<Person> getVictims() {
        return victims;
    }
}
