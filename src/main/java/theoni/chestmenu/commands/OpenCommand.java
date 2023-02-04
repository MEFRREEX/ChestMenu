package theoni.chestmenu.commands;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandEnum;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.Config;
import theoni.chestmenu.ChestMenu;

public class OpenCommand extends Command {

    private ChestMenu main;
    private Config menu;

    public OpenCommand(ChestMenu main) {
        super("open");
        this.setDescription("Open menu");

        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[] {
            CommandParameter.newEnum("menu", new CommandEnum("Menu", getMenus())),
            CommandParameter.newType("player", true, CommandParamType.TARGET)
        });

        this.main = main;
        this.menu = new Config(main.getDataFolder() + "/menu.yml", Config.YAML);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("Usage: /" + commandLabel + " <menu> [player]");
            return false;
        }

        String name = args[0];
        
        if (!menu.exists(name)) {
            sender.sendMessage("§l§c>§r§f Menu not found.");
            return false;
        }

        Player player;

        if (args.length == 2) {
            player = main.getServer().getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage("§l§c>§r§f Player not found.");
                return false;
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§l§c>§r§f This command can only be used in the game.");
                return false;
            }
            player = (Player) sender;
        }
        
        sender.sendMessage("§l§a>§r§f The menu was opened.");
        main.getInventories().openInventory(player, name);
        return false;
    }
    
    public List<String> getMenus() {
        List<String> list = new ArrayList<>();
        for (String name : new Config(ChestMenu.getInstance().getDataFolder() + "/menu.yml", Config.YAML).getRootSection().getKeys(false)) {
            list.add(name);
        }
        return list;
    }
}
