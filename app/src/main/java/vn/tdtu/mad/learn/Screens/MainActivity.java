package vn.tdtu.mad.learn.Screens;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.Screens.*;
import vn.tdtu.mad.learn.database.ItemViewModel;
import vn.tdtu.mad.learn.database.Items.ShopItem;
import vn.tdtu.mad.learn.database.Items.ShopTypes;
import vn.tdtu.mad.learn.database.Items.TaskItem;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ImageView iv_Videos, iv_Task, iv_Shop, iv_Credits;
    private Button btnHome, btnShop, btnVideos, btnChat;
    private TextView tvMainCredits;

    private List<TaskItem> taskItemList;
    private List<ShopItem> shopItemList;
    private ItemViewModel mItemViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        showOfferNotification();

        iv_Videos = (ImageView) findViewById(R.id.iv_Main_Videos);
        iv_Task = (ImageView) findViewById(R.id.iv_Main_Task);
        iv_Shop = (ImageView) findViewById(R.id.iv_Main_Shop);
        iv_Credits = (ImageView) findViewById(R.id.iv_Main_Credits);
        tvMainCredits = findViewById(R.id.main_credits);

        btnHome = findViewById(R.id.btn_Main_home);
        btnShop = findViewById(R.id.btn_Main_shop);
        btnVideos = findViewById(R.id.btn_Main_videos);


        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        mItemViewModel.getSolvedTaskItems().observe(this, new Observer<List<TaskItem>>() {
            @Override
            public void onChanged(@NonNull final List<TaskItem> taskItems) {
                taskItemList = taskItems;
                updateCredit();
            }
        });

        mItemViewModel.getRedeemedShopItems().observe(this, new Observer<List<ShopItem>>() {
            @Override
            public void onChanged(@NonNull final List<ShopItem> shopItems) {
                shopItemList = shopItems;
                updateCredit();
            }
        });

        iv_Videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideoActivity();
            }
        });
        iv_Task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTaskActivity();
            }
        });
        iv_Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShopActivity();
            }
        });

        iv_Credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreditsActivity();
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShopActivity();
            }
        });
        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideoActivity();
            }
        });


    }

    private void showOfferNotification(){
        Intent intent_alarm = new Intent(MainActivity.this,ReminderBroadcast.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent_alarm,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();
        long tenSecondInMillis = 500 * 10;
        alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick + tenSecondInMillis,pendingIntent);
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Sale Off!";
            String description = "Special Offers are there in the shop";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notification",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    private void updateCredit(){
        int plus=0;
        int minus=0;
        if(taskItemList == null ){
            Log.e("Live", "TaskList empty");
        }
        else {

            for (TaskItem taskitem :
                    taskItemList) {
                plus += taskitem.mAmount;
            }
        }

        if(shopItemList == null ){
            Log.e("Live", "ShopList empty");
        }
        else {

            for (ShopItem shopItem :
                    shopItemList) {
                minus += shopItem.mAmount;
            }
        }
        int result = plus - minus;
        tvMainCredits.setText("You have " + result+ " Credits");
    }

    private void openCreditsActivity() {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);

    }

    private void openShopActivity() {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);

    }

    private void openTaskActivity() {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

    private void openVideoActivity() {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);

    }

}