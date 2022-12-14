package vn.tdtu.mad.learn.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.RecyclerViewInterface;
import vn.tdtu.mad.learn.database.ItemViewModel;
import vn.tdtu.mad.learn.TaskItemListAdapter;
import vn.tdtu.mad.learn.database.Items.TaskItem;


public class TaskActivity extends AppCompatActivity implements RecyclerViewInterface {
    private ItemViewModel mItemViewModel;
    private  RecyclerView recyclerView;
    TaskItemListAdapter adapter;

    Button button_task_home;
    Button button_task_video;
    Button button_task_chat;
    Button button_task_shop;
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        recyclerView = findViewById(R.id.rvTasks);
        button_task_home = findViewById(R.id.btn_tasks_home);
        button_task_video = findViewById(R.id.btn_tasks_videos);
        button_task_chat = findViewById(R.id.btn_tasks_chat);
        button_task_shop = findViewById(R.id.btn_tasks_shop);

        adapter  = new TaskItemListAdapter(this,this, this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        mItemViewModel.getAllTaskItems().observe(this, adapter::setTaskItems);


        button_task_home.setOnClickListener(view -> openActivity(new MainActivity()));
        button_task_video.setOnClickListener(view -> openActivity(new VideoActivity()));
        button_task_shop.setOnClickListener(view -> openActivity(new ShopActivity()));
    }


    private void openActivity(AppCompatActivity activity) {
        Intent intent = new Intent(this,activity.getClass());
        startActivity(intent);
    }

    public void Maths(View view){
        mItemViewModel.getAllMathItems().observe(this, adapter::setTaskItems);

    }

    public void English(View view){

        mItemViewModel.getAllEnglishItems().observe(this, adapter::setTaskItems);

    }

    public void Biology(View view){
        mItemViewModel.getAllBiologyItems().observe(this, adapter::setTaskItems);

    }

    public void Geology(View view){
        mItemViewModel.getAllGeologyItems().observe(this, adapter::setTaskItems);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&& resultCode == RESULT_OK) {
            TaskItem taskItem = adapter.getTaskItem(index);
            Log.e("INDEX", "Old TaskItem selected: " + taskItem);
            taskItem.setmSolved(true);
            mItemViewModel.update(taskItem);
            Log.e("INDEX", "TaskItem updated: " + taskItem);

        }else if(requestCode == 2 && resultCode == RESULT_OK){
            TaskItem taskItem = adapter.getTaskItem(index);
            Log.e("INDEX2", "Old TaskItem selected: " + taskItem);
            taskItem.setmSolved(true);
            mItemViewModel.update(taskItem);
            Log.e("INDEX2", "TaskItem updated: " + taskItem);

        }
    }


    @Override
    public void onItemClick(int position) {
        index = position;
        Log.e("ITEM","Position Interface: "+position);
        Intent intent = new Intent(this, CaptureImageActivity.class);
        startActivityForResult(intent, 2);
    }
}