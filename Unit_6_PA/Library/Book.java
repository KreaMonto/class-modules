package Library;


// the books class to add books ... obviously 
public class Book extends LibraryItem {
    private String genre;

    public Book(String title, String author, String itemID, String genre) {
        super(title, author, itemID);
        this.genre = genre;
    }

    public String getGenre() {
return genre;
    }

    @Override
    public String toString() {
        return super.toString() + ", Genre: " + genre;
    }
}
