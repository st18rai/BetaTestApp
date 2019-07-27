package com.st18rai.betatestapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import com.st18rai.betatestapp.R;
import com.st18rai.betatestapp.interfaces.Constants;
import com.st18rai.betatestapp.model.LoginModel;
import com.st18rai.betatestapp.viewmodel.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login)
    EditText login;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login_box)
    CardView loginBox;

    @BindView(R.id.button_login)
    Button buttonLogin;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        // for test
//        login.setText("20234561");
//        password.setText("ladevi31");

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getPassKey().observe(this, this::navigateToMainScreen);

        loginViewModel.getErrorMessage().observe(this, message -> {
            showProgress(false);
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
        });

    }

    @OnClick(R.id.button_login)
    public void onLoginClick() {

        if (isFieldsCorrect(login, password)) {

            showProgress(true);

            loginViewModel.loginUser(new LoginModel(login.getText().toString(),
                    password.getText().toString()));
        } else {
            Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFieldsCorrect(EditText login, EditText password) {
        String userLogin = login.getText().toString();
        String userPassword = password.getText().toString();

        return !TextUtils.isEmpty(userLogin) && !TextUtils.isEmpty(userPassword);
    }

    private void navigateToMainScreen(String passKey) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.PASSKEY, passKey);
        intent.putExtra(Constants.LOGIN, login.getText().toString());
        startActivity(intent);
        finish();
    }

    private void showProgress(boolean show) {

        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            loginBox.setVisibility(View.GONE);
            buttonLogin.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            loginBox.setVisibility(View.VISIBLE);
            buttonLogin.setVisibility(View.VISIBLE);
        }

    }

}
