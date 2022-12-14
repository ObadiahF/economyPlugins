package hydra_ft.aternos.me.economy.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.ItemFrame;
import org.bukkit.util.BoundingBox;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class signClick implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        //sell
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0).equals("Converter")) {
                    Material target = Material.AMETHYST_CLUSTER;
                    String Block = sign.getLine(2);
                    String price = sign.getLine(3);
                    String Balance = null;
                    if (player.getInventory().containsAtLeast(new ItemStack(target), 1)) {
                        //read file
                        try {
                            File myObj = new File( player + ".txt");
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
                            System.out.println(player + "'s Bal: $" + newBalance);
                            player.sendMessage(ChatColor.GREEN + "Successfully sold 1 " + Block + " for $" + price);
                            player.getInventory().removeItem(new ItemStack(target, 1));
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        event.setCancelled(true);

                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have enough " + Block + " to sell.");
                        event.setCancelled(true);
                    }
                }
            }
        }
        //buy
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0).equals("Converter")) {
                    Material customBlock = Material.AMETHYST_CLUSTER;
                    String Block = sign.getLine(2);
                    String price = sign.getLine(3);
                    String Balance = null;
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
                            System.out.println(player + "'s Bal: $" + newBalance);
                            player.sendMessage(ChatColor.GREEN + "Successfully bought " + Block + " for $" + price);
                            player.getInventory().addItem(new ItemStack(customBlock, 1));
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        event.setCancelled(true);
                    }
                }

            }
        }
        //Normal Shops
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0).equals("Shop")) {
                    int locationx = sign.getX();
                    int locationy = sign.getY();
                    int locationz = sign.getZ();

                    String customBlock = sign.getLine(1);
                    String Block = sign.getLine(2);
                    String price = sign.getLine(3);

                    //get block
                    Material target = new Location(player.getWorld(), locationx, locationy - 1, locationz).getBlock().getType();

                    if (!customBlock.equals("")) {
                        target = Material.valueOf(customBlock);
                    }
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
                            System.out.println(player + "'s Bal: $" + newBalance);
                            player.sendMessage(ChatColor.GREEN + "Successfully bought " + Block + " for $" + price);
                            player.getInventory().addItem(new ItemStack(target, 1));
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        event.setCancelled(true);
                    }
                }
            }
                }

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0).equals("Shop")) {
                    int locationx = sign.getX();
                    int locationy = sign.getY();
                    int locationz = sign.getZ();

                    String customBlock = sign.getLine(1);
                    String Block = sign.getLine(2);
                    String price1 = sign.getLine(3);
                    int price2 = (int) (Integer.parseInt(price1) * .25);
                    String price = String.valueOf(Math.round(price2));
                    //get block
                    Material target = new Location(player.getWorld(), locationx, locationy - 1, locationz).getBlock().getType();

                    if (!customBlock.equals("")) {
                        target = Material.valueOf(customBlock);
                    }
                    String Balance = null;
                    if (player.getInventory().containsAtLeast(new ItemStack(target), 1)) {
                        //read file
                        try {
                            File myObj = new File( player + ".txt");
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
                            System.out.println(player + "'s Bal: $" + newBalance);
                            player.sendMessage(ChatColor.GREEN + "Successfully sold 1 " + Block + " for $" + price);
                            player.getInventory().removeItem(new ItemStack(target, 1));
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have enough " + Block + " to sell.");
                    }
                }
            }
                }
                }
            }