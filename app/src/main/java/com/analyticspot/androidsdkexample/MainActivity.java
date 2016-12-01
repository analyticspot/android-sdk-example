package com.analyticspot.androidsdkexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.analyticspot.anxevent.Level;

public class MainActivity extends AppCompatActivity {
  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  private Button genericBtn;
  private Button levelBtn;
  private Button itemBtn;
  SdkExampleApplication application;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    application = (SdkExampleApplication) getApplication();

    genericBtn = (Button) findViewById(R.id.button_generic_event);
    levelBtn = (Button) findViewById(R.id.button_level_event);
    itemBtn = (Button) findViewById(R.id.button_item_event);

    genericBtn.setOnClickListener(view -> {
      Log.i(LOG_TAG, "Generic event button clicked.");
      Toast toast = Toast.makeText(this, "Generic event", Toast.LENGTH_SHORT);
      toast.show();

      application.getEventManager().genericEvent()
          .putTag("generic", "event")
          .send();
    });

    levelBtn.setOnClickListener(view -> {
      Log.i(LOG_TAG, "Level event button clicked.");
      Toast toast = Toast.makeText(this, "Level event", Toast.LENGTH_SHORT);
      toast.show();

      application.getEventManager().levelEvent()
          .level()
            .setLevelDifficulty(8)
            .setLevelId("LevelButtonClicked")
            .setProgressType(Level.ProgressType.FORWARD)
            .close()
          .send();
    });

    itemBtn.setOnClickListener(view -> {
      Log.i(LOG_TAG, "Item event button clicked.");
      Toast toast = Toast.makeText(this, "Item event", Toast.LENGTH_SHORT);
      toast.show();

      application.getEventManager().itemEvent()
          .item()
            .setItemCode("Item 18")
            .setItemDescription("An item that teaches stuff")
            .setItemDifficulty(3)
            .close()
          .send();
    });
  }
}
