package com.example.mvpinloginpage.UserHome;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private FirebaseUser mCurrentUser;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
        this.mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void checkCurrentUser() {
        if (mCurrentUser==null)
        {
            mView.startLoginActivity();
        }
        else{
            mView.checkVerifiedUser();
        }
    }

    @Override
    public void checkVerifiedUser() {
        if (mCurrentUser.isEmailVerified())
        {
            mView.loadMainFragment();
        }else{
            mView.loadVerifiedFragment();
        }
    }

    @Override
    public void logOut() {
        FirebaseAuth.getInstance().signOut();
        mView.reCreate();
    }
}
