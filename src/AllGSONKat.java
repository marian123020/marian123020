import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AllGSONKat implements JsonSerializer<ArrayList<Kategorija>> {
    public JsonElement serialize(ArrayList<Kategorija> All, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Kategorija.class, new GSONKat());
        Gson parser = gsonBuilder.create();

        for (Kategorija l : All) {
            jsonArray.add(parser.toJson(l));
        }
        System.out.println(jsonArray);
        return jsonArray;
    }
}
