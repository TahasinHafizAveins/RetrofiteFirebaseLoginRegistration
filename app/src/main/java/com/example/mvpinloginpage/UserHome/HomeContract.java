package com.example.mvpinloginpage.UserHome;

public interface HomeContract {
    interface Presenter{
       void checkCurrentUser();
       void checkVerifiedUser();
       void logOut();
    }
    interface View{
        void startLoginActivity();
        void reCreate();
        void checkVerifiedUser();
        void loadMainFragment();
        void loadVerifiedFragment();
        void logOut();
    }
}
