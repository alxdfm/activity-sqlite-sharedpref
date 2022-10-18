package com.example.appcomdbativ.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appcomdbativ.R;
import com.example.appcomdbativ.model.User;
import com.example.appcomdbativ.presenter.SignUpPresenter;
import com.example.appcomdbativ.repository.DataBaseHelper;
import com.example.appcomdbativ.repository.UserSQLRepository;

public class SignUpActivity extends Activity {

  private final String TAG = "SignOnActivity";

  private EditText name;
  private String nameString;
  private EditText loginRegist;
  private String loginRegistString;
  private EditText passwordRegist;
  private String passwordRegistString;
  private EditText passwordConf;
  private String passwordConfString;
  private TextView warning;

  private UserSQLRepository userSQLRepository = new UserSQLRepository(this);
  SignUpPresenter signUpPresenter = new SignUpPresenter(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);
  }

  public void onClickSignUp(View view){
    name = findViewById(R.id.name);
    nameString = name.getText().toString();
    loginRegist = findViewById(R.id.loginRegist);
    loginRegistString = loginRegist.getText().toString();
    passwordRegist = findViewById(R.id.passwordRegist);
    passwordRegistString = passwordRegist.getText().toString();
    passwordConf = findViewById(R.id.passwordConf);
    passwordConfString = passwordConf.getText().toString();
    warning = findViewById(R.id.warning);

    User user = userSQLRepository.getUserByUserLogin(loginRegistString);

    String result = signUpPresenter.signUpValidation(nameString, loginRegistString, passwordRegistString, passwordConfString, user);

    if(result.isEmpty()){
      signUpPresenter.handler(view, nameString, loginRegistString, passwordRegistString, userSQLRepository, result);
    }
    else {
      warning.setText(result);
    }
  }
}