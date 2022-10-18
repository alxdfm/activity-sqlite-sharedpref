package com.example.appcomdbativ.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.appcomdbativ.R;
import com.example.appcomdbativ.model.User;
import com.example.appcomdbativ.view.LoggedInActivity;
import com.example.appcomdbativ.view.MainActivity;

public class LoginPresenter {

  Activity activity;

  public LoginPresenter(Activity activity) {
    this.activity = activity;
  }

  public String loginValidation(String login, String password, User user){
    if (login.isEmpty() || password.isEmpty()) {
      return "É necessário preencher o login e senha!";
    }

    else if (user == null) {
      return "Usuário não encontrado, tente novamente!";
    }

    else if (user.getPassword().equals(password) && user.getUserLogin().equals(login)){
      return "";
    }

    else {
      return "Login ou senha incorreta!";
    }
  }

  public void handler(View view, String result, User user){
    if(result.isEmpty()){
      Intent intent = new Intent(view.getContext(), LoggedInActivity.class);
      intent.putExtra("name", user.getName());
      activity.startActivity(intent);
    }
  }
}
