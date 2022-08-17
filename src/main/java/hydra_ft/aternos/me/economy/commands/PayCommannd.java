package hydra_ft.aternos.me.economy.commands;

import hydra_ft.aternos.me.economy.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;


public class PayCommannd implements CommandExecutor {
    private final Main main;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public PayCommannd(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
            String player2string = String.valueOf(player);
            String RecievingPlayer = null;
            String amount = "";
            String Balance = null;
            //check if both parms were used
            if (args.length == 2) {
                RecievingPlayer = String.valueOf(Bukkit.getPlayer(args[0]));
                if (RecievingPlayer != "null") {
                    //check if no player
                    amount = args[1];
                    if (player == Bukkit.getPlayer(args[0])) {
                        player.sendMessage(ChatColor.RED + "Cannot send money to yourself!");
                        return true;
                    }
                    if (!(Bukkit.getPlayer(args[0]).isOnline())) {
                        player.sendMessage(ChatColor.RED + "Player must be online!");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "/pay <player> <amount>");
                    return true;
                }

            } else {
                player.sendMessage(ChatColor.RED + "/pay <player> <amount>");
                return true;
            }
            //check that val is a number
            try {
                int Value = Integer.parseInt(amount);
                if (Value <= 0) {
                    player.sendMessage(ChatColor.RED + "Cannot send less than $1");
                    return true;
                }
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "/pay <player> <amount>");
                return true;
            }
            //Start sending the money

            //read sender account
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(
                        player + ".txt"));
                Balance = reader.readLine();
                reader.close();
            } catch (IOException e) {
                try {
                    Balance = "100";
                    File myObj = new File(player + ".txt");
                        System.out.println("File created: " + myObj.getName());
                        FileWriter myWriter = new FileWriter( player + ".txt");
                        myWriter.write(Balance);
                        myWriter.close();
                } catch (IOException q) {
                    System.out.println("An error occurred.");
                    q.printStackTrace();
                }
            }
            int BalanceToInt = Integer.parseInt(Balance);
            int amountToInt = Integer.parseInt(amount);

            //check if player has enough money
            if (BalanceToInt < amountToInt) {
                player.sendMessage(ChatColor.RED + "You do not have enough money!");
                return true;
            } else {
                //take away money from sender
                int newTotal = BalanceToInt - amountToInt;
                String newBalance = String.valueOf(newTotal);

                //write to senders file
                try {
                    File myObj = new File(player + ".txt");
                    System.out.println("File created: " + myObj.getName());
                    FileWriter myWriter = new FileWriter( player + ".txt");
                    myWriter.write(newBalance);
                    myWriter.close();
                } catch (IOException q) {
                    System.out.println("An error occurred.");
                    q.printStackTrace();
                }


                //read Recievers file
                String RecieverBalance = null;
                try {
                    reader = new BufferedReader(new FileReader(
                            RecievingPlayer + ".txt"));
                    RecieverBalance = reader.readLine();
                    reader.close();
                } catch (IOException e) {
                    try {
                        File myObj = new File(RecievingPlayer + ".txt");
                        System.out.println("File created: " + myObj.getName());
                        FileWriter myWriter = new FileWriter( RecievingPlayer + ".txt");
                        myWriter.write(newBalance);
                        myWriter.close();
                        RecieverBalance = "100";
                    } catch (IOException q) {
                        System.out.println("An error occurred.");
                        q.printStackTrace();
                    }
                }
                //player.sendMessage("Reciever bal: " + RecieverBalance);
                //add moeny to reciever money from sender

                int RecBalanceToInt = Integer.parseInt(RecieverBalance);
                int RecAmountToInt = Integer.parseInt(amount);
                int RecnewTotal = RecAmountToInt + RecBalanceToInt;
                String RecnewBalance = String.valueOf(RecnewTotal);

                //player.sendMessage("New bal" + RecnewBalance);
                //send to reciever
                try {
                    Files.write(Paths.get(RecievingPlayer + ".txt"), RecnewBalance.getBytes(), StandardOpenOption.WRITE);

                    String newText = RecievingPlayer.substring(17);
                    String newtext1 = (newText.substring(0, newText.length() - 1));

                    String newText2 = player2string.substring(17);
                    String newtext3 = (newText2.substring(0, newText2.length() - 1));

                    //send messages to users
                    player.sendMessage(ChatColor.GREEN + "Successfully sent $" + amount + " to " + newtext1);
                    Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "You have been sent $" + amount + " from " + newtext3 + "!");

                }catch (IOException e) {
                    player.sendMessage(ChatColor.RED + "An error occurred.");
                    return true;
                }
            }


        }
        return true;
    }}


