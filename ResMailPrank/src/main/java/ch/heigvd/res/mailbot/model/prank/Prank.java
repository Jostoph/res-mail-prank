package ch.heigvd.res.mailbot.model.prank;

import ch.heigvd.res.mailbot.model.mail.Group;

public class Prank {

    private Group group;
    private String message;

    public Prank(Group group, String message) {
        this.group = group;
        this.message = message;
    }

    public void execute() {
        // TODO execute prank (send mails)
    }
}
