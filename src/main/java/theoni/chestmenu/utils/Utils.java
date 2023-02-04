package theoni.chestmenu.utils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.nukkit.Player;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.network.protocol.PlaySoundPacket;
import theoni.chestmenu.ChestMenu;

public class Utils {
    
    public static void playSound(Player player, String sound) {
        PlaySoundPacket pk = new PlaySoundPacket();

        pk.volume = (float) 1;
        pk.x = player.getFloorX();
        pk.y = player.getFloorY();
        pk.z = player.getFloorZ();

        pk.name = sound;
        pk.pitch = (float) 1;
        player.dataPacket(pk);
    }

    public static void dispatchCommands(Player player, List<String> commands, Placeholders placeholders) {
        for (String command : commands) {
            
            String as = command.split(":")[0];
            String commandName = command.split(":")[1];

            if (as.equalsIgnoreCase("player")) {
                ChestMenu.getInstance().getServer().dispatchCommand(player, placeholders.replace(commandName));
            } else {
                ChestMenu.getInstance().getServer().dispatchCommand(ChestMenu.getInstance().getServer().getConsoleSender(), placeholders.replace(commandName));
            }
        }
    }

    public static void executeActions(Player player, List<String> actions, Inventory inventory, Placeholders placeholders) {
        for (String action : actions) {
            
            String[] split = action.split(":");
        
            String type = split[0];
            String text = split[1];
        
            if (type.equalsIgnoreCase("open")) {
                if (!ChestMenu.getInstance().getMenu().exists(text)) {
                    ChestMenu.getInstance().getLogger().error("§cFailed to open the " + text + " menu.");
                    return;
                }
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        ChestMenu.getInstance().getInventories().openInventory(player, text);
                    }
                }, 1000);
            
            } 
            
            else if (type.equalsIgnoreCase("message")) {
                player.sendMessage(placeholders.replace(text));
            }
        
            else if (type.equalsIgnoreCase("sound")) {
                playSound(player, text);
            }
        }
    }
}
