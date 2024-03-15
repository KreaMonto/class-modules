package Library;

// dvds class
public class DVD extends LibraryItem {
    private String rating;

    public DVD(String title, String author, String itemID, String rating) {
        super(title, author, itemID);
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return super.toString() + ", Rating: " + rating;
    }
}
