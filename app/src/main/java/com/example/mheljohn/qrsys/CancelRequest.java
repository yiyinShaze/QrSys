package com.example.mheljohn.qrsys;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mhel John on 5/4/2017.
 */

public class CancelRequest extends StringRequest {

    private static final String CANCEL_REQUEST_URL = "http://jamthesis.com/PHPAndroid/CancelKai.php";
    private Map<String, String> params;


    public CancelRequest(String location, String qrcode, Response.Listener<String> listener) {
        super(Method.POST, CANCEL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("location", location);
        params.put("qrcode", qrcode);



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
