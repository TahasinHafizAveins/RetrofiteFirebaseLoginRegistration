package com.example.mvpinloginpage.UserHome.main;

import android.util.Log;

import com.example.mvpinloginpage.api.ApiClient;
import com.example.mvpinloginpage.api.ServiceGenerator;
import com.example.mvpinloginpage.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragmentPresenter implements MainFragmentContract.Presenter {

    private MainFragmentContract.View mView;
    private FirebaseUser firebaseUser;

    public MainFragmentPresenter(MainFragmentContract.View mView) {
        this.mView = mView;
        this.firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void getUser() {

       ApiClient client = ServiceGenerator.createService(ApiClient.class);
       client.getUser(firebaseUser.getEmail()).enqueue(new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {
               if (response.isSuccessful()){
                   User user = response.body();
                   Log.d("Name:", user.getName());
                   Log.d("Name:", user.getEmail());
                   mView.showToast("Name:"+user.get_id()+ "\n");
                   mView.showToast("Email:"+user.getEmail());
               }
               else {  Log.d("Name:", "Response Error");}
           }

           @Override
           public void onFailure(Call<User> call, Throwable throwable) {
               Log.d("Name:", "Error "+ throwable.getMessage());
           }
       });



    }

}
