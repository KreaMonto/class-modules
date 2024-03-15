package Library;


// This is the abstract Item in which everything will be based on... so we start by this

public abstract class LibraryItem {
	
	// first, thinking about the library items as a general production of any type of item we could find
	// we set the three main attributes
    private String title;
    private String author;
    private String itemID;

    // with the constructor we assign the variables... 
    
    public LibraryItem(String title, String author, String itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    // we create the getting handler methods. The remotion and modification methods will be handled in the catalog class... 
    // that is because this class represents an item itself as a general object that can exist just because.
    
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getItemID() {
        return itemID;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Item ID: " + itemID;
    }
}
