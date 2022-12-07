package vn.tdtu.mad.learn;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import vn.tdtu.mad.learn.Screens.ShopActivity;
import vn.tdtu.mad.learn.Screens.TaskActivity;

public class MainActivity extends AppCompatActivity {
        TextView textView;
        ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        MainActivity.this.startActivity(intent);
        setContentView(R.layout.activity_shop);
    }
}