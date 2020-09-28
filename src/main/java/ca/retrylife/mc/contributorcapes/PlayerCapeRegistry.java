package ca.retrylife.mc.contributorcapes;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.util.Identifier;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * PlayerCapeRegistry is a registry of custom capes, and the players they belong
 * to
 */
public class PlayerCapeRegistry {

    // Logger
    private static final Logger logger = LogManager.getLogger(String.format("%s_PlayerCapeRegistry", Mod.NAME));

    // Singleton instance
    private static PlayerCapeRegistry instance;

    // Internal resource pack for cape assets
    private RuntimeResourcePack resourcePack = RuntimeResourcePack.create("contributorcapes_caperegistry:resource");

    // Counter for registered capes
    private int lastCapeID = 0;

    // Registry of capes
    private HashMap<String, Integer> registry = new HashMap<>();

    private PlayerCapeRegistry() {

    }

    /**
     * Get the instance of PlayerCapeRegistry
     * 
     * @return PlayerCapeRegistry
     */
    public static PlayerCapeRegistry getInstance() {
        if (instance == null) {
            instance = new PlayerCapeRegistry();
        }
        return instance;
    }

    /**
     * Add a cape to the internal resource pack
     * 
     * @param asset Cape image
     * @return Cape UID
     */
    public int addCape(BufferedImage asset) {

        // Incr the cape counter
        lastCapeID++;

        // Build a cape identifier
        Identifier capeIdentifier = constructCapeIdentifier(lastCapeID);

        // Register this cape as a texture
        resourcePack.addTexture(capeIdentifier, asset);

        // Log
        logger.log(Level.INFO, String.format("Added cape with ID: %d", lastCapeID));

        // Return the cape id
        return lastCapeID;
    }

    /**
     * Link a cape to a player
     * 
     * @param username Player username
     * @param capeUID  UID of the cape
     */
    public void registerPlayerCape(String username, int capeUID) {
        // Only do logic if the cape id exists
        if (capeUID <= lastCapeID) {

            // Check if the player is already in the registry
            if (registry.containsKey(username.toLowerCase())) {
                logger.log(Level.WARN, String.format("Player %s has been registered multiple times", username));
            } else {
                registry.put(username.toLowerCase(), capeUID);
                logger.log(Level.INFO, String.format("Linked player %s to cape %d", username, capeUID));
            }
        } else {
            logger.log(Level.WARN, String.format(
                    "Attempted to register a cape for %s with UID %d, but that ID does not exist!", username, capeUID));
        }
    }

    /**
     * Get the minecraft resource identifier for a username
     * @param username Player username
     * @return Identifier
     */
    public Identifier getIdentifierForPlayer(String username) {
        return constructCapeIdentifier(registry.get(username.toLowerCase()));
    }

    /**
     * Check if a player shows up in the cape registry
     * 
     * @param username Player username
     * @return Has cape?
     */
    public boolean doesPlayerHaveCape(String username) {
        return registry.get(username.toLowerCase()) != null;
    }

    /**
     * Call this after all other setup calls
     */
    public void loadResources() {
        logger.log(Level.INFO, "Adding RRPCallback event");
        RRPCallback.EVENT.register(a -> a.add(1, resourcePack));
    }

    /**
     * Constructs a Minecraft identifier out of a cape UID
     * 
     * @param capeUID Cape UID
     * @return Minecraft identifier
     */
    private Identifier constructCapeIdentifier(int capeUID) {
        return new Identifier(Mod.MODID, String.format("PlayerCapeRegistry_cape_%d", capeUID));
    }

}