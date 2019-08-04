package com.example.mvpinloginpage.UserHome.main;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpinloginpage.R;
import com.example.mvpinloginpage.UserHome.HomeActivity;
import com.example.mvpinloginpage.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MainFragmentContract.View {


    MainFragmentPresenter mainFragmentPresenter ;

    Button logOut;
    TextView showUser;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainFragmentPresenter = new MainFragmentPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        showUser = view.findViewById(R.id.userDetails);
        logOut = view.findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity() instanceof HomeActivity)
                {
                    HomeActivity homeActivity = (HomeActivity) getActivity();
                    homeActivity.logOut();
                }
            }
        });

        mainFragmentPresenter.getUser();

    }

    @Override
    public void showToast(String massage) {
        Toast.makeText(getContext(),massage,Toast.LENGTH_SHORT).show();
        showUser.append(massage);
    }

}
