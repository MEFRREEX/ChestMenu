# ChestMenu
With this plugin you can create menu interfaces in the chest

> [!WARNING]
> This plugin is obsolete and is no longer supported. Better and more functional alternative:  
> https://github.com/MEFRREEX/ChestCreator

## Dependency

The [`FakeInventories`](https://github.com/IWareQ/FakeInventories) plugin must be installed on the server for the plugin to work


## Example menu
![image](https://user-images.githubusercontent.com/83061703/216740109-c1174cb3-7bc4-4280-93a3-5c1f4b58deb3.png)


## Example menu.yml
```yaml
example_menu:
  # Command to open a menu
  command:
    name: "example"
    description: "Open example menu"
    # Need permission to open
    # Permission: chestmenu.menu.<menu name>
    # Example: chestmenu.menu.example_menu
    requirePermission: true
  # 1 - CHEST, 2 - DOUBLE_CHEST, 3 - HOPPER, 4 - DROPPER
  chestType: 1
  name: "Example Menu"
  items:
    "12":
      # id:meta
      item: "267:0"
      name: "Item Name"
      lore:
        - "§fLine 1"
        - "§fLine 2"
      # sender:command
      # Types of senders: player, console
      # Placeholders: {player}, {item_index}, {item_name}
      # Example: "console:say Test"
      commands:
        - "player:say Test command, executed by {player}"
      # type:name
      # Types: open, message, sound
      # Example: "open:example_menu"
      actions:
        - "open:example_menu"
        - "message:Example message"
        - "sound:random.pop"
      closeOnClick: false
```


## Permissions and commands
| Command                    | Permission        | Description                 |
| -------------------------- | ----------------- | --------------------------- |
| /open [menu name] [player] | chestmenu.open    | Open a certain menu to a specific player |
| Menu command               | chestmenu.menu.[menu name]| Permission to open a specific menu |


## Chest slots ID
![image](https://user-images.githubusercontent.com/83061703/229202490-38cdda8e-9ea3-4818-a653-f2b5e7f51f11.png)
