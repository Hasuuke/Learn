package vn.tdtu.mad.learn.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import vn.tdtu.mad.learn.MainActivity;
import vn.tdtu.mad.learn.R;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }


    public void showCredits(View view) {
        Intent intent = new Intent(this, CreditsActivity.class);
    }
    public void showShop(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
    }
    public void showTask(View view) {
        Intent intent = new Intent(this, TaskActivity.class);
    }

    public void showHome(View view) {
    }
}