package ch.heigvd.res.mailbot.model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private Person sender;
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
