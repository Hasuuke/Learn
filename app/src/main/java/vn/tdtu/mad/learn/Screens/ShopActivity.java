package vn.tdtu.mad.learn.Screens;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.ShopItemListAdapter;
import vn.tdtu.mad.learn.ShopItemViewModel;
import androidx.lifecycle.*;

public class ShopActivity extends AppCompatActivity {
    private ShopItemViewModel mShopItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        RecyclerView recyclerView = findViewById(R.id.rvShop);
        final ShopItemListAdapter adapter = new ShopItemListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShopItemViewModel = new ViewModelProvider(this).get(ShopItemViewModel.class);
        // Update the cached copy of the words in the adapter.
        mShopItemViewModel.getAllShopItems().observe(this, adapter::setShopItems);
    }

    public void showHome(View view) {
    }
}