package com.example.appcomdbativ.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

  private int id;
  private String name;
  private String userLogin;
  private String password;

  public User(int id, String name, String userLogin, String password) {
    this.id = id;
    this.name = name;
    this.userLogin = userLogin;
    this.password = password;
  }

  public User(String name, String userLogin, String password) {
    this.name = name;
    this.userLogin = userLogin;
    this.password = password;
  }

  protected User(Parcel in) {
    id = in.readInt();
    name = in.readString();
    userLogin = in.readString();
    password = in.readString();
  }

  public static final Creator<User> CREATOR = new Creator<User>() {
    @Override
    public User createFromParcel(Parcel in) {
      return new User(in);
    }

    @Override
    public User[] newArray(int size) {
      return new User[size];
    }
  };

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUserLogin() {
    return userLogin;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(name);
    parcel.writeString(userLogin);
    parcel.writeString(password);
  }
}
