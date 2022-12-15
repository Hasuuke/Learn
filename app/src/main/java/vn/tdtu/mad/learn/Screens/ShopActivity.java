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
import vn.tdtu.mad.learn.RecyclerViewInterface;
import vn.tdtu.mad.learn.ShopItemListAdapter;
import vn.tdtu.mad.learn.database.ItemViewModel;
import androidx.lifecycle.*;
import vn.tdtu.mad.learn.database.Items.ShopItem;
import vn.tdtu.mad.learn.database.Items.TaskItem;

import java.util.List;

public class ShopActivity extends AppCompatActivity implements RecyclerViewInterface {
    private ItemViewModel mItemViewModel;
    private TextView tvTotalCredits;
    private Button button_shop_home;
    private Button button_shop_videos;
    private RecyclerView recyclerView;

    private List<TaskItem> taskItemList;
    private List<ShopItem> shopItemList;
    private ShopItemListAdapter adapter;
    private int index = 0;
    public static int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recyclerView = findViewById(R.id.rvShop);
        button_shop_home = findViewById(R.id.btn_shop_home);
        button_shop_videos = findViewById(R.id.btn_shop_videos);
        tvTotalCredits = findViewById(R.id.tvTotalCredits);

        adapter = new ShopItemListAdapter(this, this);
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

    private void updateCredit() {
        int plus = 0;
        int minus = 0;
        if (taskItemList == null) {
            Log.e("Live", "TaskList empty");
        } else {

            for (TaskItem taskitem :
                    taskItemList) {
                plus += taskitem.mAmount;
            }
        }

        if (shopItemList == null) {
            Log.e("Live", "ShopList empty");
        } else {

            for (ShopItem shopItem :
                    shopItemList) {
                minus += shopItem.mAmount;
            }
        }
        result = plus - minus;
        tvTotalCredits.setText("You have " + result + " Credits");
    }

    private void openVideoActivity() {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            ShopItem shopItem = adapter.getShopItem(index);
            Log.e("INDEX", "Old ShopItem selected: " + shopItem);
            shopItem.setmRedeemed(true);
            mItemViewModel.update(shopItem);
            Log.e("INDEX", "ShopItem updated: " + shopItem);

        }
    }


    @Override
    public void onItemClick(int position) {
        Log.e("SHOP", String.valueOf(position));
        index = position;
        ShopItem current = adapter.getShopItem(position);
        Intent intent = new Intent(this, MapsActivity.class);
        switch (current.getmShopType()) {
            case MC_DONALDS:
                intent.putExtra("Type", "McDonalds");
                break;
            case BURGER_KING:
                intent.putExtra("Type", "BurgerKing");
                break;
            case FORTNITE:
                intent.putExtra("Type", "Fortnite");
                break;
            default:
                break;
        }

        Log.e("@@@@", "Total Credits"+ result);
        if (result - current.getmAmount() < 0) {
            intent.putExtra("Redeemable","0");
            Log.e("@@@@", "Not Redeemable");
        }
        else {
            intent.putExtra("Redeemable","1");
            Log.e("@@@@", "redeemable");
        }
        startActivityForResult(intent, 2);
    }
}