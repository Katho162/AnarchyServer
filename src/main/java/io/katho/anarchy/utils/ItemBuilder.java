package io.katho.anarchy.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Consumer;

public class ItemBuilder {
    /**
     * @author ssk
     * Just a simple class to turns ItemStack objects easier
     */

    private ItemStack item;

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder(Material type){
        this(new ItemStack(type));
    }

    public ItemBuilder(Material type , int amount){
        this(new ItemStack(type , 1));
    }

    public ItemBuilder changeItem(Consumer<ItemStack> consumer){
        consumer.accept(item);
        return this;
    }

    public ItemBuilder changeItemMeta(Consumer<ItemMeta> consumer){
        ItemMeta itemMeta = item.getItemMeta();
        consumer.accept(itemMeta);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder name(String name){
        return changeItemMeta(it -> it.setDisplayName(name));
    }

    public ItemBuilder setLore(String... lore){
        return changeItemMeta(it -> it.setLore(Arrays.asList(lore)));
    }

    public ItemStack build(){
        return item;
    }

}
