package com.example.appcomdbativ.presenter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.appcomdbativ.model.User;
import com.example.appcomdbativ.repository.DataBaseHelper;
import com.example.appcomdbativ.repository.UserSQLRepository;
import com.example.appcomdbativ.view.LoggedInActivity;
import com.example.appcomdbativ.view.MainActivity;

public class SignUpPresenter {

  Activity activity;

  public SignUpPresenter(Activity activity) {
    this.activity = activity;
  }

  public String signUpValidation(String name, String login, String password, String passwordConf, User user){
    if (login.isEmpty() || password.isEmpty() || name.isEmpty() || passwordConf.isEmpty()) {
      return "É necessário preencher todos os campos";
    }

    else if (!password.equals(passwordConf)) {
      return "Senhas não conferem!";
    }

    else if (user!=null){
      return "Usuário já existe!";
    }

    else {
      return "";
    }
  }

  public void handler(View view, String nameString, String loginRegistString, String passwordRegistString, UserSQLRepository userSQLRepository, String result){
    if(result.isEmpty()){
      userSQLRepository.postUser(new User(nameString, loginRegistString, passwordRegistString));
      Intent intent = new Intent(view.getContext(), MainActivity.class);
      activity.startActivity(intent);
    }
  }
}
