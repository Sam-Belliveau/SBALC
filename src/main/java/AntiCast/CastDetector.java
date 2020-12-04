package AntiCast;

import java.util.LinkedList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.block.Block;
import org.bukkit.Material;

/**
 * Detects if casting is taking place and prevents it
 * 
 * @author Sam Belliveau (sam.belliveau@gmail.com)
 */
public class CastDetector implements Listener {

    // Booleans used to make sure console doesnt get spammed with messages
    private boolean cancelled = false;
    private LinkedList<Long> casts = new LinkedList<>();

    @EventHandler
    public void onCobbleFormation(BlockFormEvent e) {
        // The current time of this call
        long now = System.currentTimeMillis();

        // Get the information of the Block
        Block block = e.getBlock();
        Material type = block.getType();
        int x = block.getX();
        int y = block.getY();

        if (Math.abs(x) < Constants.MAX_X_COORD &&
            Math.abs(y) < Constants.MAX_Y_COORD &&
            (type == Material.LAVA || type == Material.WATER)
        ) {
            // Add time of cast to lists
            casts.addLast(now);

            // Ignore any casts that are too old
            while(0 < casts.size() && Constants.SAMPLE_TIME_MS < (now - casts.getFirst())) {
                casts.removeFirst();
            }

            // Get number of casts in sample time
            int size = casts.size();

            // Check to see how many casts have happened
            if(size > Constants.MAX_CASTS_CANCEL) {
                // Cancel the formation of cobble or any other block
                e.setCancelled(true);
                block.setType(Material.AIR);
                
                // Write message about lava cast being detected
                if(!cancelled) {
                    Broadcast.critical(String.format("Lavacasting At Spawn Detected! [%d, %d]", x, y));
                    Broadcast.critical("Temporarily Disabled Lava & Water Interactions At Spawn!");
                    cancelled = true;
                }

            } else if (size > Constants.MAX_CASTS_WARN) {
                // Only write warning if the cast hasnt been cancelled
                if(!cancelled) {
                    long age = (now - casts.getFirst()) / 1000;
                    String msg = String.format("%d casts at spawn in past %d secs [%d, %d]", size, age, x, y);
                    Broadcast.warning(msg);
                } 
                
                // If it is cancelled, clear the block
                else {
                    e.setCancelled(true);
                    block.setType(Material.AIR);
                }
            } else {
                // Uncancel once the value gets low enough
                if(cancelled) {
                    Broadcast.good("Enabled Lava & Water Interactions At Spawn!");
                    cancelled = false;
                }
            }
        }
        
    }

}