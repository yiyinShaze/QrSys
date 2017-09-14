package com.example.mheljohn.qrsys;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Mhel John on 4/24/2017.
 */
public class ShopRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://jamthesis.com/PHPAndroid/Shop1.php";
    private Map<String, String> params;

    public ShopRequest(String username, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);

    }

    @Override
    public Map<String, String> getParams() {
        return checkParams(params);
    }
    private Map<String, String> checkParams(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
            if (pairs.getValue() == null) {
                map.put(pairs.getKey(), "");
            }
        }
        return map;
    }
}