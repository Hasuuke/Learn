package vn.tdtu.mad.learn.Screens;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.Screens.*;


public class MainActivity extends AppCompatActivity {
    private ImageView iv_Videos, iv_Task, iv_Shop, iv_Credits;
    private Button btnHome, btnShop, btnVideos, btnChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_Videos = (ImageView) findViewById(R.id.iv_Main_Videos);
        iv_Task = (ImageView) findViewById(R.id.iv_Main_Task);
        iv_Shop = (ImageView) findViewById(R.id.iv_Main_Shop);
        iv_Credits = (ImageView) findViewById(R.id.iv_Main_Credits);

        btnHome = findViewById(R.id.btn_Main_home);
        btnShop = findViewById(R.id.btn_Main_shop);
        btnVideos = findViewById(R.id.btn_Main_videos);

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