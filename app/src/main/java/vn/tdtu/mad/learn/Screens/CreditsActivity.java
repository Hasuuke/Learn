package vn.tdtu.mad.learn.Screens;


import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.tdtu.mad.learn.CreditItemListAdapter;
import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.database.ItemViewModel;
import vn.tdtu.mad.learn.database.Items.ShopItem;
import vn.tdtu.mad.learn.database.Items.TaskItem;


import java.util.List;

public class CreditsActivity extends AppCompatActivity {
    private ItemViewModel mItemViewModel;
    private TextView tvTotalCredits;
    private Button button_credits_home;
    private Button button_credits_videos;
    private Button button_credits_shop;
    private RecyclerView rvCredits;
    private List<TaskItem> taskItemList;
    private List<ShopItem> shopItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        button_credits_home = findViewById(R.id.btn_credits_home);
        button_credits_videos = findViewById(R.id.btn_credits_videos);
        button_credits_shop = findViewById(R.id.btn_credits_shop);
        rvCredits = findViewById(R.id.rvCredits);
        tvTotalCredits = findViewById(R.id.tvTotalCredits);


        final CreditItemListAdapter adapter = new CreditItemListAdapter(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCredits.getContext(),
                new LinearLayoutManager(this).getOrientation());
        rvCredits.addItemDecoration(dividerItemDecoration);
        rvCredits.setAdapter(adapter);
        rvCredits.setLayoutManager(new LinearLayoutManager(this));

        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        mItemViewModel.getAllCreditItems().observe(this, adapter::setCreditItems);

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

    private void openVideoActivity() {
        Intent intent = new Intent(this,VideoActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

}