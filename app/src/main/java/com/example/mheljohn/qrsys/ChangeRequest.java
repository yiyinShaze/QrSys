package com.example.mheljohn.qrsys;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mhel John on 4/24/2017.
 */
public class ChangeRequest extends StringRequest {
    private static final String CHANGE_REQUEST_URL = "http://jamthesis.com/PHPAndroid/Change.php";
    private static final String UPDATE_REQUEST_URL = "http://jamthesis.com/PHPAndroid/Update.php";
    private Map<String, String> params;

    public ChangeRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, CHANGE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }
    public ChangeRequest(String username, String cpassword,String npassword, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("cpassword", cpassword);
        params.put("npassword", npassword);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}