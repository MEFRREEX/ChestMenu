package theoni.chestmenu.inventories;

import cn.nukkit.Player;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.Item;
import cn.nukkit.utils.Config;
import me.iwareq.fakeinventories.CustomInventory;
import theoni.chestmenu.ChestMenu;
import theoni.chestmenu.utils.Placeholders;
import theoni.chestmenu.utils.Utils;

import java.util.List;

public class Inventories {

    public ChestMenu main;
    private Config menu;

    public Inventories(ChestMenu main) {
        this.main = main;
        this.menu = new Config(main.getDataFolder() + "/menu.yml", Config.YAML);
    }

    public void openInventory(Player player, String invName) {

        String name = menu.getString(invName + ".name");
        int chestType = menu.getInt(invName + ".chestType");

        CustomInventory inventory = new CustomInventory(getInventoryType(chestType), name);

        for (String s : menu.getSection(invName + ".items").getKeys(false)) {

            String section = invName + ".items." + s;

            String[] itemData = menu.getString(section + ".item").split(":");
            int id = Integer.parseInt(itemData[0]);
            int meta = Integer.parseInt(itemData[1]);
            String itemName = menu.getString(section + ".name");
            List<String> lore = menu.getStringList(section + ".lore");
            Item toset = Item.get(id, meta).setCustomName(itemName).setLore(String.join("\n", lore));

            boolean close = menu.getBoolean(section + ".closeOnClick");

            inventory.setItem(Integer.parseInt(s), toset, (item, event) -> {
                event.setCancelled(true);
                
                Player target = event.getTransaction().getSource();
                Placeholders placeholders = new Placeholders(target.getName(), s, itemName);
                
                List<String> commands = menu.getStringList(section + ".commands");
                Utils.dispatchCommands(target, commands, placeholders);

                List<String> actions = menu.getStringList(section + ".actions");
                Utils.executeActions(player, actions, inventory, placeholders);

                if (close) target.removeWindow(inventory);
            });
        }

        player.addWindow(inventory);
        
    }

    public InventoryType getInventoryType(int chestType) {
        InventoryType inventoryType = InventoryType.CHEST;
        switch(chestType) {
            case 1:
                inventoryType = InventoryType.CHEST;
                break;
            case 2:
                inventoryType = InventoryType.DOUBLE_CHEST;
                break;
            case 3:
                inventoryType = InventoryType.HOPPER;
                break;
            case 4:
                inventoryType = InventoryType.DROPPER;
                break;
        }
        return inventoryType;
    }

}
