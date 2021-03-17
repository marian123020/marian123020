

import java.util.ArrayList;


public final class ImonesBudzietas implements Budzetas {

    private String imones_pav;
    private int imones_kodas;
    private int imones_budzetas;
    private int imones_tel_numeris;
    private ArrayList<Kategorija> Islaidos_ir_pajamos = new ArrayList<Kategorija>();
    private ArrayList<AsmuoAtsakingasUzSistema> vartotojas = new ArrayList<AsmuoAtsakingasUzSistema>();

    public ImonesBudzietas (String name, int imones_kodas, int imones_tel_numeris)
    {
        this.imones_pav = name;
        this.imones_kodas = imones_kodas;
        this.imones_tel_numeris = imones_tel_numeris;
    }

    public int Budzetass() {
        return this.imones_budzetas;
    }

    public ArrayList<Kategorija> getIslaidos_ir_pajamos() {
        return this.Islaidos_ir_pajamos;
    }

    public void setIslaidos_ir_pajamos(Kategorija s) {
        this.Islaidos_ir_pajamos.add(s);
    }

    public void biudzetas(int kaina)
    {
        this.imones_budzetas = imones_budzetas - kaina;
    }

    public void delIslaidos_ir_pajamos(Kategorija s) {
        this.Islaidos_ir_pajamos.remove(s);
    }

    public ArrayList<Kategorija> getsize() {
        return this.Islaidos_ir_pajamos;
    }

    public int Perziureti_Budzeta() {
        return this.imones_budzetas;
    }

    public void setBudzetas(int kaina) {
        this.imones_budzetas = kaina;
    }

    public void addVartotojas(AsmuoAtsakingasUzSistema s)
    {
        this.vartotojas.add(s);
    }

    public void removeVartotojas(AsmuoAtsakingasUzSistema s)
    {
        this.vartotojas.remove(s);
    }

    public int getBudzetas() {
        return this.imones_budzetas;
    }

    public void setVartotojas(ArrayList<AsmuoAtsakingasUzSistema> c)
    {
        this.vartotojas = c;
    }

    public ArrayList<AsmuoAtsakingasUzSistema> getVartotojas()
    {
        return this.vartotojas;
    }
}
