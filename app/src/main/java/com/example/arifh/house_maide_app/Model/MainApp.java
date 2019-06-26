package com.example.arifh.house_maide_app.Model;

import android.app.Application;

public class MainApp extends Application {
    private static MainApp instant = null;
    User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instant = this;
    }

    public static MainApp getInstance() {
        return instant;
    }
}
