package com.example.mvpinloginpage.UserRegistration;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.example.mvpinloginpage.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View mView;

    public RegistrationPresenter(RegistrationContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(User user) {

        if (user.getName().isEmpty()) {
            mView.showErrorToast(1, "You Must enter your Name");
            return false;
        }
        if (user.getAddress().isEmpty()) {
            mView.showErrorToast(2, "You Must enter your Address");
            return false;
        }

        if (!Patterns.PHONE.matcher(user.getPhone()).matches()) {
            mView.showErrorToast(3, "You Must enter valid Phone Number");
            return false;
        }

        if (user.getEmail().isEmpty()) {
            mView.showErrorToast(4, "You Must enter your Email");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            mView.showErrorToast(4, "You Must enter valid Email");
            return false;
        }

        if (user.getPassword().length() < 6) {
            mView.showErrorToast(5, "Password Length must be more then 6 Letter");
            return false;
        }
        if (!user.getRepassword().equals(user.getPassword())) {
            mView.showErrorToast(6, "Password Doesn't match");
            return false;
        }

        return true;
    }

    @Override
    public void signUp(User user) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    if (firebaseUser != null)
                    {
                        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    FirebaseAuth.getInstance().signOut();
                                    mView.startLoginActivity();
                                }
                                else {
                                    mView.showToastOnError("Unable to send verification code");
                                    mView.thisActivity();
                                }
                            }
                        });
                    }
                    else {
                        mView.showToastOnError("User is null");
                    }

                }

                else {
                    mView.showToastOnError("Unable Create new User");
                }
            }
        });
    }
}
