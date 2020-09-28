package ca.retrylife.mc.contributorcapes.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Tools for loading capes
 */
public class CapeLoader {

    /**
     * Get a cape from a URL
     * 
     * @param url URL
     * @return Cape image
     */
    public static BufferedImage resolveCapeFromURL(URL url) throws IOException {
        return ImageIO.read(url);
    }

}