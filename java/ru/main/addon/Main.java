package ru.main.addon;

import java.util.*; // а чё не фулл импорты надо делать????
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import ru.main.addon.utils.*;
import org.bukkit.plugin.java.*;
import org.bukkit.event.player.*;
import org.bukkit.event.entity.*;
import org.bukkit.configuration.file.*;

/**
 * @Copyright © 2021 TheKrealisYT or Exept.
 * @Author source code: @naulbimix. All rights reserved. Please telegram @naulbimix for usage rights and other information.
 */

public class Main extends JavaPlugin {

    private static Main instance;
    public Random random = new Random();
    public static Main inst;
    public static FileConfiguration data;

    /**
     * Auto generation
     */
    @Override
    public void onEnable() {
        instance = this;
        Config.init();
        getCommand("prtp").setExecutor(this);
        Logger.info("§3");
        Logger.info("§3 .----------------------------------------------------------. ");
        Logger.info("§3| .-------------------------------------------------------. |");
        Logger.info("§3| |\t\t\t\t\t\t       §3| |");
        Logger.info("§3| |            §fПлагин: §3kRTP§8| §fВерсия: §d2.1                  §9| |");
        Logger.info("§3| |        §fРазработчик плагина §3TheKrealisYT §8- @yacushev   §3| |"); // помню как у этого креалиса была страничка ХАКЕР КРЕАЛИС, она в бане кста, надо будет разбанить ему как-то...
        Logger.info("§3| |           §fПоследние обновлние: §a17.09.2020             §9| |");
        Logger.info("§3| |\t\t\t\t\t\t       §3| |");
        Logger.info("§3| '-------------------------------------------------------' |");
        Logger.info("§3'-----------------------------------------------------------'");
        Logger.info("§3");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) { // генесис команда бай креалисёнок
        if (!sender.hasPermission("prtp.perm")) {
            sender.sendMessage(getInstance().getConfig().getString("Messages.noPermission").replace("&", "§"));
            return true;
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(getInstance().getConfig().getString("Messages.OnlyPlayer").replace("&", "§"));
            return true;
        } else if (!command.getName().equals("prtp")) {
            return true;
        } else {
            List<Player> players = new ArrayList();
            for(Player randomPlayer : Bukkit.getOnlinePlayers()) {
                if (randomPlayer.getWorld() == ((Player) sender).getWorld() && randomPlayer != sender) {
                    players.add(randomPlayer);
                }
                if (players.size() == 0) {
                    sender.sendMessage(getInstance().getConfig().getString("Messages.noplayer").replace("&", "§"));
                    return true;
                } else {
                    randomPlayer = players.get(this.random.nextInt(players.size()));
                    Player p = (Player)sender;
                    int x = (int) randomPlayer.getLocation().getX() + this.random.nextInt(11) + 30;
                    int z = (int) randomPlayer.getLocation().getZ() + this.random.nextInt(11) + 30;
                    int y = randomPlayer.getWorld().getHighestBlockYAt(x, z);
                    if (p.getFoodLevel() < getInstance().getConfig().getInt("RTP.FoodLevel")) {
                        p.sendMessage(getInstance().getConfig().getString("Messages.nofood").replace("&", "§"));
                        return true;
                    } else {
                        ((Player) sender).teleport(new Location(randomPlayer.getWorld(),  x, y, z));
                        sender.sendMessage(getInstance().getConfig().getString("Messages.teleport").replace("&", "§"));
                        FoodLevelChangeEvent event = new FoodLevelChangeEvent(p, p.getFoodLevel() - getInstance().getConfig().getInt("RTP.FoodLevel")); // креалис спастил хороший код
                        Bukkit.getPluginManager().callEvent(event);
                        p.setFoodLevel(event.getFoodLevel());
                        return true;
                    }
                }
            }
            return true;
        }
    }

    /**
     * Auto generation
     */
    @Override
    public void onDisable() {}

    public String getMSG(String w) {
        return ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Messages." + w));
    }

    /**
     @return instance in main
     */
    public static Main getInstance() {
        return instance;
    }

}
