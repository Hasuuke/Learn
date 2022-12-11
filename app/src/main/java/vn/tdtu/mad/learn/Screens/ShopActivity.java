package vn.tdtu.mad.learn.Screens;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.tdtu.mad.learn.MainActivity;
import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.ShopItemListAdapter;
import vn.tdtu.mad.learn.ShopItemViewModel;
import androidx.lifecycle.*;

public class ShopActivity extends AppCompatActivity {
    private ShopItemViewModel mShopItemViewModel;
    private TextView tvTotalCredits;
    private Button button_shop_home;
    private Button button_shop_videos;
    private Button button_shop_chat;
    private Button button_shop_shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        RecyclerView recyclerView = findViewById(R.id.rvShop);
        button_shop_home = findViewById(R.id.btn_shop_home);
        button_shop_videos = findViewById(R.id.btn_shop_videos);
        button_shop_chat = findViewById(R.id.btn_shop_chat);
        button_shop_shop = findViewById(R.id.btn_shop_shop);
        final ShopItemListAdapter adapter = new ShopItemListAdapter(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShopItemViewModel = new ViewModelProvider(this).get(ShopItemViewModel.class);
        mShopItemViewModel.getAllShopItems().observe(this, adapter::setShopItems);
        tvTotalCredits = findViewById(R.id.tvTotalCredits);

        button_shop_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
        button_shop_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideoActivity();
            }
        });

        button_shop_shop.setOnClickListener(new View.OnClickListener() {
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



    private void openVideoActivity() {
        Intent intent = new Intent(this,VideoActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClick_Shop(View view) {
        Intent intent = new Intent(this,ShopActivity.class);
        startActivity(intent);
    }
}