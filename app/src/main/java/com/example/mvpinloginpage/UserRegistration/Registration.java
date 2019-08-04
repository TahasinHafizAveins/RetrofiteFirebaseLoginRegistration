package com.example.mvpinloginpage.UserRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpinloginpage.R;
import com.example.mvpinloginpage.UserHome.HomeActivity;
import com.example.mvpinloginpage.UserLogin.LoginActivity;
import com.example.mvpinloginpage.models.User;

import es.dmoral.toasty.Toasty;

public class Registration extends AppCompatActivity implements RegistrationContract.View {


    RegistrationContract.Presenter registrationPresenter;

    EditText name;
    EditText address;
    EditText phone;
    EditText email;
    EditText password;
    EditText repassword;
    Button registrationBtn;
    Button loginPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        loginPageBtn=findViewById(R.id.login);
        registrationBtn=findViewById(R.id.btn_Registration);

        registrationPresenter =new RegistrationPresenter(this);

        onClickRegistrationBtn();
        onClickLoginBtn();

    }

    private void onClickRegistrationBtn() {

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(name.getText().toString(),address.getText().toString(),
                        phone.getText().toString(),email.getText().toString(),
                        password.getText().toString(),repassword.getText().toString());

                User usres =new User(email.getText().toString(),password.getText().toString());

                boolean valid = registrationPresenter.validate(user);

                if(!valid){
                    return;
                }

                registrationPresenter.signUp(usres);

            }
        });

    }
    private void onClickLoginBtn() {
        loginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginActivity();
            }
        });

    }


    @Override
    public void showToastOnError(String massage) {
        Toasty.error(this, massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorToast(int fieldId, String massage) {
        switch (fieldId) {
            case 1:
                showToastOnError(massage);
                break;
            case 2:
                showToastOnError(massage);
                break;
            case 3:
                showToastOnError(massage);
                break;
            case 4:
                showToastOnError(massage);
                break;
            case 5:
                showToastOnError(massage);
                break;
            case 6:
                showToastOnError(massage);
                break;
        }
    }


    @Override
    public void thisActivity() {
        startActivity(getIntent());
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
