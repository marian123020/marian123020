import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class GSONKat implements JsonSerializer<Kategorija> {
    @Override
    public JsonElement serialize(Kategorija kategotija, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject KJson = new JsonObject();
        KJson.addProperty("Kategorija", kategotija.getKategorija());
        KJson.addProperty("Pavadinimas", kategotija.getPavadinimas());
        KJson.addProperty("Kodas", kategotija.getKodas());
        KJson.addProperty("Kaina", kategotija.getKaina());
        KJson.addProperty("Subkategorija", String.valueOf(kategotija.getSubkategorija()));
        return KJson;
    }
}
