import Library.Book;
import Library.DVD;
import Library.LibraryCatalog;
import Library.LibraryItem;
import Library.Magazine; //importing everything to start

// now here we will apply all the logic from the previous parts

public class Main {
    public static void main(String[] args) {
        LibraryCatalog<LibraryItem> catalog = new LibraryCatalog<>();

        // Now we will add items to the catalog
        catalog.addItem(new Book("The Great Gatsby", "F. Scott Fitzgerald", "12345", "Fiction"));
        catalog.addItem(new DVD("The Godfather", "Francis Ford Coppola", "67890", "R"));
        catalog.addItem(new Magazine("National Geographic", "Editor", "01234", "National Geographic Society"));

        
        //testing!!!---- 
        // Displaying the library catalog
        catalog.displayCatalog();
        
        // this way we can remove an item
        catalog.removeItem("12345");

        // Displaying it again
        catalog.displayCatalog();

        // Getting details from an item
        LibraryItem item = catalog.getItemDetails("67890");
        System.out.println(item);
    }
}