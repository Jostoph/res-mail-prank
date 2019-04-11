package ch.heigvd.res.mailbot.config;

import ch.heigvd.res.mailbot.model.mail.Person;

import java.util.List;

/**
 * Interface IConfigManager
 *
 * Interface that provides methods to retrieve the data from the
 * configuration files
 *
 * @author Jostoph
 * @version 1.0
 */
public interface IConfigManager {

    /**
     * @return the list of Person (people) from the configuration files
     */
    List<Person> getPeople();

    /**
     * @return the number of groups set in the properties
     */
    int getNumberOfGroups();

    /**
     * @return the list of messages from the configuration files
     */
    List<String> getMessages();

    /**
     * @return the SMTP server address from the properties
     */
    String getServerAddress();

    /**
     * @return the SMTP server port from the properties
     */
    int getServerPort();
}
