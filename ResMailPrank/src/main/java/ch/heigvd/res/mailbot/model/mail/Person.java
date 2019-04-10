package ch.heigvd.res.mailbot.model.mail;

public class Person {

    private String mailAddress;

    public Person(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }
}
