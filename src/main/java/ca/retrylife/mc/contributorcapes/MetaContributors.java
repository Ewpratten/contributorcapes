package ca.retrylife.mc.contributorcapes;

import java.net.URL;

import ca.retrylife.mc.contributorcapes.util.CapeLoader;

/**
 * This class is used to award capes to ContributorCapes devs globally
 */
public class MetaContributors {

    // URL for ContributorCapes dev cape
    private static final URL CONTRIBUTOR_CAPE_URL = new URL();

    public static void registerAll() {

        // Load the contributor cape
        int capeUID = PlayerCapeRegistry.getInstance().addCape(CapeLoader.resolveCapeFromURL(CONTRIBUTOR_CAPE_URL));

        // Define all contributors here
        PlayerCapeRegistry.getInstance().registerPlayerCape("Xnor50", capeUID);
    }

}