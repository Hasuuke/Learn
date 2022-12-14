package vn.tdtu.mad.learn.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.database.ItemViewModel;
import vn.tdtu.mad.learn.TaskItemListAdapter;

public class VideoActivity extends AppCompatActivity {
    private ItemViewModel mItemViewModel;
    private RecyclerView recyclerView;
    private Button button_videos_home;
    private Button button_videos_shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recyclerView = findViewById(R.id.rvVideos);
        button_videos_home = findViewById(R.id.btn_videos_home);

        button_videos_shop = findViewById(R.id.btn_videos_shop);

        button_videos_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });


        button_videos_shop.setOnClickListener(new View.OnClickListener() {
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


    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showShop(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        VideoActivity.this.startActivity(intent);
    }

}
