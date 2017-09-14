package com.example.mheljohn.qrsys;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mhel John on 4/24/2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://jamthesis.com/PHPAndroid/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String fName, String lName, String username, String password, String cNumber, String address, String email, String loc, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("fName", fName);
        params.put("lName", lName);
        params.put("username", username);
        params.put("password", password);
        params.put("cNumber", cNumber);
        params.put("address", address);
        params.put("email", email);
        params.put("loc", loc);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}