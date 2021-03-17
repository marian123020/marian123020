import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AllGSONvartotojas implements JsonSerializer<ArrayList<AsmuoAtsakingasUzSistema>> {
public JsonElement serialize(ArrayList<AsmuoAtsakingasUzSistema> All, Type type, JsonSerializationContext jsonSerializationContext){
        JsonArray jsonArray=new JsonArray();

        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AsmuoAtsakingasUzSistema.class,new GSONKat());
        Gson parser=gsonBuilder.create();

        for(AsmuoAtsakingasUzSistema l:All){
        jsonArray.add(parser.toJson(l));
        }
        System.out.println(jsonArray);
        return jsonArray;
        }
}
