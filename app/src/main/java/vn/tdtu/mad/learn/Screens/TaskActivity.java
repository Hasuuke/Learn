package vn.tdtu.mad.learn.Screens;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.tdtu.mad.learn.MainActivity;
import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.ShopItemViewModel;
import vn.tdtu.mad.learn.TaskItem;
import vn.tdtu.mad.learn.TaskItemListAdapter;

public class TaskActivity extends AppCompatActivity {
    private ShopItemViewModel mShopItemViewModel;
    private  RecyclerView recyclerView;
    Button button_task_home;
    Button button_task_video;
    Button button_task_chat;
    Button button_task_shop;
    Button btnSolved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        recyclerView = findViewById(R.id.rvTasks);
        button_task_home = findViewById(R.id.btn_tasks_home);
        button_task_video = findViewById(R.id.btn_tasks_videos);
        button_task_chat = findViewById(R.id.btn_tasks_chat);
        button_task_shop = findViewById(R.id.btn_tasks_shop);

        final TaskItemListAdapter adapter = new TaskItemListAdapter(this,this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShopItemViewModel = new ViewModelProvider(this).get(ShopItemViewModel.class);
        // Update the cached copy of the words in the adapter.
        mShopItemViewModel.getAllTaskItems().observe(this, adapter::setTaskItems);

        button_task_home.setOnClickListener(view -> openActivity(new MainActivity()));
        button_task_video.setOnClickListener(view -> openActivity(new VideoActivity()));
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openTaskActivity();
//            }
//        });
        button_task_shop.setOnClickListener(view -> openActivity(new ShopActivity()));
    }

    private void openShopActivity() {
        Intent intent = new Intent(this,ShopActivity.class);
        startActivity(intent);
    }

//    private void openTaskActivity() {
//        Intent intent = new Intent(this,TaskActivity.class);
//        startActivity(intent);
//    }



    private void openActivity(AppCompatActivity activity) {
        Intent intent = new Intent(this,activity.getClass());
        startActivity(intent);
    }
    public void onClick_Task(View v) {
        Intent intent = new Intent(this,TaskActivity.class);
        startActivity(intent);
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

    public void Maths(View view){
        final TaskItemListAdapter adapter = new TaskItemListAdapter(this,this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mShopItemViewModel.getAllMathItems().observe(this, adapter::setTaskItems);

    }

    public void English(View view){
        final TaskItemListAdapter adapter = new TaskItemListAdapter(this,this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mShopItemViewModel.getAllEnglishItems().observe(this, adapter::setTaskItems);

    }

    public void Biology(View view){
        final TaskItemListAdapter adapter = new TaskItemListAdapter(this,this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mShopItemViewModel.getAllBiologyItems().observe(this, adapter::setTaskItems);

    }

    public void Geology(View view){
        final TaskItemListAdapter adapter = new TaskItemListAdapter(this,this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mShopItemViewModel.getAllGeologyItems().observe(this, adapter::setTaskItems);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            TextView credits = findViewById(R.id.credits);
        }
    }

}