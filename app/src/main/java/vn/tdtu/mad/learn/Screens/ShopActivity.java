package vn.tdtu.mad.learn.Screens;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.ShopItemListAdapter;
import vn.tdtu.mad.learn.database.ItemViewModel;
import androidx.lifecycle.*;
import vn.tdtu.mad.learn.database.Items.ShopItem;
import vn.tdtu.mad.learn.database.Items.TaskItem;

import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private ItemViewModel mItemViewModel;
    private TextView tvTotalCredits;
    private Button button_shop_home;
    private Button button_shop_videos;

    private List<TaskItem> taskItemList;
    private List<ShopItem> shopItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        RecyclerView recyclerView = findViewById(R.id.rvShop);
        button_shop_home = findViewById(R.id.btn_shop_home);
        button_shop_videos = findViewById(R.id.btn_shop_videos);
        tvTotalCredits = findViewById(R.id.tvTotalCredits);

        final ShopItemListAdapter adapter = new ShopItemListAdapter(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        mItemViewModel.getAllShopItems().observe(this, adapter::setShopItems);

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
        tvTotalCredits.setText("You have " + result+ " Credits");
    }

    private void openVideoActivity() {
        Intent intent = new Intent(this,VideoActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}