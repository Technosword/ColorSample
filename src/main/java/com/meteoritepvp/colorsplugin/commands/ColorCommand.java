package com.meteoritepvp.colorsplugin.commands;

import com.meteoritepvp.api.command.Command;
import com.meteoritepvp.api.command.CommandClass;
import com.meteoritepvp.api.command.DefaultCommand;
import com.meteoritepvp.api.inventory.MeteoriteInventory;
import com.meteoritepvp.api.inventory.presets.BasicInventory;
import com.meteoritepvp.api.utils.CC;
import com.meteoritepvp.colorsplugin.ColorsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@DefaultCommand
public class ColorCommand implements CommandClass {

    /**
     * The method for the main command. The main command brings up a GUI with all the registered colors
     */
    @Command(description = "Open the color GUI")
    public void onMainCommand(Player p) {
        p.sendMessage(CC.format("&AOpening color GUI..."));
        showColorInventory(p);
    }

    /**
     * The method for the add command. The add command adds the params to the registered color list if it is a valid color and not already registered
     */
    @Command(description = "Adds a color to the registered color list.",
    args = {"add"},
    params = "@availableColors")
    public void onAddCommand(Player p, String[] params) {
        String color = String.join("_", params);
        ColorsPlugin plugin = ColorsPlugin.getInstance();
        if (plugin.getAvailableColors().contains(color)) {
            plugin.registerColor(color);
            p.sendMessage(CC.format("&aSuccessfully added your color!"));
        } else {
            p.sendMessage(CC.format("&cThat color has already been registered or does not exist!"));
        }
    }
    /**
     * The method for the remove command. The remove command removes the param color from the registered list
     */
    @Command(description = "Removes a color from the registered list.",
            args = {"remove"},
            params = "@registeredColors")
    public void onRemoveCommand(Player p, String[] params) {
        String color = String.join("_", params);
        ColorsPlugin plugin = ColorsPlugin.getInstance();
        if (plugin.getColors().contains(color)) {
            plugin.unregisterColor(color);
            p.sendMessage(CC.format("&aSuccessfully removed your color!"));
        } else {
            p.sendMessage(CC.format("&cThat color was not registered!"));
        }
    }

    /**
     * This method creates and shows an inventory containing all registered colors to a player
     * @param p The player that the inventory should be shown to
     */
    public void showColorInventory(Player p) {
        ColorsPlugin plugin = ColorsPlugin.getInstance();
        MeteoriteInventory inventory = new MeteoriteInventory(plugin, CC.BLACK + CC.BOLD + "Colors GUI", 9, 6, true);

        BasicInventory page = new BasicInventory();
        HashMap<String, Material> materialsMap = plugin.getPossibleColors();
        for (int i = 0; i < plugin.getColors().size(); i++) { //for every registered color, add its wool material to the page
            page.setItem(i, new ItemStack(materialsMap.get(plugin.getColors().get(i))));
        }
        page.update();
        inventory.applyPage(page);
        inventory.show(p);
    }
}
