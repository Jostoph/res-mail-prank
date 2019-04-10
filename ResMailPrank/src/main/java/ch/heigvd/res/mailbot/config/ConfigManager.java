package ch.heigvd.res.mailbot.config;

import ch.heigvd.res.mailbot.model.mail.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigManager implements IConfigManager {

    // properties
    private String serverAddress;
    private int port;
    private int numberOfGroups;

    // people and messages
    private List<Person> people;
    private List<String> messages;

    public ConfigManager() throws IOException, IllegalArgumentException {
        people = loadPeople("./config/people.utf8");
        messages = loadMessages("./config/messages.utf8");
        loadProperties("./config/config.properties");

        if(people.size() < 3) {
            throw new IllegalArgumentException("You need at least 3 people to generate a prank !");
        }

        if(numberOfGroups < 0) {
            throw new IllegalArgumentException("You need to set a positive numberOfGroups !");
        }
    }

    @Override
    public List<Person> getPeople() {
        return people;
    }

    @Override
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public String getServerAddress() {
        return serverAddress;
    }

    @Override
    public int getServerPort() {
        return port;
    }

    private List<Person> loadPeople(String path) throws IOException {

        List<Person> people = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

        String line = reader.readLine();
        while (line != null) {
            people.add(new Person(line));
            line = reader.readLine();
        }

        reader.close();
        return people;
    }

    private List<String> loadMessages(String path) throws IOException {

        List<String> messages = new ArrayList<>();
        String messageSeparator = "---";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

        String line = reader.readLine();
        while (line != null) {
            StringBuilder sb = new StringBuilder();
            while ((line != null) && (!line.equals(messageSeparator))) {
                sb.append(line);
                sb.append("\r\n");

                line = reader.readLine();
            }
            messages.add(sb.toString());
            line = reader.readLine();
        }


        reader.close();
        return messages;
    }

    private void loadProperties(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        Properties properties = new Properties();
        properties.load(inputStream);
        this.serverAddress = properties.getProperty("serverAddress");
        this.port = Integer.parseInt(properties.getProperty("serverPort"));
        this.numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
    }
}
