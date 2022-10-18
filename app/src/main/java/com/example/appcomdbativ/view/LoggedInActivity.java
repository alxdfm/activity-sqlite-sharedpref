package com.example.appcomdbativ.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.appcomdbativ.R;
import com.example.appcomdbativ.presenter.LoggedInPresenter;
import com.example.appcomdbativ.repository.UserSQLRepository;

public class LoggedInActivity extends Activity {

  TextView user;

  SharedPreferences preferences;
  boolean logged;

  LoggedInPresenter loggedInPresenter = new LoggedInPresenter(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_logged_in);
    user = findViewById(R.id.user);
    user.setText(getIntent().getStringExtra("name"));
    preferences = getSharedPreferences("pref", 0);

    logged = preferences.getBoolean("logged", false);
    loggedInPresenter.ifNotLogged(logged, this);
  }

  public void onClickSignOut(View view){
    loggedInPresenter.logOutHandler(logged, preferences, view);
  }
}