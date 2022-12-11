package vn.tdtu.mad.learn;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.tdtu.mad.learn.Screens.CreditsActivity;
import vn.tdtu.mad.learn.Screens.ShopActivity;
import vn.tdtu.mad.learn.Screens.TaskActivity;
import vn.tdtu.mad.learn.Screens.VideoActivity;

public class MainActivity extends AppCompatActivity {
        TextView textView;
        ImageView imageView_videos;
        ImageView imageView_task;
        ImageView imageView_shop;
        ImageView imageView_credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
//        MainActivity.this.startActivity(intent);
        setContentView(R.layout.activity_main);
        imageView_videos = (ImageView) findViewById(R.id.btn_videos);
        imageView_task = (ImageView) findViewById(R.id.btn_task);
        imageView_shop = (ImageView) findViewById(R.id.btn_shop);
        imageView_credits = (ImageView) findViewById(R.id.credits_btn);
        imageView_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideoActivity();
            }
        });
        imageView_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTaskActivity();
            }
        });
        imageView_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShopActivity();
            }
        });
        imageView_credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreditsActivity();
            }
        });
    }

    private void openCreditsActivity() {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    private void openShopActivity() {
        Intent intent = new Intent(this,ShopActivity.class);
        startActivity(intent);
    }
    private void openTaskActivity() {
        Intent intent = new Intent(this,TaskActivity.class);
        startActivity(intent);
    }
    private void openVideoActivity() {
        Intent intent = new Intent(this,VideoActivity.class);
        startActivity(intent);
    }


}