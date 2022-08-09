package com.supamash.suptest1;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;


public final class Main extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        CM.setupCooldown();
        CustomEnchants.register();

        this.getServer().getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onEntityPickupItemEvent(EntityPickupItemEvent e) {
        if (e.getItem().getItemStack().getType() == Material.DRAGON_EGG) {
            Entity n = e.getEntity();
            String message = "The Dragon Egg Has Been Taken By " + n.getName() + "!!!";
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_PURPLE + message));
                p.playSound(p, Sound.MUSIC_CREDITS, 20, 1);
                p.playSound(p, Sound.ENTITY_PARROT_IMITATE_ENDER_DRAGON, 20, 1);
                new BukkitRunnable() {
                    private int tmp = 0;
                    @Override
                    public void run() {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_PURPLE + message));
                        tmp++;
                        if(tmp == 6) {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(this, 0,4);
            }
            ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
            ItemMeta Helmet = helmet.getItemMeta();
            List<String> lore = new ArrayList<>();
            assert Helmet != null;
            Helmet.setUnbreakable(true);
            Helmet.setDisplayName(ChatColor.DARK_PURPLE + "Mythic Helmet of the Dragons");
            lore.add(ChatColor.BOLD + "Can only be wielded by the best fighter.");
            Helmet.setLore(lore);
            helmet.setItemMeta(Helmet);
            helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
            helmet.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
            helmet.addUnsafeEnchantment(Enchantment.OXYGEN, 4);
            helmet.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
            helmet.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            helmet.getItemMeta().setUnbreakable(true);
            ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
            ItemMeta Chestplate = chestplate.getItemMeta();
            assert Chestplate != null;
            Chestplate.setUnbreakable(true);
            Chestplate.setDisplayName(ChatColor.DARK_PURPLE + "Mythic Chestplate of the Dragons");
            Chestplate.setLore(Collections.singletonList("Can only be wielded by the strongest warrior."));
            chestplate.setItemMeta(Chestplate);
            chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
            chestplate.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
            chestplate.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            chestplate.getItemMeta().setUnbreakable(true);
            ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
            ItemMeta Leggings = leggings.getItemMeta();
            assert Leggings != null;
            Leggings.setUnbreakable(true);
            Leggings.setDisplayName(ChatColor.DARK_PURPLE + "Mythic Leggings of the Dragons");
            Leggings.setLore(Collections.singletonList("Can only be wielded by the most fierce warrior."));
            leggings.setItemMeta(Leggings);
            leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
            leggings.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
            leggings.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            leggings.getItemMeta().setUnbreakable(true);
            ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
            ItemMeta Boots = boots.getItemMeta();
            assert Boots != null;
            Boots.setUnbreakable(true);
            Boots.setDisplayName(ChatColor.DARK_PURPLE + "Mythic Boots of the Dragons");
            Boots.setLore(Collections.singletonList("Can only be picked up by the person with pickup priority."));
            boots.setItemMeta(Boots);
            boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
            boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 4);
            boots.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 4);
            boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 5);
            boots.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
            boots.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            boots.getItemMeta().setUnbreakable(true);
        Objects.requireNonNull(e.getEntity().getEquipment()).setHelmet(helmet);
        e.getEntity().getEquipment().setChestplate(chestplate);
        e.getEntity().getEquipment().setLeggings(leggings);
        e.getEntity().getEquipment().setBoots(boots);
        }
        if (e.getItem().getItemStack().getType() == Material.TIPPED_ARROW) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void OnPlayerDropItemEvent(PlayerDropItemEvent item) {
        if (item.getItemDrop().getItemStack().getType() == Material.DRAGON_EGG) {
            if (item.getPlayer().getGameMode() == GameMode.CREATIVE)
                return;
            item.setCancelled(true);
        }

    }

    @EventHandler
    public void OnPlayerDeathEvent (PlayerDeathEvent e) {
        Objects.requireNonNull(Objects.requireNonNull(e.getEntity().getPlayer()).getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
    }

    @EventHandler
    public void OnEntityAttackEntity (EntityDamageByEntityEvent e) {
        if (e.getDamager().getType().equals(EntityType.PLAYER) && e.getEntity().getType().equals(EntityType.PLAYER)) {
            if (e.getDamager().getType().equals(EntityType.PLAYER)) {
                Player p = (Player) e.getDamager();
                if (!p.getInventory().contains(Material.DRAGON_EGG)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getLore()).contains(ChatColor.DARK_GRAY + "Withering")) {
                        if (e.getEntity().getType().equals(EntityType.PLAYER)) {
                            Player pl = (Player) e.getEntity();
                            pl.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 3));
                        }
                    }
                }
                if (Objects.requireNonNull(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getLore()).contains(ChatColor.GREEN + "Dual Wielding")) {
                    if (p.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)) {
                        if (p.getInventory().getItemInOffHand().getType().equals(Material.DIAMOND_SWORD)) {
                            double Damage = e.getDamage();
                            double FinalDamage = Damage * 1.25;
                            e.setDamage(FinalDamage);
                        }
                    }
                    if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)) {
                        if (p.getInventory().getItemInOffHand().getType().equals(Material.NETHERITE_SWORD)) {
                            double Damage = e.getDamage();
                            double FinalDamage = Damage * 1.25;
                            e.setDamage(FinalDamage);
                        }
                    }
                } else if (Objects.requireNonNull(Objects.requireNonNull(p.getInventory().getItemInOffHand().getItemMeta()).getLore()).contains(ChatColor.GREEN + "Dual Wielding")) {
                    if (p.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)) {
                        if (p.getInventory().getItemInOffHand().getType().equals(Material.DIAMOND_SWORD)) {
                            double Damage = e.getDamage();
                            double FinalDamage = Damage * 1.25;
                            e.setDamage(FinalDamage);
                        }
                    }
                    if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)) {
                        if (p.getInventory().getItemInOffHand().getType().equals(Material.NETHERITE_SWORD)) {
                            double Damage = e.getDamage();
                            double FinalDamage = Damage * 1.25;
                            e.setDamage(FinalDamage);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void OnInventoryClickEvent(InventoryClickEvent item) {
        if (item.getCurrentItem() == null) return;
        if (item.getCursor() == null) return;
        if (Objects.requireNonNull(item.getCurrentItem()).getType() == Material.DRAGON_EGG) {
            if (item.getWhoClicked().getGameMode() == GameMode.CREATIVE)
                return;
            item.setResult(Event.Result.DENY);
        }
        if (item.getCurrentItem().getType() == Material.TIPPED_ARROW) {
            item.setResult(Event.Result.DENY);
        }
        if (!item.getWhoClicked().getInventory().contains(Material.DRAGON_EGG)) {
            if (Objects.requireNonNull(item.getCursor()).getType().equals(Material.WITHER_SKELETON_SKULL)) {
                if (Objects.requireNonNull(item.getCurrentItem().getItemMeta()).hasLore()) {
                    if (Objects.requireNonNull(item.getCurrentItem().getItemMeta().getLore()).contains(ChatColor.DARK_GRAY + "Withering")) {
                        item.setCancelled(true);
                        item.getWhoClicked().sendMessage(ChatColor.DARK_GRAY + "You already have the Withering Enchantment");
                    }
                } else if (item.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
                    item.setCancelled(true);
                    ItemStack itemstack = item.getCurrentItem();
                    ItemMeta itemmeta = itemstack.getItemMeta();
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.DARK_GRAY + "Withering");
                    itemmeta.setLore(lore);
                    itemstack.setItemMeta(itemmeta);
                    itemstack.addUnsafeEnchantment(CustomEnchants.HEALTH, 1);
                    item.getWhoClicked().sendMessage(ChatColor.DARK_GRAY + "Added Withering Enchantment!");
                    item.setCurrentItem(itemstack);
                    item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                } else if (item.getCurrentItem().getType().equals(Material.NETHERITE_SWORD)) {
                    item.setCancelled(true);
                    ItemStack itemstack = item.getCurrentItem();
                    ItemMeta itemmeta = itemstack.getItemMeta();
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.DARK_GRAY + "Withering");
                    itemmeta.setLore(lore);
                    itemstack.setItemMeta(itemmeta);
                    itemstack.addUnsafeEnchantment(CustomEnchants.HEALTH, 1);
                    item.getWhoClicked().sendMessage(ChatColor.DARK_GRAY + "Added Withering Enchantment!");
                    item.setCurrentItem(itemstack);
                    item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                }
            }
        /*
        if (item.getCursor().getType().equals(Material.PLAYER_HEAD)) {
            if (item.getSlotType().equals(InventoryType.SlotType.ARMOR)) {
                if (Objects.requireNonNull(item.getCurrentItem().getItemMeta()).hasLore()) {
                    if (Objects.requireNonNull(item.getCurrentItem().getItemMeta().getLore()).contains(ChatColor.YELLOW + "Health II")) {
                        item.setCancelled(true);
                        item.getWhoClicked().sendMessage(ChatColor.YELLOW + "You have max health enchantment already");
                    } else if (item.getCurrentItem().getItemMeta().getLore().contains(ChatColor.YELLOW + "Health I")) {
                        if (item.getCurrentItem().getType().equals(Material.TURTLE_HELMET) || item.getCurrentItem().getType().equals(Material.LEATHER_HELMET) || item.getCurrentItem().getType().equals(Material.NETHERITE_HELMET) || item.getCurrentItem().getType().equals(Material.DIAMOND_HELMET) || item.getCurrentItem().getType().equals(Material.GOLDEN_HELMET) || item.getCurrentItem().getType().equals(Material.IRON_HELMET) || item.getCurrentItem().getType().equals(Material.CHAINMAIL_HELMET)) {
                            item.setCancelled(true);
                            ItemStack itemstack = item.getCurrentItem();
                            ItemMeta itemmeta = itemstack.getItemMeta();
                            AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 1,
                                    AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
                            List<String> lore = new ArrayList<>();
                            lore.add(ChatColor.YELLOW + "Health II");
                            itemmeta.setLore(lore);
                            itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);
                            itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                            itemstack.setItemMeta(itemmeta);
                            item.getWhoClicked().sendMessage(ChatColor.YELLOW + "Added Health II Enchantment!");
                            item.setCurrentItem(itemstack);
                            item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                        } else if (item.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.NETHERITE_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.GOLDEN_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.IRON_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.CHAINMAIL_CHESTPLATE)) {
                            item.setCancelled(true);
                            ItemStack itemstack = item.getCurrentItem();
                            ItemMeta itemmeta = itemstack.getItemMeta();
                            AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 1,
                                    AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
                            List<String> lore = new ArrayList<>();
                            lore.add(ChatColor.YELLOW + "Health II");
                            itemmeta.setLore(lore);
                            itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);
                            itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                            itemstack.setItemMeta(itemmeta);
                            item.getWhoClicked().sendMessage(ChatColor.YELLOW + "Added Health II Enchantment!");
                            item.setCurrentItem(itemstack);
                            item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                        } else if (item.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS) || item.getCurrentItem().getType().equals(Material.NETHERITE_LEGGINGS) || item.getCurrentItem().getType().equals(Material.DIAMOND_LEGGINGS) || item.getCurrentItem().getType().equals(Material.GOLDEN_LEGGINGS) || item.getCurrentItem().getType().equals(Material.IRON_LEGGINGS) || item.getCurrentItem().getType().equals(Material.CHAINMAIL_LEGGINGS)) {
                            item.setCancelled(true);
                            ItemStack itemstack = item.getCurrentItem();
                            ItemMeta itemmeta = itemstack.getItemMeta();
                            AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 1,
                                    AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
                            List<String> lore = new ArrayList<>();
                            lore.add(ChatColor.YELLOW + "Health II");
                            itemmeta.setLore(lore);
                            itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);
                            itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                            itemstack.setItemMeta(itemmeta);
                            item.getWhoClicked().sendMessage(ChatColor.YELLOW + "Added Health II Enchantment!");
                            item.setCurrentItem(itemstack);
                            item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                        } else if (item.getCurrentItem().getType().equals(Material.LEATHER_BOOTS) || item.getCurrentItem().getType().equals(Material.NETHERITE_BOOTS) || item.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS) || item.getCurrentItem().getType().equals(Material.GOLDEN_BOOTS) || item.getCurrentItem().getType().equals(Material.IRON_BOOTS) || item.getCurrentItem().getType().equals(Material.CHAINMAIL_BOOTS)) {
                            item.setCancelled(true);
                            ItemStack itemstack = item.getCurrentItem();
                            ItemMeta itemmeta = itemstack.getItemMeta();
                            AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 1,
                                    AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
                            List<String> lore = new ArrayList<>();
                            lore.add(ChatColor.YELLOW + "Health II");
                            itemmeta.setLore(lore);
                            itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);
                            itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                            itemstack.setItemMeta(itemmeta);
                            item.getWhoClicked().sendMessage(ChatColor.YELLOW + "Added Health II Enchantment!");
                            item.setCurrentItem(itemstack);
                            item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                        }
                    }
                } else {
                    if (item.getCurrentItem().getType().equals(Material.TURTLE_HELMET) || item.getCurrentItem().getType().equals(Material.LEATHER_HELMET) || item.getCurrentItem().getType().equals(Material.NETHERITE_HELMET) || item.getCurrentItem().getType().equals(Material.DIAMOND_HELMET) || item.getCurrentItem().getType().equals(Material.GOLDEN_HELMET) || item.getCurrentItem().getType().equals(Material.IRON_HELMET) || item.getCurrentItem().getType().equals(Material.CHAINMAIL_HELMET)) {
                        item.setCancelled(true);
                        ItemStack itemstack = item.getCurrentItem();
                        ItemMeta itemmeta = itemstack.getItemMeta();
                        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 1,
                                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.YELLOW + "Health I");
                        itemmeta.setLore(lore);
                        itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);
                        itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        itemstack.setItemMeta(itemmeta);
                        item.getWhoClicked().sendMessage(ChatColor.YELLOW + "Added Health I Enchantment!");
                        item.setCurrentItem(itemstack);
                        item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                    } else if (item.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.NETHERITE_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.GOLDEN_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.IRON_CHESTPLATE) || item.getCurrentItem().getType().equals(Material.CHAINMAIL_CHESTPLATE)) {
                        item.setCancelled(true);
                        ItemStack itemstack = item.getCurrentItem();
                        ItemMeta itemmeta = itemstack.getItemMeta();
                        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 1,
                                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.YELLOW + "Health I");
                        itemmeta.setLore(lore);
                        itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);
                        itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        itemstack.setItemMeta(itemmeta);
                        item.getWhoClicked().sendMessage(ChatColor.YELLOW + "Added Health I Enchantment!");
                        item.setCurrentItem(itemstack);
                        item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                    } else if (item.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS) || item.getCurrentItem().getType().equals(Material.NETHERITE_LEGGINGS) || item.getCurrentItem().getType().equals(Material.DIAMOND_LEGGINGS) || item.getCurrentItem().getType().equals(Material.GOLDEN_LEGGINGS) || item.getCurrentItem().getType().equals(Material.IRON_LEGGINGS) || item.getCurrentItem().getType().equals(Material.CHAINMAIL_LEGGINGS)) {
                        item.setCancelled(true);
                        ItemStack itemstack = item.getCurrentItem();
                        ItemMeta itemmeta = itemstack.getItemMeta();
                        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 1,
                                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.YELLOW + "Health I");
                        itemmeta.setLore(lore);
                        itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);
                        itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        itemstack.setItemMeta(itemmeta);
                        item.getWhoClicked().sendMessage(ChatColor.YELLOW + "Added Health I Enchantment!");
                        item.setCurrentItem(itemstack);
                        item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                    } else if (item.getCurrentItem().getType().equals(Material.LEATHER_BOOTS) || item.getCurrentItem().getType().equals(Material.NETHERITE_BOOTS) || item.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS) || item.getCurrentItem().getType().equals(Material.GOLDEN_BOOTS) || item.getCurrentItem().getType().equals(Material.IRON_BOOTS) || item.getCurrentItem().getType().equals(Material.CHAINMAIL_BOOTS)) {
                        item.setCancelled(true);
                        ItemStack itemstack = item.getCurrentItem();
                        ItemMeta itemmeta = itemstack.getItemMeta();
                        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 1,
                                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.YELLOW + "Health I");
                        itemmeta.setLore(lore);
                        itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);
                        itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        itemstack.setItemMeta(itemmeta);
                        item.getWhoClicked().sendMessage(ChatColor.YELLOW + "Added Health I Enchantment!");
                        item.setCurrentItem(itemstack);
                        item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                    }
                }
            }
        }
        */
            if (Objects.requireNonNull(item.getCursor()).getType().equals(Material.DIAMOND_SWORD)) {
                if (Objects.requireNonNull(item.getCurrentItem().getItemMeta()).hasLore()) {
                    if (Objects.requireNonNull(item.getCurrentItem().getItemMeta().getLore()).contains(ChatColor.GREEN + "Dual Wielding")) {
                        item.setCancelled(true);
                        item.getWhoClicked().sendMessage(ChatColor.GREEN + "You already the Dual Wielding Enchantment");
                    }
                } else if (item.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
                    item.setCancelled(true);
                    ItemStack itemstack = item.getCurrentItem();
                    ItemMeta itemmeta = itemstack.getItemMeta();
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GREEN + "Dual Wielding");
                    itemmeta.setLore(lore);
                    itemstack.setItemMeta(itemmeta);
                    itemstack.addUnsafeEnchantment(CustomEnchants.HEALTH, 1);
                    item.getWhoClicked().sendMessage(ChatColor.GREEN + "Added Dual Wielding Enchantment!");
                    item.setCurrentItem(itemstack);
                    item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                }
            } else if (Objects.requireNonNull(item.getCursor()).getType().equals(Material.NETHERITE_SWORD)) {
                if (Objects.requireNonNull(item.getCurrentItem().getItemMeta()).hasLore()) {
                    if (Objects.requireNonNull(item.getCurrentItem().getItemMeta().getLore()).contains(ChatColor.GREEN + "Dual Wielding")) {
                        item.setCancelled(true);
                        item.getWhoClicked().sendMessage(ChatColor.GREEN + "You already have the max level of Dual Wielding Enchantment");
                    }
                } else if (item.getCurrentItem().getType().equals(Material.NETHERITE_SWORD)) {
                    item.setCancelled(true);
                    ItemStack itemstack = item.getCurrentItem();
                    ItemMeta itemmeta = itemstack.getItemMeta();
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GREEN + "Dual Wielding");
                    itemmeta.setLore(lore);
                    itemstack.setItemMeta(itemmeta);
                    itemstack.addUnsafeEnchantment(CustomEnchants.HEALTH, 1);
                    item.getWhoClicked().sendMessage(ChatColor.GREEN + "Added Dual Wielding Enchantment!");
                    item.setCurrentItem(itemstack);
                    item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                }
            }
            if (item.getWhoClicked().getName().equals("Supamash") || item.getWhoClicked().getName().equals("popper_mic_poper")) {
                if (Objects.requireNonNull(item.getCursor()).getType().equals(Material.NETHER_STAR)) {
                    if (Objects.requireNonNull(item.getCurrentItem().getItemMeta()).hasLore()) {
                        if (Objects.requireNonNull(item.getCurrentItem().getItemMeta().getLore()).contains(ChatColor.AQUA + "Skull Armada")) {
                            item.setCancelled(true);
                            item.getWhoClicked().sendMessage(ChatColor.AQUA + "You already have the Skull Armada Enchantment");
                        }
                    } else if (item.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
                        item.setCancelled(true);
                        ItemStack itemstack = item.getCurrentItem();
                        ItemMeta itemmeta = itemstack.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.AQUA + "Skull Armada");
                        itemmeta.setLore(lore);
                        itemstack.setItemMeta(itemmeta);
                        itemstack.addUnsafeEnchantment(CustomEnchants.HEALTH, 1);
                        item.getWhoClicked().sendMessage(ChatColor.AQUA + "Added Skull Armada Enchantment!");
                        item.setCurrentItem(itemstack);
                        item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                    } else if (item.getCurrentItem().getType().equals(Material.NETHERITE_SWORD)) {
                        item.setCancelled(true);
                        ItemStack itemstack = item.getCurrentItem();
                        ItemMeta itemmeta = itemstack.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.AQUA + "Skull Armada");
                        itemmeta.setLore(lore);
                        itemstack.setItemMeta(itemmeta);
                        itemstack.addUnsafeEnchantment(CustomEnchants.HEALTH, 1);
                        item.getWhoClicked().sendMessage(ChatColor.AQUA + "Added Skull Armada Enchantment!");
                        item.setCurrentItem(itemstack);
                        item.getCursor().setAmount(item.getCursor().getAmount() - 1);
                    }
                }
            }
        }
    }
    @EventHandler
    public void OnBlockPlaceEvent(BlockPlaceEvent item) {
        if (item.getBlockPlaced().getType() == Material.DRAGON_EGG) {
            if (item.getPlayer().getGameMode() == GameMode.CREATIVE)
                return;
            item.setCancelled(true);
        }
    }
    @EventHandler
    public void OnPlayerPickupArrowEvent(PlayerPickupArrowEvent item) {
        if (item.getItem().getItemStack().getType() == Material.TIPPED_ARROW) {
            item.setCancelled(true);
        }
    }
    @EventHandler
    public void craftItem(PrepareItemCraftEvent e) {
        if (e.getRecipe() == null)
            return;
        Material itemType = Objects.requireNonNull(e.getRecipe()).getResult().getType();
        if(itemType==Material.TIPPED_ARROW||itemType==Material.END_CRYSTAL||itemType==Material.RESPAWN_ANCHOR) {
            e.getInventory().setResult(new ItemStack(Material.AIR));
            for(HumanEntity he:e.getViewers()) {
                if(he instanceof Player) {
                    he.sendMessage(ChatColor.DARK_RED + "Bro did you even read the rules?????????????");
                }
            }
        }
    }
    @EventHandler
    public void OnPlayerInteractEvent(PlayerInteractEvent e) {
        if (!(e.getItem() == null)) {
            if (!e.getPlayer().getInventory().contains(Material.DRAGON_EGG)) {
                if (e.getItem().getItemMeta().getLore() == null)
                    return;
            }
            if (e.getPlayer().getInventory().contains(Material.DRAGON_EGG)) {
                if (Objects.requireNonNull(e.getItem()).getType() == Material.NETHERITE_SWORD) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR) {
                        World w = e.getPlayer().getWorld();
                        Player p = e.getPlayer();
                        if (CM.checkCooldown(e.getPlayer())) {
                            Block b = p.getTargetBlock(null, 100);
                            Location l = b.getLocation();
                            w.strikeLightning(l);
                            w.createExplosion(l, 5, false, false);
                            e.getPlayer().sendMessage(ChatColor.BLUE + "Used Lightning Strike!");
                            CM.setCooldown(e.getPlayer(), 15);
                        } else {
                            e.getPlayer().sendMessage("You cannot do this for another " + CM.getCooldown(e.getPlayer()) + " seconds");
                        }
                    }
                }
                if (e.getItem().getType() == Material.NETHERITE_AXE) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR) {
                        World w = e.getPlayer().getWorld();
                        Player p = e.getPlayer();
                        if (CM.checkCooldown(e.getPlayer())) {
                            for (Entity en : p.getNearbyEntities(10, 10, 10)) {
                                LivingEntity pl = (LivingEntity) en;
                                Vector v = pl.getLocation().getDirection();
                                pl.setVelocity(v.multiply(-5).setY(1).setZ(2));
                                w.playSound(pl.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                                e.getPlayer().sendMessage("Used Dragon's Roar!");
                                CM.setCooldown(e.getPlayer(), 15);
                            }
                        } else {
                            e.getPlayer().sendMessage("You cannot do this for another " + CM.getCooldown(e.getPlayer()) + " seconds");
                        }
                    }
                }
                if (e.getItem().getType() == Material.ENDER_EYE) {
                    if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                        World w = e.getPlayer().getWorld();
                        Player p = e.getPlayer();
                        if (CM.checkCooldown(e.getPlayer())) {
                            Vector v = p.getLocation().getDirection();
                            p.setVelocity(v.multiply(-5).setY(2));
                            Block b = p.getTargetBlock(null, 5);
                            Location l = b.getLocation();
                            w.createExplosion(l, 1, false, false);
                            CM.setCooldown(e.getPlayer(), 10);
                        } else {
                            e.getPlayer().sendMessage("You cannot do this for another " + CM.getCooldown(e.getPlayer()) + " seconds");
                        }
                    }
                }
            }
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (e.getItem().getType().equals(Material.NETHER_STAR)) {
                    if (e.getPlayer().getName().equals("Ow3nV2") || e.getPlayer().getName().equals("Supamash")) {
                        if (CM.checkCooldown(e.getPlayer())) {
                            Player p = e.getPlayer();
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 3, false, false));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 3, false, false));

                            CM.setCooldown(e.getPlayer(), 360);
                        } else {
                            e.getPlayer().sendMessage("You cannot do this for another " + CM.getCooldown(e.getPlayer()) + " seconds");
                        }
                    }
                } else if (e.getItem().getType().equals(Material.DIAMOND_SWORD) || e.getItem().getType().equals(Material.NETHERITE_SWORD)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(e.getItem().getItemMeta()).getLore()).contains(ChatColor.AQUA + "Skull Armada")) {
                        if (e.getPlayer().getName().equals("popper_mic_poper") || e.getPlayer().getName().equals("Supamash")) {
                            if (CM.checkCooldown(e.getPlayer())) {
                                Player p = e.getPlayer();
                                new BukkitRunnable() {
                                    private int tmp = 0;

                                    @Override
                                    public void run() {
                                        WitherSkull ws = p.launchProjectile(WitherSkull.class);
                                        WitherSkull wsr = p.launchProjectile(WitherSkull.class);
                                        WitherSkull wsrt = p.launchProjectile(WitherSkull.class);
                                        ws.setShooter(p);
                                        ws.setYield(0);
                                        ws.setBounce(true);
                                        ws.setVelocity(p.getLocation().getDirection().multiply(2));
                                        ws.setGlowing(true);
                                        wsrt.setShooter(p);
                                        wsrt.setYield(0);
                                        wsrt.setBounce(true);
                                        wsrt.setVelocity(p.getLocation().getDirection().multiply(2));
                                        wsrt.setGlowing(true);
                                        wsr.setShooter(p);
                                        wsr.setYield(0);
                                        wsr.setBounce(true);
                                        wsr.setVelocity(p.getLocation().getDirection().multiply(2));
                                        wsr.setGlowing(true);
                                        tmp++;
                                        if (tmp == 6) {
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(this, 0, 4);

                                CM.setCooldown(e.getPlayer(), 20);
                            } else {
                                e.getPlayer().sendMessage("You cannot do this for another " + CM.getCooldown(e.getPlayer()) + " seconds");
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void OnPlayerMoveEvent(PlayerMoveEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AXOLOTL_BUCKET) {
            e.getPlayer().sendMessage("Depression & ItzDax");
        }
    }
}
