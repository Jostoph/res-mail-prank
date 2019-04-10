package ch.heigvd.res.mailbot.config;

import ch.heigvd.res.mailbot.model.mail.Person;

import java.util.List;

public interface IConfigManager {

    List<Person> getPeople();

    int getNumberOfGroups();

    List<String> getMessages();

    String getServerAddress();

    int getServerPort();
}
