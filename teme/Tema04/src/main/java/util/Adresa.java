package util;

public class Adresa {

    public Tara getTara() {
        return tara;
    }

    private Tara tara;
    private String localitate;
    private String strada;
    private int numar;

    public Adresa(Tara tara, String localitate, String adresa, int numar) {
        this.tara = tara;
        this.localitate = localitate;
        this.strada = adresa;
        this.numar = numar;
    }
}
