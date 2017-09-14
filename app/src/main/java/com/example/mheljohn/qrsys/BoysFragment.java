package com.example.mheljohn.qrsys;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoysFragment extends Fragment {
    private  static final String Url_Data = "http://jamthesis.com/PHPAndroid/getDressBoys1.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public BoysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        String username = (String) MainActivity.myBundle.get("username");
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_women);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });


        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        // Layout of recyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(15), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // recyclerView.setAdapter(new MAdapter(this.clothes_list, this.getActivity(), this));
        // connection of adapter
        recyclerView.setAdapter(adapter);
        listItems = new ArrayList<>();
        loadRecyclerViewData();

        return view;
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Data....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Url_Data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("result");

                            for(int i = 0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                ListItem item = new ListItem(
                                        object.getString("itemname"),
                                        object.getString("gender"),
                                        object.getString("size"),
                                        object.getString("age"),
                                        object.getString("height"),
                                        object.getString("weight"),
                                        object.getString("chest"),
                                        object.getString("waist"),
                                        object.getString("fabrictype"),
                                        object.getString("price"),
                                        object.getString("qrcode"),
                                        object.getString("nostock"),
                                        object.getString("location"),
                                        object.getString("frontfilename"),
                                        object.getString("backfilename"),
                                        object.getString("leftfilename"),
                                        object.getString("rightfilename"),
                                        object.getString("fabricfilename")


                                );
                                listItems.add(item);
                            }

                            adapter= new MyAdapter(listItems, getActivity().getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //getActivity().setTitle("WOMEN");
    }

    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        public int spanCount;
        public int spacing;
        public boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

    }
    private void refreshItems() {
        // Load items

        listItems.clear();
        loadRecyclerViewData();
        // Load complete
        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...
        adapter.notifyDataSetChanged();
        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
