package ca.retrylife.mc.contributorcapes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.retrylife.mc.contributorcapes.util.CapeLoader;
import ca.retrylife.mc.contributorcapes.util.ConfigLoader;
import ca.retrylife.mc.contributorcapes.util.ConfigLoader.CapeConfiguration;

import java.awt.image.BufferedImage;

public class Mod implements ModInitializer {

    // Logger
    private static final Logger logger = LogManager.getLogger(Mod.NAME);

    // Mod config
    public static final String MODID = "contributorcapes";
    public static final String NAME = "Contributor Capes";

    @Override
    public void onInitialize() {
        logger.info("Starting Contributor Capes mod");

        // Load all meta contributor capes
        MetaContributors.registerAll();

        // Try to load contributor capes from config file
        CapeConfiguration config = ConfigLoader.loadConfiguration();

        // Only load config capes if the config exists
        configFileLoading: {
            if (config != null) {

                // Load the cape from its url
                URL url = null;
                try {
                    url = new URL(config.url);
                } catch (MalformedURLException e) {
                    logger.log(Level.WARN, "Config file using malformed URL");
                    break configFileLoading;
                }

                // Get the cape asset as an image
                BufferedImage asset;
                try {
                    asset = CapeLoader.resolveCapeFromURL(url);
                } catch (IOException e) {
                    logger.log(Level.WARN, "Failed to load cape image from config file");
                    break configFileLoading;
                }

                // Add the cape
                int capeUID = PlayerCapeRegistry.getInstance().addCape(asset);

                // Iterate every user, and register them with a cape
                for (String user : config.usernames) {
                    PlayerCapeRegistry.getInstance().registerPlayerCape(user, capeUID);
                }

            } else {
                logger.log(Level.INFO, "No configuration defined");
            }
        }

        // Register all capes
        PlayerCapeRegistry.getInstance().loadResources();

        logger.info("Contributor Capes ready.");
    }

}