package AntiCast;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * AntiCast java plugin
 * 
 * @author Sam Belliveau (sam.belliveau@gmail.com)
 */
public class AntiCastPlugin extends JavaPlugin {
    private static final Logger LOGGER = Logger.getLogger("AntiCast");

    public CastDetector detector = new CastDetector();

    public void onEnable() {
        getServer().getPluginManager().registerEvents(detector, this);
        LOGGER.info("Sam Belliveau's Anti Lava Cast Enabled");
    }

    public void onDisable() {
        LOGGER.info("Sam Belliveau's Anti Lava Cast Disabled");
    }
}
