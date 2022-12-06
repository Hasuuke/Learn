package vn.tdtu.mad.learn.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.tdtu.mad.learn.MainActivity;
import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.ShopItemViewModel;
import vn.tdtu.mad.learn.TaskItemListAdapter;

public class TaskActivity extends AppCompatActivity {
    private ShopItemViewModel mShopItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        RecyclerView recyclerView = findViewById(R.id.rvTasks);
        final TaskItemListAdapter adapter = new TaskItemListAdapter(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShopItemViewModel = new ViewModelProvider(this).get(ShopItemViewModel.class);
        // Update the cached copy of the words in the adapter.
        mShopItemViewModel.getAllTaskItems().observe(this, adapter::setTaskItems);
    }


    public void showCredits(View view) {
        Intent intent = new Intent(this, CreditsActivity.class);
        TaskActivity.this.startActivity(intent);
    }
    public void showShop(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        TaskActivity.this.startActivity(intent);
    }


    public void showHome(View view) {
    }
}