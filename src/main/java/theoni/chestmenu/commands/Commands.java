package theoni.chestmenu.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import theoni.chestmenu.ChestMenu;

public class Commands {
    
    public void register(ChestMenu main, Config menu) {

        for (String section : menu.getRootSection().getKeys(false)) {

            String name = menu.getString(section + ".command.name");
            String description = menu.getString(section + ".command.description");
            boolean requirePermission = menu.getBoolean(section + ".command.requirePermission");

            main.getServer().getCommandMap().register("ChestMenu", new Command(name, description) {

                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    if (!sender.hasPermission("chestmenu.menu." + section) && requirePermission) {
                        sender.sendMessage("§l§c>§r§f You don't have permission.");
                        return false;
                    }
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§l§c>§r§f This command can only be used in the game.");
                        return false;
                    }
                    main.getInventories().openInventory((Player) sender, section);
                    return true;
                }
            });
        }
    }
}
