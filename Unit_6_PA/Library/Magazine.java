package Library;


// magazines class

public class Magazine extends LibraryItem {
    private String publisher;

    public Magazine(String title, String author, String itemID, String publisher) {
        super(title, author, itemID);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return super.toString() + ", Publisher: " + publisher;
    }
}
