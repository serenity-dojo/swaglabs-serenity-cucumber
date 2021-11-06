package swaglabs.model;

import java.util.Optional;

import static java.util.Arrays.stream;

public enum InventoryItem {
    BikeLight("Sauce Labs Bike Light",0),
    TShirt("Sauce Labs Bolt T-Shirt",1),
    Onesie("Sauce Labs Onesie",2),
    TestAllTheThings("Test.allTheThings() T-Shirt (Red)",3),
    Backpack("Sauce Labs Backpack",4),
    Jacket("Sauce Labs Fleece Jacket",5),
    ;

    public final String name;
    public final int id;

    InventoryItem(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static Optional<InventoryItem> called(String name) {
        return stream(values()).filter(item -> item.name.equals(name)).findFirst();
    }
}
