package ch.heigvd.res.mailbot.model.prank;

import ch.heigvd.res.mailbot.model.mail.Group;
import ch.heigvd.res.mailbot.model.mail.Mail;

/**
 * Class Prank
 *
 * Class representing a prank,
 * it is composed of a group to prank and a message to send
 *
 * @author Jostoph
 * @version 1.0
 */
public class Prank {

    // to group of Person (people) to prank
    private Group group;
    // the message to send to the victims of the group
    private String message;

    public Prank(Group group, String message) {
        this.group = group;
        this.message = message;
    }

    /**
     * @return a prank Mail from the group's sender to the victims with the message to send
     */
    public Mail getMail() {
        return new Mail(group.getSender(), group.getVictims(), message);
    }
}
