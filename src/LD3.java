

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LD3 extends Application {

    private static ImonesBudzietas imone = new ImonesBudzietas("IIIIMONE", 468975236, 852245865);;
    private static Vadovas vadovas = new Vadovas("vad", 5465, "pass");;

    static ImonesBudzietas getImone()
    {
        return imone;
    }

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Langas.fxml"));
        Parent root = loader.load();
        PagrindinisLangas control = (PagrindinisLangas) loader.getController();
        control.stage = primaryStage;
        primaryStage.setTitle("LD5");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        nuskaitytiBiudzeta(imone);
        launch(args);
    }

    /*public static void nuskaitytiDuomenisAsmuoAtsakingasUzSistema(ImonesBudzietas imone) throws FileNotFoundException {
        File s = new File("Uzsistema.txt");
       Scanner sc = new Scanner(s);
        ArrayList<AsmuoAtsakingasUzSistema> a = new ArrayList<AsmuoAtsakingasUzSistema>();
        while(sc.hasNextLine()) {
            String str1;
            str1 = sc.nextLine();
            String[] str2 = str1.split(";");
            AsmuoAtsakingasUzSistema b = new AsmuoAtsakingasUzSistema(str2[0], Integer.parseInt(str2[1]), str2[2]);
            a.add(b);
        }
        imone.setVartotojas(a);
        sc.close();
    }

    public static void nuskaitytiDuomenisKategorijos(ImonesBudzietas imone) throws FileNotFoundException {
        File s = new File("kategorijos.txt");
        Scanner sc = new Scanner(s);
        while(sc.hasNextLine()) {
            String str1;
            str1 = sc.nextLine();
            String[] str2 = str1.split(";");
            ArrayList<String> d = new ArrayList<String>();
            for(int i = 4;i<str2.length;i++)
            {
                d.add(str2[i]);
            }
            Kategorija b = new Kategorija(str2[0], str2[1], Integer.parseInt(str2[2]), Integer.parseInt(str2[3]), d);
            imone.setIslaidos_ir_pajamos(b);
        }
        sc.close();
    }*/

    public static void nuskaitytiBiudzeta(ImonesBudzietas imones) throws FileNotFoundException {
        File s = new File("Budzetass.txt");
        Scanner sc = new Scanner(s);
        String k = sc.nextLine();
        int kain = Integer.parseInt(k);
        imones.setBudzetas(kain);
        sc.close();
    }

    /*public static void irasytiDuomenisAsmuoAtsakingasUzSistema() throws IOException {
        FileWriter fw = new FileWriter("Uzsistema.txt");
        String d;
        ArrayList<AsmuoAtsakingasUzSistema> a = getImone().getVartotojas();
        for(AsmuoAtsakingasUzSistema b : a){
            d = "";
            d = b.getName() + ";" + b.getKodas() + ";" + b.getKodas() + ";";
            fw.write(d+ "\n");
        }
        fw.close();
    }

    public static void irasytiDuomenisKategorijos() throws IOException {
        FileWriter fw = new FileWriter("kategorijos.txt");
        String d;
        ArrayList<Kategorija> a = getImone().getIslaidos_ir_pajamos();
        for(Kategorija b : a){
            d = "";
            d = b.getKategorija() + ";" + b.getPavadinimas() + ";" + b.getKodas() + ";" + b.getKaina() + ";";
            for(String e : b.getSubkategorija())
            {
                d+=e;
                d+=";";
            }
            fw.write(d + "\n");
        }
        fw.close();
    }*/

    public static void irasytiBiudzeta() throws IOException {
        FileWriter fw = new FileWriter("Budzetass.txt");
        fw.write(getImone().getBudzetas() + "\n");
        fw.close();
    }

    public static Kategorija getKategorijaNamePirkimas(ArrayList<Kategorija> k, String a, int kod) {
        return k.stream().filter(Kategorija -> Kategorija.getKodas() == kod && Kategorija.getKategorija().equals(a)).findFirst().orElseGet(null);
    }

    public static Kategorija getKategorijaNamePardavimas(ArrayList<Kategorija> k, String a, int kod) {
        return k.stream().filter(Kategorija -> Kategorija.getKodas() == kod  && Kategorija.getKategorija().equals(a)).findFirst().orElseGet(null);
    }

    public static AsmuoAtsakingasUzSistema getNameAsmuoAtsakingasUzSistema(ArrayList<AsmuoAtsakingasUzSistema> k, int kod) {
        return k.stream().filter(AsmuoAtsakingasUzSistema -> AsmuoAtsakingasUzSistema.getKodas() == kod).findFirst().orElseGet(null);
    }
}
