package AntiCast;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public final class Broadcast {

    public static final String HEADER = "" +
        ChatColor.RESET + 
        ChatColor.BOLD + 
        ChatColor.DARK_AQUA + "[" +
        ChatColor.AQUA + "SBALC" +
        ChatColor.DARK_AQUA + "]" +
        ChatColor.RESET + ChatColor.WHITE + " ";

    public static final String NORMAL   = ChatColor.GRAY.toString();
    public static final String GOOD     = ChatColor.GREEN.toString();
    public static final String WARNING  = ChatColor.YELLOW.toString();
    public static final String CRITICAL = ChatColor.RED.toString();
    
    private static void broadcast(String msg) {
        Bukkit.broadcastMessage(HEADER + msg);
    }

    public static void normal(String msg) {
        broadcast(NORMAL + msg);
    }

    public static void good(String msg) {
        broadcast(GOOD + msg);
    }

    public static void warning(String msg) {
        broadcast(WARNING + msg);
    }

    public static void critical(String msg) {
        broadcast(CRITICAL + msg);
    }
}
