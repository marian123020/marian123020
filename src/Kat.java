
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


@Controller
public class Kat {
    ImonesBudzietas imone = LD3.getImone();
    Gson parser = new Gson();
    @RequestMapping(value = "Kategorija/info", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getKategorijaPagalKodas(@RequestParam("Kategorija") String kategorija ,@RequestParam("pav") String pav ,@RequestParam("Kodas") String kodas) throws SQLException, ClassNotFoundException {
        GsonBuilder gson = new GsonBuilder();
        Kategorija kategor = new Kategorija();
        Kategorija katego = new Kategorija();
        if (pav == "" || kodas == "" || kategorija == "") {
            return "Nera tokios kategorijos";
        } else {
            int kain = 0;

            Kategorija kateg = new Kategorija();
            DatebaseHandler dbHandler = new DatebaseHandler();
            kateg.setKategorija(kategorija);
            kateg.setPavadinimas(pav);
            kateg.setKodas(Integer.parseInt(kodas));
            ResultSet result = null;
            try {
                result = dbHandler.ArYrKategorija(kateg);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int temp = 0;
            if (result.next()) {
                if (kategorija == "islaidos") {
                    imone.biudzetas(kain * -1);
                } else if (kategorija == "pajamos") {
                    imone.biudzetas(kain);
                }
                try {
                    temp++;
                    gson.registerTypeAdapter(Kategorija.class, new GSONKat());
                    parser = gson.create();
                    String a = result.getString(1);
                    String b = result.getString(2);
                    int c = result.getInt(3);
                    int d = result.getInt(4);
                    String e =  result.getString(5);
                    String[] f = e.split(";");
                    ArrayList<String> sub = new ArrayList<>();
                    for(String n : f)
                    {
                        sub.add(n);
                    }
                    katego = new Kategorija(a, b, c, d, sub);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if(temp==0)
            {
                return "Nera tokios kategorijos";
            }
        }
        return parser.toJson(katego);
    }
    @RequestMapping(value = "Kategorija/All")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllKategorija() throws SQLException, ClassNotFoundException {

        DatebaseHandler Handler= new DatebaseHandler();
        ResultSet result = Handler.KategorijaVisasSarasas();
        ArrayList<Kategorija> All = new ArrayList<>();
        while(result.next())
        {
            String a = result.getString(1);
            String b = result.getString(2);
            int c = result.getInt(3);
            int d = result.getInt(4);
            String e =  result.getString(5);
            String[] f = e.split(";");
            ArrayList<String> sub = new ArrayList<>();
            for(String n : f)
            {
                sub.add(n);
            }
            Kategorija as = new Kategorija(a, b, c, d, sub);
            All.add(as);
        }

        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Kategorija.class, new GSONKat());
        Type KategList = new TypeToken<ArrayList<Kategorija>>() {
        }.getType();
        gson.registerTypeAdapter(KategList, new AllGSONKat());
        parser = gson.create();

        return parser.toJson(All);
    }

    @RequestMapping(value = "Kategorija/prideti", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String PridetiKategorija(@RequestParam("Kategorija") String kategorija ,@RequestParam("pav") String pav ,@RequestParam("Kodas") String kodas, @RequestParam("Kaina") String kaina, @RequestParam("Subkategorija") String subkategorija) throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        if (pav == "" || kaina == "" || kodas == "" || kategorija == "") {
            return "Iveskite visus duomenys";
        } else {
            boolean KategorijaArYra = false;
            Kategorija kategorijaa = new Kategorija();
            kategorijaa.setKategorija(kategorija);
            kategorijaa.setPavadinimas(pav);
            kategorijaa.setKodas(Integer.parseInt(kodas));
            ResultSet result = null;
            try {
                result = dbHandler.ArYrKategorija(kategorijaa);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int temp = 0;
            while (true) {
                try {
                    if (!result.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                temp++;
            }
            if (temp > 0) {

                KategorijaArYra = true;
                return "Tokia " + kategorija + " su pavadinimu:" + pav + " ir kodu:" + kodas + " jau yra.";
            }
            if (KategorijaArYra == false) {
                String sa = subkategorija;
                String[] subklass = sa.split(";");
                ArrayList<String> subklas = new ArrayList<String>();
                for (String a : subklass) {
                    subklas.add(a);
                }
                if (kategorija == "islaidos") {
                    imone.biudzetas(Integer.parseInt(kaina));
                } else if (kategorija == "pajamos") {

                    imone.biudzetas(Integer.parseInt(kaina) * -1);
                }
                Kategorija s = new Kategorija(kategorija, pav, Integer.parseInt(kodas), Integer.parseInt(kaina), subklas);
                imone.setIslaidos_ir_pajamos(s);
                try {
                    dbHandler.KategorijaPrideti(s);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Paslauga prideta";
    }
    @RequestMapping(value = "Kategorija/pasalinti", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String PasalintiKategorija(@RequestParam("Kategorija") String kategorija ,@RequestParam("pav") String pav ,@RequestParam("Kodas") String kodas) throws SQLException, ClassNotFoundException { /*url/libraryInfo?name=Pilies*/
        DatebaseHandler dbHandler = new DatebaseHandler();
        if (pav == "" || kodas == "" || kategorija == "") {
            return "Iveskite visus duomenys";
        } else {
            int kain = 0;
            if (kategorija == "islaidos") {
                //imone.biudzetas(kain * -1);
            } else if (kategorija == "pajamos") {
                //imone.biudzetas(kain);
            }
            Kategorija kateg = new Kategorija();
            dbHandler = new DatebaseHandler();
            kateg.setKategorija(kategorija);
            kateg.setPavadinimas(pav);
            kateg.setKodas(Integer.parseInt(kodas));
            ResultSet result = null;
            try {
                result = dbHandler.ArYrKategorija(kateg);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int temp = 0;
            while (true) {
                try {
                    if (!result.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    kain = result.getInt(4);
                    temp++;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (temp > 0) {
                if (kategorija == "islaidos") {
                    imone.biudzetas(kain * -1);
                } else if (kategorija == "pajamos") {
                    imone.biudzetas(kain);
                }
                try {
                    dbHandler.KategorijaSalinimas(kateg);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //imone.getIslaidos_ir_pajamos().remove(kateg);
            }
        }
        return "Paslauga pasalinta";
    }
    @RequestMapping(value = "Kategorija/nameupdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String UpdateNameKategorija(@RequestParam("Kategorija") String kategorija, @RequestParam("Esantiskodas") String esantiskodas, @RequestParam("Pav") String pav) throws SQLException, ClassNotFoundException { /*url/libraryInfo?name=Pilies*/
        DatebaseHandler dbHandler = new DatebaseHandler();
        if(esantiskodas == "" || pav == "" || kategorija == "")
        {
            return "Nurodykite paslaugos koda, pavadinima ir kategorija kuri norite pakeisti";
        }
        else {
            try {
                dbHandler.UpdateNameKategorija(kategorija, pav, Integer.parseInt(esantiskodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return "Pavadinimas pakeistas";
        }
    }
    @RequestMapping(value = "Kategorija/kodasupdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String UpdateKodasKategorija(@RequestParam("Kategorija") String kategorija, @RequestParam("Esantiskodas") String esantiskodas, @RequestParam("kodas") String kodas) throws SQLException, ClassNotFoundException { /*url/libraryInfo?name=Pilies*/
        DatebaseHandler dbHandler = new DatebaseHandler();
        if(esantiskodas == "" || kodas == "" || kategorija == "")
        {
            return "Nurodykite paslaugos koda, koda ir kategorija kuri norite pakeisti";
        }
        else {
            try {
                dbHandler.UpdateKodasKategorija(kategorija, Integer.parseInt(kodas), Integer.parseInt(esantiskodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return "Kodas pakeistas";
        }
    }
    @RequestMapping(value = "Kategorija/kainaupdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String UpdateKainaKategorija(@RequestParam("Kategorija") String kategorija, @RequestParam("Esantiskodas") String esantiskodas, @RequestParam("Kaina") String kaina) throws SQLException, ClassNotFoundException { /*url/libraryInfo?name=Pilies*/
        DatebaseHandler dbHandler = new DatebaseHandler();
        if (esantiskodas == "" || kaina == "" || kategorija == "") {
            return "Nurodykite paslaugos koda, kaina ir kategorija kuri norite pakeisti";
        } else {
            int kain = 0;
            ResultSet resultt = null;
            try {
                resultt = dbHandler.KategorijaKaina(kategorija, Integer.parseInt(esantiskodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    if (!resultt.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    kain = resultt.getInt(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            try {
                dbHandler.UpdateKainaKategorija(kategorija, Integer.parseInt(kaina), Integer.parseInt(esantiskodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (kategorija == "islaidos") {
                imone.biudzetas(kain * -1);
                imone.biudzetas(Integer.parseInt(kaina));
            } else if (kategorija == "pajamos") {
                imone.biudzetas(kain);
                imone.biudzetas(Integer.parseInt(kaina) * -1);
            }

            return "Kaina pakeista";
        }
    }
        @RequestMapping(value = "Kategorija/pridetisubupdate", method = RequestMethod.PUT)
        @ResponseStatus(value = HttpStatus.OK)
        @ResponseBody
        public String UpdatePridetiSubKategorija(@RequestParam("Kategorija") String kategorija, @RequestParam("Esantiskodas") String esantiskodas, @RequestParam("Sub") String sub) throws SQLException, ClassNotFoundException { /*url/libraryInfo?name=Pilies*/
            DatebaseHandler dbHandler = new DatebaseHandler();
            if (esantiskodas == "" || sub == "" || kategorija == "") {
                return "Nurodykite paslaugos koda, subkategorija ir kategorija kuri norite pakeisti";
            } else {
                ResultSet resultt = null;
                String subkategorija = null;
                try {
                    resultt = dbHandler.KategorijaSub(kategorija, Integer.parseInt(esantiskodas));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        if (!resultt.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        subkategorija = resultt.getString(1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                subkategorija += sub;
                subkategorija += ";";
                try {
                    dbHandler.UpdateSubkategorija(kategorija, subkategorija, Integer.parseInt(esantiskodas));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return "Subkategorija prideta";
        }
    @RequestMapping(value = "Kategorija/pasalintisubupdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String UpdatePasalintiSubKategorija(@RequestParam("Kategorija") String kategorija, @RequestParam("Esantiskodas") String esantiskodas, @RequestParam("Sub") String sub) throws SQLException, ClassNotFoundException { /*url/libraryInfo?name=Pilies*/
        DatebaseHandler dbHandler = new DatebaseHandler();
        if (esantiskodas == "" || sub == "" || kategorija == "") {
            return "Nurodykite paslaugos koda, subkategorija ir kategorija kuri norite pakeisti";
        } else {
            ResultSet resultt = null;
            String[] subkategorija = null;
            try {
                resultt = dbHandler.KategorijaSub(kategorija, Integer.parseInt(esantiskodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    if (!resultt.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    subkategorija = resultt.getString(1).split(";");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            ArrayList<String> d = new ArrayList<>();
            for(String a : subkategorija)
            {
                d.add(a);
            }
            d.remove(sub);
            String s = "";
            for(String a : d)
            {
                s += a;
                s += ";";
            }
            try {
                dbHandler.UpdateSubkategorija(kategorija, s, Integer.parseInt(esantiskodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "Subkategorija pasalinta";
    }
}
