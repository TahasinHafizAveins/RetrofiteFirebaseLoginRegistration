package com.example.mvpinloginpage.UserLogin;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.example.mvpinloginpage.api.ApiClient;
import com.example.mvpinloginpage.api.ServiceGenerator;
import com.example.mvpinloginpage.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;


    LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
    }


    @Override
    public boolean validate(User user) {

        if (user.getEmail().isEmpty()) {
            mView.showErrorMessage(1, "You Must enter your Email");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            mView.showErrorMessage(1, "You Must enter valid Email");
            return false;
        }

        if (user.getPassword().length() < 6) {
            mView.showErrorMessage(2, "Password Length must be more then 6 Letter");
            return false;
        }


        return true;
    }

    @Override
    public void signIn(final User user) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    ApiClient client = ServiceGenerator.createService(ApiClient.class);
                    client.addUser(user)
                            .enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    if(response.isSuccessful()){
                                        mView.startHomeActivity();
                                    }
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable throwable) {
                                    mView.showToast("SignIn Unsuccessful");
                                }
                            });
                   // mView.startHomeActivity();
                } else {
                    mView.showToast("SignIn Unsuccessful");
                }
            }
        });
    }

}
