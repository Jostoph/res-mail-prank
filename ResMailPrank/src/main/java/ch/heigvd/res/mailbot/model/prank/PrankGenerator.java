package ch.heigvd.res.mailbot.model.prank;

import ch.heigvd.res.mailbot.config.ConfigManager;
import ch.heigvd.res.mailbot.model.mail.Group;
import ch.heigvd.res.mailbot.model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class PrankGenerator
 *
 * Class representing a generator of prank lists
 *
 * @author Jostoph
 * @version 1.0
 *
 * Inspired by the Labo SMTP, part 4 video from professor Olivier Liechti
 * in the context of the RES course at the heig-vd
 *
 * https://www.youtube.com/watch?v=OrSdRCt_6YQ
 */
public class PrankGenerator {

    private static final Logger LOGGER = Logger.getLogger(PrankGenerator.class.getName());

    private ConfigManager configManager;

    /**
     * Constructor
     *
     * @param configManager the configuration manager to generate the pranks
     */
    public PrankGenerator(ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * generate Pranks with generated groups of Person (people) from a randomly sorted list
     * and a message selected from a randomly sorted list of messages, respecting a given number of
     * groups if possible
     *
     * @return a list of Pranks ready to be performed
     */
    public List<Prank> generatePranks() {
        // the list of groups
        List<Group> groups;
        // the list of pranks to generate
        List<Prank> pranks;

        int numberOfPeople = configManager.getPeople().size();
        int numberOfGroups = configManager.getNumberOfGroups();
        List<String> messages = configManager.getMessages();
        // shuffling the messages
        Collections.shuffle(messages);

        // if there are less than 3 people per group with the given number of groups
        // the number of groups is set so that there are at least 3 people per group
        if((numberOfPeople / numberOfGroups) < 3) {
            numberOfGroups = numberOfPeople / 3;
            LOGGER.warning("The size of the groups to generate the pranks is to small (need at least 3 people), it has been set to : " + numberOfGroups);
        }

        // generation of the list of groups
        groups = generateGroups(configManager.getPeople(), numberOfGroups);

        pranks = new ArrayList<>();

        // setting a message for each group
        int messageIndex = 0;
        for(Group group : groups) {
            pranks.add(new Prank(group, messages.get(messageIndex)));
            messageIndex = (messageIndex + 1) % messages.size();
        }

        return pranks;
    }

    /**
     * private helper method to generate the groups for the pranks
     *
     * @param people the list of Person (people)
     * @param numberOfGroups the number of groups to generate
     * @return the list of created groups
     */
    private List<Group> generateGroups(List<Person> people, int numberOfGroups) {
        List<Group> groups = new ArrayList<>();
        // list of randomly sorted Person (people)
        LinkedList<Person> randomSortedPeople = new LinkedList<>(people);
        Collections.shuffle(randomSortedPeople);

        // create groups and add senders
        for(int i = 0; i < numberOfGroups; ++i) {
            Group g = new Group();
            g.setSender(randomSortedPeople.getFirst());
            randomSortedPeople.removeFirst();
            groups.add(g);
        }

        // spread victims in the groups
        int groupIndex = 0;
        while (!randomSortedPeople.isEmpty()) {
            groups.get(groupIndex).addVictim(randomSortedPeople.getFirst());
            randomSortedPeople.removeFirst();
            groupIndex = (groupIndex + 1) % numberOfGroups;
        }
        return groups;
    }
}
