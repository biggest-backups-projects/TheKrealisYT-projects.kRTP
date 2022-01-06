package ru.main.rtp.utils;

import java.io.*;
import ru.main.rtp.*;
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

    public static FileConfiguration create(String fileName) { // upd 28.10.2021: пересмотрел пендосов и баккит, в принципе утилки винлокера02, а точнее опенсурс с баккита более менее пастят кфг, но БЛЯТЬ, ваша оперативочка сосёт хуй долго и очень хорошо, но МЫ ВЕДЬ НЕ БУДЕМ ОПТИМИЗИРОВАТЬ ЭТО ГОВНО, МЫ ДОКУПИМ ЕЩЁ ОПЕРАТИВЧКУ. вот именно поэтому ваш сервер даже с 128 гигами сосёт, а гении с 16 гигами окупаются
        File file = new File(Main.getInstance().getDataFolder(), fileName);

        if (Main.getInstance().getResource(fileName) == null) return save(YamlConfiguration.loadConfiguration(file), fileName);

        if (!file.exists()) Logger.info("File not found. Creating new file " + fileName + "..."); // фиксим грамматического креалиса. я бы здесь написал русский язык, но если у вас нету фиксов на кодировку от этого ебучего спигота, то вы обречены на бедствие в этой кодировке ведь вы знаете мало о ней.
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
