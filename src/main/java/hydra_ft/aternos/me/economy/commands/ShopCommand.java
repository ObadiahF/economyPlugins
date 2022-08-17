package hydra_ft.aternos.me.economy.commands;


import hydra_ft.aternos.me.economy.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class ShopCommand implements Listener, CommandExecutor {
    private final Main main;

    public ShopCommand(Main main) {
        this.main = main;
    }
/*
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory open = event.getClickedInventory();
        String name = player.getOpenInventory().getTitle();
        ItemStack item = event.getCurrentItem();
        if (item.getType() == Material.GRASS_BLOCK) {
            player.sendMessage(ChatColor.GREEN + "It worked!");
            player.closeInventory();
        }
    }
*/
@EventHandler
public void onPlayerInteract(PlayerInteractEvent e) {
    Action a = e.getAction();
    Block block = e.getClickedBlock();
    Player plr = e.getPlayer();
    if(block.getState() instanceof Sign) {
        Sign sign = (Sign) block.getState();
        String[] ln = sign.getLines();
        if(ln[0].equalsIgnoreCase("Hi")){
            plr.sendMessage("Hi");
        }
    }
}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    /*
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "House Of Bread Item Shop");

     */
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }

        ItemStack Blocks = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta BlocksMeta = Blocks.getItemMeta();

        ItemStack Interactive_Blocks = new ItemStack(Material.ANVIL);
        ItemMeta Interactive_BlocksMeta = Interactive_Blocks.getItemMeta();

        ItemStack Resources = new ItemStack(Material.IRON_INGOT);
        ItemMeta ResourcesMeta = Resources.getItemMeta();

        ItemStack Food = new ItemStack(Material.BREAD);
        ItemMeta FoodMeta = Food.getItemMeta();

        ItemStack Mob_Drops = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta Mob_DropsMeta = Mob_Drops.getItemMeta();

        ItemStack Potions = new ItemStack(Material.POTION);
        ItemMeta PotionsMeta = Potions.getItemMeta();

        ItemStack Plants = new ItemStack(Material.FERN);
        ItemMeta PlantsMeta = Plants.getItemMeta();

        ItemStack Raiding_Supplies = new ItemStack(Material.TNT);
        ItemMeta Raiding_SuppliesMeta = Raiding_Supplies.getItemMeta();

        ItemStack Redstone = new ItemStack(Material.REDSTONE);
        ItemMeta RedstoneMeta = Redstone.getItemMeta();

        ItemStack Cancel = new ItemStack(Material.BARRIER);
        ItemMeta CancelMeta = Cancel.getItemMeta();


        //names
        BlocksMeta.setDisplayName(ChatColor.AQUA + "Blocks");
        Blocks.setItemMeta(BlocksMeta);

        Interactive_BlocksMeta.setDisplayName(ChatColor.WHITE + "Interactive Blocks");
        Interactive_Blocks.setItemMeta(Interactive_BlocksMeta);

        ResourcesMeta.setDisplayName(ChatColor.WHITE + "Resources");
        Resources.setItemMeta(ResourcesMeta);

        FoodMeta.setDisplayName(ChatColor.WHITE + "Food");
        Food.setItemMeta(FoodMeta);

        Mob_DropsMeta.setDisplayName(ChatColor.WHITE + "Mob Drops");
        Mob_Drops.setItemMeta(Mob_DropsMeta);

        PotionsMeta.setDisplayName(ChatColor.WHITE + "Potions");
        Potions.setItemMeta(PotionsMeta);

        PlantsMeta.setDisplayName(ChatColor.WHITE + "Plants");
        Plants.setItemMeta(PlantsMeta);

        Raiding_SuppliesMeta.setDisplayName(ChatColor.WHITE + "Raiding Supplies");
        Raiding_Supplies.setItemMeta(Raiding_SuppliesMeta);

        RedstoneMeta.setDisplayName(ChatColor.WHITE + "Redstone");
        Redstone.setItemMeta(RedstoneMeta);

        CancelMeta.setDisplayName(ChatColor.RED + "Cancel");
        Cancel.setItemMeta(CancelMeta);
        /*
        inv.setItem(1, Blocks);
        inv.setItem(2, Interactive_Blocks);
        inv.setItem(3, Resources);
        inv.setItem(4, Food);
        inv.setItem(5, Mob_Drops);
        inv.setItem(6, Potions);
        inv.setItem(7, Plants);
        inv.setItem(8, Raiding_Supplies);
        inv.setItem(9, Redstone);
        inv.setItem(10, Cancel);

        player.openInventory(inv);
        */
        return true;
    }
}