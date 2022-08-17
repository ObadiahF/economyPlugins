package hydra_ft.aternos.me.economy.commands;

import hydra_ft.aternos.me.economy.Main;
import jdk.internal.net.http.common.Log;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import net.md_5.bungee.api.ChatColor;

import java.io.*;

public class BalCommand implements CommandExecutor{
    private final Main main;

    public BalCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
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

            player.sendMessage(ChatColor.GREEN + "Balance: $" + Balance);
            return true;
        } else {
            main.getLogger().info("error with /bal command");
        }
        return true;
    }

}