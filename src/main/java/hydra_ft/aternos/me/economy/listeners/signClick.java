package hydra_ft.aternos.me.economy.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.ItemFrame;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class signClick implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        String mode = null;
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                int locationx = sign.getX();
                int locationy = sign.getY();
                int locationz = sign.getZ();

                //get block
                Material target = new Location(player.getWorld(), locationx, locationy - 1, locationz).getBlock().getType();
                //get mode
                if (sign.getLine(0).equals("[Buy]")) {
                    mode = "Buying";
                }
                if (sign.getLine(0).equals("[Sell]")) {
                    mode = "Selling";
                }

                String amount = sign.getLine(1);
                String Block = sign.getLine(2);
                String price = sign.getLine(3);

                //for selling
                if (mode == "Selling") {
                    String Balance = null;
                    if (player.getInventory().contains(new ItemStack(target, Integer.parseInt(amount)))) {
                        //read file
                        try {
                            File myObj = new File(player + ".txt");
                            Scanner myReader = new Scanner(myObj);
                            while (myReader.hasNextLine()) {
                                Balance  = myReader.nextLine();
                            }
                            myReader.close();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        int newBalance = Integer.parseInt(Balance) + Integer.parseInt(price);
                        String NewBalance1 = String.valueOf(newBalance);

                        try {
                            FileWriter myWriter = new FileWriter(player + ".txt");
                            myWriter.write(NewBalance1);
                            myWriter.close();
                            System.out.println("Successfully wrote to the file.");
                            player.sendMessage(ChatColor.GREEN + "Successfully sold " + amount + " " + Block + " for $" + price);
                            player.getInventory().addItem(new ItemStack(target, Integer.parseInt(amount)));
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        player.getInventory().removeItem(new ItemStack(target, Integer.parseInt(amount)));
                        mode = null;

                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have enough " + amount + " " + Block + " to sell.");
                    }
                }

                //for buying
                if (mode == "Buying") {
                    String Balance = null;
                    //read file
                    try {
                        File myObj = new File(player + ".txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            Balance  = myReader.nextLine();
                    }
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    //check if enough money
                    if (Integer.parseInt(Balance) < Integer.parseInt(price)) {
                        player.sendMessage(ChatColor.RED + "Not Enough Money");
                    } else {
                        int newBalance = Integer.parseInt(Balance) - Integer.parseInt(price);
                        String NewBalance1 = String.valueOf(newBalance);

                        try {
                            FileWriter myWriter = new FileWriter(player + ".txt");
                            myWriter.write(NewBalance1);
                            myWriter.close();
                            System.out.println("Successfully wrote to the file.");
                            player.sendMessage(ChatColor.GREEN + "Successfully bought " + Block + " for $" + price);
                            player.getInventory().addItem(new ItemStack(target, Integer.parseInt(amount)));
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        mode = null;

                    }
                    }
                    event.setCancelled(true);
            }
        }
    }
}
