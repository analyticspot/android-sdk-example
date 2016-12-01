package com.analyticspot.androidsdkexample;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import com.analyticspot.android.sdk.EventManager;
import com.analyticspot.android.sdk.SessionInfo;
import com.analyticspot.anxevent.Context;

import java.util.UUID;

/**
 * A singleton to hold the EventManager and configure it for the current user.
 */
public class SdkExampleApplication extends MultiDexApplication {
  private static final String SHARED_PREFS_FILE_NAME = "APP_PREFS";
  private static final String USERNAME_KEY = "USERNAME";
  private static final String ANX_API_KEY = "API KEY GOES HERE!!";

  private EventManager eventManager;

  @Override
  public void onCreate() {
    super.onCreate();

    SessionInfo sessionInfo = SessionInfo.builder()
        .setSessionId(UUID.randomUUID().toString())
        .setAppId("APP ID GOES HERE!!!")
        .setAppStore(Context.AppStore.GOOGLE_PLAY)
        .setAppVersion("1.0")
        .setUser("Internal", getPersistentUserId())
        .addTag("EXAMPLE_TAG", "FOO")
        .build();
    eventManager = EventManager.create(ANX_API_KEY, sessionInfo, this);
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

  public EventManager getEventManager() {
    return eventManager;
  }
}
