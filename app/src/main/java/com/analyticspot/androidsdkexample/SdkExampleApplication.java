package com.analyticspot.androidsdkexample;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;

import java.util.UUID;

/**
 * A singleton to hold the EventManager and configure it for the current user.
 */
public class SdkExampleApplication extends MultiDexApplication {
  private static final String SHARED_PREFS_FILE_NAME = "APP_PREFS";
  private static final String USERNAME_KEY = "USERNAME";

  @Override
  public void onCreate() {
    super.onCreate();
  }

  /**
   * Returns a persistent, unique user id. If there is no such id one is created and saved for later use.
   */
  private String getPersistentUserId() {
    SharedPreferences prefs = getApplicationContext().getSharedPreferences(
        SHARED_PREFS_FILE_NAME, android.content.Context.MODE_PRIVATE);
    String userId = prefs.getString(USERNAME_KEY, null);
    if (userId == null) {
      userId = UUID.randomUUID().toString();
      prefs.edit().putString(USERNAME_KEY, userId).apply();
    }
    return userId;
  }
}
