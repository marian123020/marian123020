import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class GSONvartotojas implements JsonSerializer<AsmuoAtsakingasUzSistema> {
    @Override
    public JsonElement serialize(AsmuoAtsakingasUzSistema asmuoAtsakingasUzSistema, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject KJson = new JsonObject();
        KJson.addProperty("Vardas", asmuoAtsakingasUzSistema.getName());
        KJson.addProperty("Kodas", asmuoAtsakingasUzSistema.getKodas());
        KJson.addProperty("Password", asmuoAtsakingasUzSistema.getPassword());
        return KJson;
    }
}
