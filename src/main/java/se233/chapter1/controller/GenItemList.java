package se233.chapter1.controller;

import se233.chapter1.model.character.DamageType;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.BasedEquipment;
import se233.chapter1.model.item.Weapon;

import java.util.ArrayList;

public class GenItemList {
    public static ArrayList<BasedEquipment> setUpItemList() {
        ArrayList<BasedEquipment> itemLists = new ArrayList<BasedEquipment>(5);
        itemLists.add(new Weapon("Sword", 10, DamageType.physical, "assets/sword.png"));
        itemLists.add(new Weapon("Gun", 20, DamageType.physical, "assets/gun.png"));
        itemLists.add(new Weapon("Staff", 30, DamageType.magical, "assets/staff.png"));
        itemLists.add(new Weapon("Axe", 8, DamageType.physical, "assets/axe.png"));
        itemLists.add(new Weapon("Zuck", 40, DamageType.magical, "assets/zuck.png"));
        itemLists.add(new Armor("Shirt", 0, 50, "assets/shirt.png"));
        itemLists.add(new Armor("Armor", 50, 0, "assets/armor.png"));
        itemLists.add(new Armor("Sunglasses", 20, 30, "assets/sunglasses.png"));
        itemLists.add(new Armor("Shield", 40, 10, "assets/shield.png"));
        return itemLists;
    }
}
