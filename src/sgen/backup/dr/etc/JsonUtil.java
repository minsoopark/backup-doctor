package sgen.backup.dr.etc;

import org.json.JSONObject;

import java.util.Map;

public class JsonUtil {
    public static String getJsonStringFromMap(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }
}
