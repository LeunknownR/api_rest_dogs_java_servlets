package entity;

import com.google.gson.JsonObject;
import utils.delegates.DelegateReturnWithOneParameter;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class EntityParent<T> {
    protected ArrayList<T> hashMapArrayListToDTOArrayList(
            final ArrayList<HashMap<String, String>> table, 
            final DelegateReturnWithOneParameter<T, HashMap<String, String>> delegate) {
        ArrayList<T> array = new ArrayList<>();
        table.forEach((HashMap<String, String> row) -> {
            array.add(delegate.Execute(row));
        });
        return array;
    }
    protected abstract T getDTOforRowHashMap(HashMap<String, String> row);
    public boolean isNullPropertyOfJson(JsonObject jObj, String property) {
        if (!jObj.has(property))
            return true;
        return jObj.get(property).isJsonNull();
    }
}
