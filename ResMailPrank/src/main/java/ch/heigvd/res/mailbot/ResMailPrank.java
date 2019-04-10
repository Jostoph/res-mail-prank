package ch.heigvd.res.mailbot;

import ch.heigvd.res.mailbot.config.ConfigManager;
import ch.heigvd.res.mailbot.model.prank.Prank;
import ch.heigvd.res.mailbot.model.prank.PrankGenerator;
import ch.heigvd.res.mailbot.smtp.SmtpClient;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ResMailPrank {

    private static Logger LOGGER = Logger.getLogger(ResMailPrank.class.getName());

    public static void main(String[] args) {

        try {
            ConfigManager  configManager = new ConfigManager();
            SmtpClient client = new SmtpClient(configManager.getServerAddress(), configManager.getServerPort());
            PrankGenerator generator = new PrankGenerator(configManager);

            List<Prank> pranks = generator.generatePranks();

            for (Prank prank : pranks) {
                client.sendMail(prank.getMail());
            }

        } catch (IllegalArgumentException | IOException ex) {
            LOGGER.info("Error : " + ex);
        }
    }
}
