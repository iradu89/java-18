public class Album extends Book {
    private String paperQuality;

    public Album() {
    }

    public Album(String paperQuality, String name, int numPages) {
        super(name, numPages);
        this.paperQuality = paperQuality;
    }

    public String getPaperQuality() {
        return paperQuality;
    }

    public void setPaperQuality(String paperQuality) {
        this.paperQuality = paperQuality;
    }

    @Override
    public String toString() {
        return this.getName() + " is an Album" + " with " + this.paperQuality + " paper quality and is " + getNumPages() + " pages long";
    }
}
