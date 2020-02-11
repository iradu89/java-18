package composition;

public class Camera {
    private String culoare;
    private int marime;

    public Camera(){
        this.culoare = "alb";
        this.marime = 10;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public int getMarime() {
        return marime;
    }

    public void setMarime(int marime) {
        this.marime = marime;
    }
}
