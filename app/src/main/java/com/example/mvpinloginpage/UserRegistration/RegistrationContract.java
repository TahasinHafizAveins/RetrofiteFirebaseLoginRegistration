package com.example.mvpinloginpage.UserRegistration;

import com.example.mvpinloginpage.models.User;

public interface RegistrationContract {

    interface Presenter{

        boolean validate(User user);
        void signUp(User user);

    }

    interface View{
        void showToastOnError(String massage);
        void showErrorToast(int fieldId,String massage);
        void thisActivity();
        void startLoginActivity();
    }
}
