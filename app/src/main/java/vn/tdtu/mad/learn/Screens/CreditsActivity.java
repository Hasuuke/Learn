package vn.tdtu.mad.learn.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.tdtu.mad.learn.MainActivity;
import vn.tdtu.mad.learn.R;

public class CreditsActivity extends AppCompatActivity {
    Button button_credits_home;
    Button button_credits_videos;
    Button button_credits_chat;
    Button button_credits_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        button_credits_home = findViewById(R.id.btn_credits_home);
        button_credits_videos = findViewById(R.id.btn_credits_videos);
        button_credits_chat = findViewById(R.id.btn_credits_chat);
        button_credits_shop = findViewById(R.id.btn_credits_shop);

        button_credits_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
        button_credits_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideoActivity();
            }
        });
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openTaskActivity();
//            }
//        });
        button_credits_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShopActivity();
            }
        });
    }

    private void openShopActivity() {
        Intent intent = new Intent(this,ShopActivity.class);
        startActivity(intent);
    }

//    private void openTaskActivity() {
//        Intent intent = new Intent(this,TaskActivity.class);
//        startActivity(intent);
//    }

    private void openVideoActivity() {
        Intent intent = new Intent(this,VideoActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}