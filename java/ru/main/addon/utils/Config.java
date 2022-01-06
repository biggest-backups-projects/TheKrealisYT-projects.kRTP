package ru.main.addon.utils;

import java.io.*;
import ru.main.addon.*;
import org.bukkit.configuration.file.*;

/**
 * @Copyright © 2021 TheKrealisYT or Exept.
 * @Author source code: @naulbimix. All rights reserved. Please telegram @naulbimix for usage rights and other information.
 */

public class Config {

    private static FileConfiguration config;

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void saveConfig() {
        save(config, "config.yml");
    }

    public static void init() {
        config = create("config.yml");
    }

    public static FileConfiguration create(String fileName) {
        File file = new File(Main.getInstance().getDataFolder(), fileName);

        if (Main.getInstance().getResource(fileName) == null)
            return save(YamlConfiguration.loadConfiguration(file), fileName);

        if (!file.exists())
            Logger.info("File not fond. Creating new file " + fileName + "...");
            Main.getInstance().saveResource(fileName, false);

        return YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration save(FileConfiguration config, String fileName) {
        try {
            config.save(new File(Main.getInstance().getDataFolder(), fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

}
