package theoni.chestmenu.utils;

import lombok.Getter;

@Getter
public class Placeholders {
    
    private String name;
    private String itemIndex;
    private String itemName;

    public Placeholders(String name, String itemIndex, String itemName) {
        this.name = name;
        this.itemIndex = itemIndex;
        this.itemName = itemName;
    }

    public String replace(String text) {
        return text.replace("{player}", name)
                   .replace("{item_index}", itemIndex)
                   .replace("{item_name}", itemName);
    }
}
