public class Nuvela extends Book {
    private String type;

    public Nuvela() {
    }

    public Nuvela(String type, String name, int numPages) {
        super(name, numPages);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.getName() + " is a novella" + " of the " + getType() + " genre with " + getNumPages() + " pages";
    }
}
