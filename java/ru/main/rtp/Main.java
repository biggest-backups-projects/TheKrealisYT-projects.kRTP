package ru.main.rtp;

import java.util.*; // а чё не фулл импорты надо делать????
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import ru.main.rtp.utils.*;
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
        Config.init(); // а что если не инит, а цинитка и -winlocker STOP SERVER ???
        getCommand("krtp").setExecutor(this);
        Logger.info("§3");
        Logger.info("§3 .----------------------------------------------------------. ");
        Logger.info("§3| .-------------------------------------------------------. |");
        Logger.info("§3| |\t\t\t\t\t\t       §3| |");
        Logger.info("§3| |            §fПлагин: §3kRTP§8| §fВерсия: §d2.2                  §9| |");
        Logger.info("§3| |        §fРазработчик плагина §3TheKrealisYT §8- @yacushev   §3| |"); // помню как у этого креалиса была страничка ХАКЕР КРЕАЛИС, она в бане кста, надо будет разбанить ему как-то...
        Logger.info("§3| |           §fПоследние обновлние: §a17.09.2020             §9| |");
        Logger.info("§3| |\t\t\t\t\t\t       §3| |");
        Logger.info("§3| '-------------------------------------------------------' |");
        Logger.info("§3'-----------------------------------------------------------'");
        Logger.info("§3");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) { // генесис команда бай креалисёнок
        //if(sender != null) {  тут уже спорный моментик, нищие не юзают самопис коры, поэтому похуй на них
            if (!sender.hasPermission("krtp.command")) {
                sender.sendMessage(getInstance().getConfig().getString("Messages.noPermission", "&cУ вас нет прав!").replace("&", "§"));
                return true;
            } else if (!(sender instanceof Player)) {
                sender.sendMessage(getInstance().getConfig().getString("Messages.OnlyPlayer", "&cПоблизости нет игроков!").replace("&", "§"));
                return true;
            }
                List<Player> players = new ArrayList<>(); // бля я тут затупил с декомпилом, прошу прощение
                Player p = (Player) sender;
                World worldPlayer = p.getWorld();
                Bukkit.getOnlinePlayers().forEach(randomPlayer ->  // рандом нн юзер на ратке креалиса...
                {// блять я вообще нихуя не понял эту конструкцию изичную, поэтому насрать...
                    if (randomPlayer.getWorld().equals(worldPlayer) && !randomPlayer.equals(p)) { // equals воркает быстрее
                        players.add(randomPlayer);
                    }
                });
                if (players.size() == 0) {
                    sender.sendMessage(getInstance().getConfig().getString("Messages.noplayer",
                            "&cПоблизости нет игроков!" // поблизости нет бомжей
                    ).replace("&", "§"));
                    return true;
                } else {
                    Player randomPlayer = players.get(this.random.nextInt(players.size())); // блять...
                    int x = (int) randomPlayer.getLocation().getX() + random.nextInt(11) + 30;
                    int z = (int) randomPlayer.getLocation().getZ() + random.nextInt(11) + 30;
                    int y = randomPlayer.getWorld().getHighestBlockYAt(x, z);
                    if (p.getFoodLevel() < getInstance().getConfig().getInt("RTP.FoodLevel")) {
                        p.sendMessage(getInstance().getConfig().getString("Messages.nofood",
                                "&aУ вас недостаточно голода, поешьте!") // схуяли креалис выбрал зелёный цвет если ты бомж по хавке?
                                .replace("&", "§"));
                        return true;
                    } else {
                        p.teleport(new Location(randomPlayer.getWorld(), x, y, z));
                        sender.sendMessage(getInstance().getConfig().getString("Messages.teleport", "&aВы успешно телепортированы").replace("&", "§"));
                        FoodLevelChangeEvent event = new FoodLevelChangeEvent(p, p.getFoodLevel() - getInstance().getConfig().getInt("RTP.FoodLevel", 6)); // креалис спастил хороший код
                        Bukkit.getServer().getPluginManager().callEvent(event);
                        p.setFoodLevel(event.getFoodLevel()); // я не помню - будут ли тут баги или не будут с эвентом, а может только так и работает. хер его знает как это уже работает, тестить лень
                        return true;
                    }
                }

        //  }
        // чудо баккит
     //   return true;
    }

    /**
     * Auto generation
     */
   /* @Override
    public void onDisable() {
        HandlerList.unregisterAll(this); // да, фиксы накинули, но вспомнили что листенер убрали
    } */

    /**
     @return instance in main
     */
    public static Main getInstance() {
        return instance;
    }

}
