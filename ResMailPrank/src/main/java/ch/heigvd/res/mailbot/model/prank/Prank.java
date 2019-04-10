package ch.heigvd.res.mailbot.model.prank;

import ch.heigvd.res.mailbot.model.mail.Group;
import ch.heigvd.res.mailbot.model.mail.Mail;

public class Prank {

    private Group group;
    private String message;

    public Prank(Group group, String message) {
        this.group = group;
        this.message = message;
    }

    public Mail getMail() {
        // TODO getMail to send
        return null;
    }
}
