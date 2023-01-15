package com.meteoritepvp.colorsplugin;

import com.meteoritepvp.api.MeteoritePlugin;
import com.meteoritepvp.colorsplugin.commands.ColorCommand;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public final class ColorsPlugin extends MeteoritePlugin {
    private static ColorsPlugin instance;

    private List<String> colors = new LinkedList<>(); //List of colors that have been registered
    private HashMap<String, Material> possibleColors = new HashMap<>(16);

    /**
     * The onInit method where we register static commands
     */
    @Override
    public void onInit() {
        super.onInit();
        registerCommandClass(ColorCommand.class);
        instance = this;
    }

    @Override
    public void onEnable() {
        registerPlaceholderParameter("registeredColors", sender -> getColors());
        registerPlaceholderParameter("availableColors", sender -> getAvailableColors());
        initColors();
    }

    @Override
    public void onDisable() {
    }

    @Override
    protected void onRegisterCommands(String... aliases) {
        super.onRegisterCommands("c", "color");
    }

    @Override
    protected void onRegisterMainCommand(String description) {}

    /**
     * Register all possible minecraft colors and their corresponding wool material into our map.
     */
    private void initColors() {
        possibleColors.put("white", Material.WHITE_WOOL);
        possibleColors.put("orange", Material.ORANGE_WOOL);
        possibleColors.put("magenta", Material.MAGENTA_WOOL);
        possibleColors.put("light_blue", Material.LIGHT_BLUE_WOOL);
        possibleColors.put("yellow", Material.YELLOW_WOOL);
        possibleColors.put("lime", Material.LIME_WOOL);
        possibleColors.put("pink", Material.PINK_WOOL);
        possibleColors.put("gray", Material.GRAY_WOOL);
        possibleColors.put("light_gray", Material.LIGHT_GRAY_WOOL);
        possibleColors.put("cyan", Material.CYAN_WOOL);
        possibleColors.put("purple", Material.PURPLE_WOOL);
        possibleColors.put("blue", Material.BLUE_WOOL);
        possibleColors.put("brown", Material.BROWN_WOOL);
        possibleColors.put("green", Material.GREEN_WOOL);
        possibleColors.put("red", Material.RED_WOOL);
        possibleColors.put("black", Material.BLACK_WOOL);
    }

    /**
     * This method returns the list of colors
     * @return The list of currently registered colors
     */
    public List<String> getColors() {
        return colors;
    }

    /**
     * Adds a color to the registered color list
     * @param color The name of the color to be added
     */
    public void registerColor(String color) {
        colors.add(color);
    }

    /**
     * Removes a color from the registered color list
     * @param color The name of the color to be removed
     */
    public void unregisterColor(String color) {
        colors.remove(color);
    }

    /**
     * This method returns the list of available colors
     * @return A list of possible colors excluding already registered colors
     */
    public List<String> getAvailableColors() {
        List<String> available = new ArrayList<>(possibleColors.keySet());
        available.removeAll(colors);
        return available;
    }

    /**
     * This method returns the map containing all possible colors and their wool material
     * @return The Hashmap of possible colors
     */
    public HashMap<String, Material> getPossibleColors() {
        return possibleColors;
    }

    /**
     * A method to get the current plugin instance
     * @return The instance of the ColorsPlugin
     */
    public static ColorsPlugin getInstance() {
        return instance;
    }
}
