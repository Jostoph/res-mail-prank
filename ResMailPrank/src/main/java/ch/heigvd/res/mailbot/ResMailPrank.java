package ch.heigvd.res.mailbot;

import ch.heigvd.res.mailbot.config.ConfigManager;
import ch.heigvd.res.mailbot.model.prank.Prank;
import ch.heigvd.res.mailbot.model.prank.PrankGenerator;
import ch.heigvd.res.mailbot.smtp.SmtpClient;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class ResMailPrank
 *
 * Main class of the application.
 * Forms groups of people to play a prank on by choosing one
 * of the group member to be the sender and the other to be the victims
 * that will receive a message from a given message list
 *
 * @author Jostoph
 * @version 1.0
 */
public class ResMailPrank {

    private static Logger LOGGER = Logger.getLogger(ResMailPrank.class.getName());

    public static void main(String[] args) {

        try {
            // get the configuration for the prank
            ConfigManager  configManager = new ConfigManager();
            // creates a new SMTP client
            SmtpClient client = new SmtpClient(configManager.getServerAddress(), configManager.getServerPort());
            // creates a new PrankGenerator with the configManager
            PrankGenerator generator = new PrankGenerator(configManager);

            // generate the list of pranks
            List<Prank> pranks = generator.generatePranks();

            // executing the pranks by sending the mails using the SMTP client
            for (Prank prank : pranks) {
                client.sendMail(prank.getMail());
            }

        } catch (IllegalArgumentException | IOException ex) {
            LOGGER.info("Error : " + ex);
        }
    }
}
