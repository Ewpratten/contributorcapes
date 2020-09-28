package ca.retrylife.mc.contributorcapes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.retrylife.mc.contributorcapes.util.CapeLoader;

/**
 * This class is used to award capes to ContributorCapes devs globally
 */
public class MetaContributors {

    // Logger
    private static final Logger logger = LogManager.getLogger(String.format("%s_MetaContributors", Mod.NAME));

    /**
     * Register capes for all meta contributors
     */
    public static void registerAll() {

        // Load the contributor cape
        int capeUID;
        try {
            capeUID = PlayerCapeRegistry.getInstance().addCape(CapeLoader.resolveCapeFromURL(
                    new URL("https://github.com/Ewpratten/contributorcapes/raw/master/assets/contributorcape.png")));
        } catch (MalformedURLException e) {
            logger.log(Level.WARN, "Invalid URL");
            return;
        } catch (IOException e) {
            logger.log(Level.WARN, "Could not resolve cape from URL. Are you offline?");
            return;
        }

        // Define all contributors here
        PlayerCapeRegistry.getInstance().registerPlayerCape("Xnor50", capeUID);
    }

}