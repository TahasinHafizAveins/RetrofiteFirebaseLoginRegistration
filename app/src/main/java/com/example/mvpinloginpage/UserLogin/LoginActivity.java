package com.example.mvpinloginpage.UserLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mvpinloginpage.R;
import com.example.mvpinloginpage.UserHome.HomeActivity;
import com.example.mvpinloginpage.UserRegistration.Registration;
import com.example.mvpinloginpage.models.User;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    EditText email;
    EditText password;
    Button loginBtn;
    Button registrationBtn;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email =findViewById(R.id.email);
        password =findViewById(R.id.password);
        loginBtn =findViewById(R.id.btn_login);
        registrationBtn =findViewById(R.id.link_signUp);

        loginPresenter =new LoginPresenter(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loginPresenter.onLogin(email.getText().toString(),password.getText().toString());

                User user = new User(email.getText().toString(),password.getText().toString());

                boolean valid =loginPresenter.validate(user);

                if(!valid){
                    return;
                }
                loginPresenter.signIn(user);

                //loginPresenter.showSuccessToast("Login Success");

            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegistrationActivity();
            }
        });


    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(int fieldId, String message) {
        switch (fieldId){
            case 1:
                showToast(message);
                break;

            case 2:
                showToast(message);
                break;
        }
    }


    @Override
    public void startHomeActivity() {
        finish();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoRegistrationActivity() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}
