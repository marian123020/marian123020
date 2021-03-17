
import java.util.ArrayList;

public class Kategorija {

    private String kategorija;
    private String pavadinimas;
    private int kodas;
    private int kaina;
    private ArrayList<String> subkategorija = new ArrayList<String>();

    public Kategorija()
    {

    }
    public Kategorija(String kategorija, String pavadinimas, int kodas, int kaina, ArrayList<String> subkategorija) {
        this.kategorija = kategorija;
        this.pavadinimas = pavadinimas;
        this.kodas = kodas;
        this.kaina = kaina;
        this.subkategorija = subkategorija;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public int getKodas() {
        return kodas;
    }

    public String getKategorija() {
        return kategorija;
    }

    public int getKaina() {
        return kaina;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public void setKodas(int kodas) {
        this.kodas = kodas;
    }

    public void setKaina(int kain) {
        kaina = kain;
    }

    public ArrayList<String> getSubkategorija() {
        return subkategorija;
    }

    public void setSubkategorija(ArrayList<String> a) {
        subkategorija = a;
    }
    public String toString() {
        return "Kategorija:" +  this.kategorija + ", Pavadinimas:" + this.pavadinimas + ", Kodas:" + this.kodas + ", Kaina" + this.kaina + ", Subkategorija:" + this.subkategorija;
    }
}
