package com.example.mvpinloginpage.UserHome.main;

import com.example.mvpinloginpage.models.User;

public interface MainFragmentContract {

    interface Presenter{
        void getUser();
    }
    interface View{

        void showToast(String massage);

    }
}
