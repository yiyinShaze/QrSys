package com.example.mheljohn.qrsys;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private static EditText username;
    private static EditText password;
    private static Button loginButton;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        username = (EditText) root.findViewById(R.id.username);
        password = (EditText) root.findViewById(R.id.password);
        loginButton = (Button) root.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (username.getText().toString().equals("user") &&
                        password.getText().toString().equals("pass")) {
                    Toast.makeText(getActivity(), "User and Password is correct",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "User and Password is not correct",Toast.LENGTH_SHORT).show();

                }
            }
        });
        return root;
    }

}
