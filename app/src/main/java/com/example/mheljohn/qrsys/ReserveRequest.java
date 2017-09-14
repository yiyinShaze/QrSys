package com.example.mheljohn.qrsys;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mhel John on 5/4/2017.
 */

public class ReserveRequest extends StringRequest {
    private static final String RESERVE_REQUEST_URL = "http://jamthesis.com/PHPAndroid/ReserveKai5Min.php";

    private Map<String, String> params;

    public ReserveRequest(String username, String qrcode,String itemname,String price,String currentrenter,String location, Response.Listener<String> listener) {
        super(Method.POST, RESERVE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("qrcode", qrcode);
        params.put("itemname", itemname);
        params.put("price", price);
        params.put("currentrenter", currentrenter);
        params.put("location", location);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
