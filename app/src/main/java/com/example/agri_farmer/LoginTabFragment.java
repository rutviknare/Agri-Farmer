package com.example.agri_farmer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginTabFragment extends Fragment {

    EditText email,password;
    FirebaseAuth mAuth;
    Button signin;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginTabFragment newInstance(String param1, String param2) {
        LoginTabFragment fragment = new LoginTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent=new Intent(getActivity(),DashBoard.class);
//            startActivity(intent);
////            getActivity().finish();
//        }
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login_tab, container, false);
        email=v.findViewById(R.id.login_email);
        password=v.findViewById(R.id.login_password);
        signin=v.findViewById(R.id.login_button);
        mAuth=FirebaseAuth.getInstance();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1,password1;
                email1=String.valueOf(email.getText());
                password1=String.valueOf(password.getText());

                if(TextUtils.isEmpty(email1)){
                    Toast.makeText(getActivity(),"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(getActivity(),"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                                    Toast.makeText(getActivity(), "Welcome "+currentFirebaseUser.getEmail(),
                                            Toast.LENGTH_SHORT).show();
                                    email.setText("");
                                    password.setText("");
                                    Intent intent= new Intent(getActivity(),DashBoard.class);
                                    startActivity(intent);
//                                    getActivity().finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getActivity(), "Please Enter Correct Password.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
        return v;
    }
}