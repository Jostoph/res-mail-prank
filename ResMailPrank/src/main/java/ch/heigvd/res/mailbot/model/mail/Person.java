package ch.heigvd.res.mailbot.model.mail;

/**
 * Class Person
 *
 * Class representing a Person as an email address
 *
 * @author Jostoph
 * @version 1.0
 */
public class Person {

    private String mailAddress;

    public Person(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }
}
