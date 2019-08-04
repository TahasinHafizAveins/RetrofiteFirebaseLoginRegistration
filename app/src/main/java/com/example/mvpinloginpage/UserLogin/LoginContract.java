package com.example.mvpinloginpage.UserLogin;

import com.example.mvpinloginpage.models.User;

public interface LoginContract {

    interface Presenter{
        boolean validate(User user);
        void signIn(User user);

    }

    interface View{
        void showToast(String message);
        void showErrorMessage(int fieldId,String message);
        void startHomeActivity();
        void gotoRegistrationActivity();
    }

}
