package theoni.chestmenu;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import theoni.chestmenu.commands.Commands;
import theoni.chestmenu.commands.OpenCommand;
import theoni.chestmenu.inventories.Inventories;
import theoni.chestmenu.metrics.Metrics;
import lombok.Getter;

public class ChestMenu extends PluginBase {

    @Getter private static ChestMenu instance;
    @Getter private Inventories inventories;
    @Getter private Config menu;

    public void onLoad() {
        this.saveDefaultConfig();
        this.saveResource("menu.yml");
    }

    public void onEnable() {
        instance = this;

        this.inventories = new Inventories(this);
        this.menu = new Config(this.getDataFolder() + "/menu.yml", Config.YAML);


        this.getServer().getCommandMap().register("ChestMenu", new OpenCommand(this));
        new Commands().register(this, menu);
        
        this.metrics();
    }

    public void metrics() {
        int pluginId = 17642;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("server_movement", () -> String.valueOf(getConfig().getBoolean("PowerNukkiX-movement-server"))));
        metrics.addCustomChart(new Metrics.SimplePie("nukkit_version", () -> Server.getInstance().getNukkitVersion()));
    }
}
