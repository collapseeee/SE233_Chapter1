package se233.chapter1.model.character;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.Weapon;

public class BasedCharacter {
    private static final Logger logger = LogManager.getLogger(BasedCharacter.class);
    protected String name, imgpath;
    protected DamageType type;
    protected Integer fullHp, basedPow, basedDef, basedRes;
    protected Integer hp, power, defense, resistance;
    protected Weapon weapon;
    protected Armor armor;
    public String getName() { return name; }
    public String getImagepath() { return imgpath; }
    public Integer getHp() { return hp; }
    public Integer getFullHp() { return fullHp; }
    public Integer getPower() { return power; }
    public Integer getDefense() { return defense; }
    public Integer getResistance() { return resistance; }
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
        logger.info("Equipped weapon: {}, Power: {}, Type: {}.", weapon.getName(), weapon.getPower(), weapon.getDamageType());
    }
    public void unequipWeapon (Weapon weapon) {
        this.weapon = null;
        this.power -= weapon.getPower();
    }
    public void equipArmor(Armor armor) {
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance = this.basedRes + armor.getResistance();
        logger.info("Equipped armor: {}, Defense: {}, Resistance: {}.", armor.getName(), armor.getDefense(), armor.getResistance());
    }
    public void unequipArmor (Armor armor) {
        this.armor = null;
        this.defense -= armor.getDefense();
        this.resistance -= armor.getResistance();
    }
    @Override
    public String toString() { return name; }
    public DamageType getType() {
        return type; }
}
