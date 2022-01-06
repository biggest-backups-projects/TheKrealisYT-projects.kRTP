package ru.main.addon.utils;

import org.bukkit.*;
import ru.main.addon.*;

/**
 * @Copyright © 2021 TheKrealisYT or Exept.
 * @Author source code: @naulbimix. All rights reserved. Please telegram @naulbimix for usage rights and other information.
 */


public class Logger {

    public static void info(String text) {
        Bukkit.getConsoleSender().sendMessage("§e(" + Main.getInstance().getDescription().getName() + "/INFO) " + text);
    }

    public static void warn(String text) {
        Bukkit.getConsoleSender().sendMessage("§e(" + Main.getInstance().getDescription().getName() + "/WARN) " + text);
    }

    public static void error(String text) {
        Bukkit.getConsoleSender().sendMessage("§e(" + Main.getInstance().getDescription().getName() + "/ERROR) " + text);
    }


}
