package ch.heigvd.res.mailbot.model.prank;

import ch.heigvd.res.mailbot.model.mail.Person;

import java.util.ArrayList;
import java.util.LinkedList;

public class PrankGenerator {

    private static PrankGenerator generator = null;

    private ArrayList<Person> people;

    public PrankGenerator getInstance() {
        if(generator != null) {
            return generator;
        }
        generator = new PrankGenerator();
        return generator;
    }

    public void init(ArrayList<Person> people) {
        this.people = people;
    }

    private PrankGenerator() {}

    public LinkedList<Prank> generatePranks() {
        // TODO generate a list of Group -> then list of Prank
        return null;
    }
}
