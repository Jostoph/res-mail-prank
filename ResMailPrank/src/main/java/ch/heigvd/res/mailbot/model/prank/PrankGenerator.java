package ch.heigvd.res.mailbot.model.prank;

import ch.heigvd.res.mailbot.config.ConfigManager;
import ch.heigvd.res.mailbot.model.mail.Group;
import ch.heigvd.res.mailbot.model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class PrankGenerator {

    private static final Logger LOGGER = Logger.getLogger(PrankGenerator.class.getName());

    private ConfigManager configManager;

    public PrankGenerator(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public List<Prank> generatePranks() {
        List<Group> groups;
        List<Prank> pranks;

        int numberOfPeople = configManager.getPeople().size();
        int numberOfGroups = configManager.getNumberOfGroups();
        List<String> messages = configManager.getMessages();
        Collections.shuffle(messages);

        if((numberOfPeople / numberOfGroups) < 3) {
            numberOfGroups = numberOfPeople / 3;
            LOGGER.warning("The size of the groups to generate the pranks is to small (need at least 3 people), it has been set to : " + numberOfGroups);
        }

        groups = generateGroups(configManager.getPeople(), numberOfGroups);

        pranks = new ArrayList<>();

        int messageIndex = 0;
        for(Group group : groups) {
            pranks.add(new Prank(group, messages.get(messageIndex)));
            messageIndex = (messageIndex + 1) % messages.size();
        }

        return pranks;
    }

    private List<Group> generateGroups(List<Person> people, int numberOfGroups) {
        List<Group> groups = new ArrayList<>();
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
        int groupeIndex = 0;
        while (!randomSortedPeople.isEmpty()) {
            groups.get(groupeIndex).addVictim(randomSortedPeople.getFirst());
            randomSortedPeople.removeFirst();
            groupeIndex = (groupeIndex + 1) % numberOfGroups;
        }
        return groups;
    }
}
