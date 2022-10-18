package com.example.appcomdbativ.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appcomdbativ.R;
import com.example.appcomdbativ.model.User;
import com.example.appcomdbativ.presenter.LoggedInPresenter;
import com.example.appcomdbativ.presenter.LoginPresenter;
import com.example.appcomdbativ.repository.DataBaseHelper;
import com.example.appcomdbativ.repository.UserSQLRepository;

public class MainActivity extends Activity {

  private final String TAG = "MainActivity";

  private EditText  login;
  private String    loginString;
  private EditText  password;
  private String    passwordString;
  private TextView  warningLogin;

  SharedPreferences preferences;
  boolean logged;
  int userId;

  LoginPresenter loginPresenter = new LoginPresenter(this);
  LoggedInPresenter loggedInPresenter = new LoggedInPresenter(this);
  private UserSQLRepository userSQLRepository = new UserSQLRepository(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    preferences = getSharedPreferences("pref", 0);
    logged = preferences.getBoolean("logged", false);
    userId = preferences.getInt("userId", -1);
    String name = userSQLRepository.getUserById(userId).getName();

    loggedInPresenter.ifLogged(logged, this, name);
  }

  public void onClickLogin(View view){

    login = findViewById(R.id.name);
    loginString = login.getText().toString();
    password = findViewById(R.id.password);
    passwordString = password.getText().toString();
    warningLogin = findViewById(R.id.warningLogin);

    User user = userSQLRepository.getUserByUserLogin(loginString);

    String result = loginPresenter.loginValidation(loginString, passwordString, user);

    if(result.isEmpty()){
      loginPresenter.handler(view, result, user);
      SharedPreferences.Editor edit = preferences.edit();
      edit.putBoolean("logged", true);
      edit.putInt("userId", user.getId());
      edit.commit();
    }
    else {
      warningLogin.setText(result);
    }
  }

  public void onClickSignUp(View view) {
    Intent intent = new Intent(view.getContext(), SignUpActivity.class);
    startActivity(intent);
  }
}