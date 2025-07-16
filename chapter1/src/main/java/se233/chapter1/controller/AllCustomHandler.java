package se233.chapter1.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import se233.chapter1.Launcher;
import se233.chapter1.model.character.BasedCharacter;
import se233.chapter1.model.character.DamageType;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.BasedEquipment;
import se233.chapter1.model.item.Weapon;

import java.util.ArrayList;

public class AllCustomHandler {
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            unequipWeapon();
            unequipArmor();
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            Launcher.refreshPane();
        }
    }
    // Unequip Button Handler
    public static class UnequipAllHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            unequipWeapon();
            unequipArmor();
            Launcher.refreshPane();
        }
    }
    public static void unequipWeapon() {
        BasedCharacter character = Launcher.getMainCharacter();
        ArrayList<BasedEquipment> allEquipment = Launcher.getAllEquipments();
        if (Launcher.getEquippedWeapon() != null) {
            character.unequipWeapon(Launcher.getEquippedWeapon());
            allEquipment.add(Launcher.getEquippedWeapon());
            Launcher.setEquippedWeapon(null);
            Launcher.setAllEquipments(allEquipment);
        }
    }
    public static void unequipArmor() {
        BasedCharacter character = Launcher.getMainCharacter();
        ArrayList<BasedEquipment> allEquipment = Launcher.getAllEquipments();
        if (Launcher.getEquippedArmor() != null) {
            character.unequipArmor(Launcher.getEquippedArmor());
            allEquipment.add(Launcher.getEquippedArmor());
            Launcher.setEquippedArmor(null);
            Launcher.setAllEquipments(allEquipment);
        }
    }
    public static void onDragDetected(MouseEvent event, BasedEquipment equipment, ImageView imgView) {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);
        db.setContent(content);
        event.consume();
    }
    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type)) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }
    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);

        int pos = -1;
        for (int i=0; i<allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
            }
        }
        if (pos != -1) {
            allEquipments.remove(pos);
        }

        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedCharacter character = Launcher.getMainCharacter();
            if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                Weapon retrievedWeapon = (Weapon) retrievedEquipment;
                if (Launcher.getEquippedWeapon() != null && (character.getType().equals((((Weapon) retrievedEquipment).getDamageType())) || character.getType().equals(DamageType.battlemage))) {
                    allEquipments.add(Launcher.getEquippedWeapon());
                    Launcher.setEquippedWeapon(retrievedWeapon);
                    character.equipWeapon(retrievedWeapon);
                }
                if (character.getType().equals((((Weapon) retrievedEquipment).getDamageType())) || character.getType().equals(DamageType.battlemage)) {
                    Launcher.setEquippedWeapon(retrievedWeapon);
                    character.equipWeapon(retrievedWeapon);
                }
                else {
                    allEquipments.add(retrievedEquipment);
                }
            } else {
                if (Launcher.getEquippedArmor() != null)
                    allEquipments.add(Launcher.getEquippedArmor());
                if (character.getType() != DamageType.battlemage) {
                    Launcher.setEquippedArmor((Armor) retrievedEquipment);
                    character.equipArmor((Armor) retrievedEquipment);
                }
                else {
                    allEquipments.add(retrievedEquipment);
                }
            }
            Launcher.setMainCharacter(character);
            Launcher.setAllEquipments(allEquipments);
            Launcher.refreshPane();
            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size()!=1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + "\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);
    }
    public static void onEquipDone(DragEvent event) {
        Launcher.refreshPane();
    }
}