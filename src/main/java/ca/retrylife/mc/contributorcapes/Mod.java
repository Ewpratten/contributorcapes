package ca.retrylife.mc.contributorcapes;

import java.io.FileFilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mod implements ModInitializer {

    // Mod config
    public static final String MODID = "contributorcapes";
    public static final String NAME = "Contributor Capes";

    // Define a logger
    private static final Logger logger = LogManager.getLogger(NAME);

    // JSON loader
    private static final Gson GSON = new GsonBuilder().create();

    // Filter for PNG files
    private static final FileFilter PNG_FILE_FILTER = file -> file.getName().endsWith(".png");

    @Override
    public void onInitialize() {
        logger.info("Starting Contributor Capes mod");


        
        logger.info("Contributor Capes ready.");
    }
    
}