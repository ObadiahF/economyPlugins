package hydra_ft.aternos.me.economy.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.*;

public class PlayerJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        BufferedReader reader;
        String Balance = null;
        try {
            reader = new BufferedReader(new FileReader(
                    player + ".txt"));
            Balance = reader.readLine();
            reader.close();

        } catch (IOException e) {
            Balance = "100";
            try {
                File myObj = new File(player + ".txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                    FileWriter myWriter = new FileWriter( player + ".txt");
                    myWriter.write(Balance);
                    myWriter.close();
                }
            } catch (IOException q) {
                System.out.println("An error occurred.");
                q.printStackTrace();
            }
        }
    }
}
