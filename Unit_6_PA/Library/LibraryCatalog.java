package Library;

import java.util.HashMap;
import java.util.Map;

// First we have to keep in mind this Catalog class basically handles the items we add as a librarian, so it is supposed...
// to be thought being capable to remove and put new items in the library.

public class LibraryCatalog<T extends LibraryItem> { //since we extend the LibraryItem class we are capable to use its attributes.
	// creating the list that will contain everything
    private Map<String, T> catalog;

    // now we initialize the list as a new HashMap
    public LibraryCatalog() {
        catalog = new HashMap<>();
    }

    // adding a new item
    public void addItem(T item) {
        catalog.put(item.getItemID(), item);
    }

    // removing an existent item
    public void removeItem(String itemID) {
        if (catalog.containsKey(itemID)) {
            catalog.remove(itemID);
        } else {
            System.out.println("Item not found."); // just implementing a simple handling at removing an item
        }
    }

    // getting details of an item
    public T getItemDetails(String itemID) {
        if (catalog.containsKey(itemID)) {
            return catalog.get(itemID);
        } else {
            System.out.println("Item not found.");
            return null;
        }
    }

    // finally a method to display everything with a simple format
    public void displayCatalog() {
    	System.out.println("================================================================");
        for (T item : catalog.values()) {
            System.out.println(item);
        }
        System.out.println("================================================================");
    }
}