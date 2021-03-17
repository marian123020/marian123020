import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class vartotojas {
    ImonesBudzietas imone = LD3.getImone();
    Gson parser = new Gson();
    @RequestMapping(value = "vartotojas/info", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getKategorijaPagalKodas(@RequestParam("Vardas") String vardas , @RequestParam("Kodas") String kodas) throws SQLException, ClassNotFoundException {
        GsonBuilder gson = new GsonBuilder();
        AsmuoAtsakingasUzSistema uz = new AsmuoAtsakingasUzSistema();
        if (vardas == "" || kodas == "") {
            return "Iveskite visus duomenys";
        } else {
            DatebaseHandler dbHandler = new DatebaseHandler();
            uz.setName(vardas);
            uz.setKodas(Integer.parseInt(kodas));
            ResultSet result = null;
            try {
                result = dbHandler.ArYrVartotojas(uz);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            int temp = 0;
            if (result.next()) {
                try {
                    temp++;
                    gson.registerTypeAdapter(AsmuoAtsakingasUzSistema.class, new GSONvartotojas());
                    parser = gson.create();
                    String a = result.getString(1);
                    int b = result.getInt(2);
                    String c = result.getString(3);
                    uz = new AsmuoAtsakingasUzSistema(a, b, c);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if(temp==0)
            {
                return "Nera tokio vartotojo";
            }
        }
        return parser.toJson(uz);
    }
    @RequestMapping(value = "vartotojas/All")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllKategorija() throws SQLException, ClassNotFoundException {

        DatebaseHandler Handler= new DatebaseHandler();
        ResultSet result = Handler.VartotojoPerziuretiSarasa();
        ArrayList<AsmuoAtsakingasUzSistema> All = new ArrayList<>();
        while(result.next())
        {
            String a = result.getString(1);
            int b = result.getInt(2);
            String c = result.getString(3);
            AsmuoAtsakingasUzSistema as = new AsmuoAtsakingasUzSistema(a, b, c);
            All.add(as);
        }

        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(AsmuoAtsakingasUzSistema.class, new GSONvartotojas());
        Gson parser = gson.create();
        //parser.toJson(All.get(0));

        Type VartotojasList = new TypeToken<ArrayList<AsmuoAtsakingasUzSistema>>() {
        }.getType();
        gson.registerTypeAdapter(VartotojasList, new AllGSONvartotojas());
        parser = gson.create();

        return parser.toJson(All);
    }

    @RequestMapping(value = "vartotojas/prideti", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String PridetiVartotoja(@RequestParam("Vardas") String vardas ,@RequestParam("Kodas") String kodas ,@RequestParam("Password") String password) throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        if (vardas == "" || kodas == "" || password == "") {
            return "Iveskite visus duomenys";
        } else {
            boolean VartotojasArYra = false;
            AsmuoAtsakingasUzSistema uz = new AsmuoAtsakingasUzSistema();
            uz.setName(vardas);
            uz.setKodas(Integer.parseInt(kodas));
            uz.setPassword(password);
            ResultSet result = null;
            try {
                result = dbHandler.ArYrVartotojas(uz);
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

                VartotojasArYra = true;
                return "Toks vartoptojas su vardu:" + " ir kodu:" + kodas + " jau yra.";
            }
            if (VartotojasArYra == false) {
                AsmuoAtsakingasUzSistema s = new AsmuoAtsakingasUzSistema(vardas,Integer.parseInt(kodas),password);
                try {
                    dbHandler.VartotojaPrideti(s);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Vartotojas pridetas";
    }
    @RequestMapping(value = "vartotojas/pasalinti", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String PasalintiVartotoja(@RequestParam("Vardas") String vardas ,@RequestParam("Kodas") String kodas ) throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        if (vardas == "" || kodas == "") {
            return "Iveskite visus duomenys";
        } else {
            AsmuoAtsakingasUzSistema uz = new AsmuoAtsakingasUzSistema();
            dbHandler = new DatebaseHandler();
            uz.setName(vardas);
            uz.setKodas(Integer.parseInt(kodas));
            ResultSet result = null;
            try {
                result = dbHandler.ArYrVartotojas(uz);
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
                try {
                    dbHandler.VartotojoSalinimas(uz);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Vartotojas pasalintas";
    }
    @RequestMapping(value = "vartotojas/vardasupdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String UpdateNameKategorija(@RequestParam("Esantiskodas") String esantiskodas, @RequestParam("Vardas") String vardas) throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        if(esantiskodas == "" || vardas == "")
        {
            return "Nurodykite vartotojo koda ir varda, kuri norite pakeisti";
        }
        else {
            try {
                dbHandler.UpdateNameVartotojas(vardas, Integer.parseInt(esantiskodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return "Vardas pakeistas";
        }
    }
    @RequestMapping(value = "vartotojas/kodasupdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String UpdateKodasKategorija(@RequestParam("Esantiskodas") String esantiskodas, @RequestParam("kodas") String kodas) throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        if(esantiskodas == "" || kodas == "")
        {
            return "Nurodykite vartotojo koda, nauja koda, kuri norite pakeisti";
        }
        else {
            try {
                dbHandler.UpdateKodasVartotojas(Integer.parseInt(esantiskodas), Integer.parseInt(kodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return "Kodas pakeistas";
        }
    }
    @RequestMapping(value = "vartotojas/passupdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String UpdateKainaKategorija(@RequestParam("Esantiskodas") String esantiskodas, @RequestParam("Password") String password) throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        if(esantiskodas == "" || password == "")
        {
            return "Nurodykite vartotojo koda, nauja passworda, kuri norite pakeisti";
        }
        else {
            try {
                dbHandler.UpdatePassVartotojas(password, Integer.parseInt(esantiskodas));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return "Kodas pakeistas";
        }
    }
}
