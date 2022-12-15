package vn.tdtu.mad.learn.Screens;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.tdtu.mad.learn.*;
import vn.tdtu.mad.learn.database.ItemViewModel;
import vn.tdtu.mad.learn.database.Items.VideoItem;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity implements RecyclerViewInterface {
    private ItemViewModel mItemViewModel;
    private VideoItemListAdapter adapter;
    private RecyclerView recyclerView;
    private Button button_videos_home;
    private Button button_videos_shop;
    private VideoItem currentVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recyclerView = findViewById(R.id.rvVideos);
        button_videos_home = findViewById(R.id.btn_videos_home);
        button_videos_shop = findViewById(R.id.btn_videos_shop);

        adapter = new VideoItemListAdapter(this, this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<VideoItem> videoItems = new ArrayList<>();
        videoItems.add(new VideoItem("Protein Synthesis", "oefAI2x2CQM", "Amoeba Sisters", "Biology", 8.46f, 4.0f));
        videoItems.add(new VideoItem("Introduction to Plate Tectonics", "fzhPmemffII", "Frank Gregorio", "Geology", 3.27f, 5f));
        videoItems.add(new VideoItem("Introduction to Polynomials", "nPPNgin7W7Y", "Professor Dave Explains", "Maths", 5.12f, 4.0f));
        videoItems.add(new VideoItem("Writing Skills: The Paragraph", "0IFDuhdB2Hk", "Adams English Lessons", "English", 14.32f, 5f));
        adapter.setVideoItems(videoItems);


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
        Intent intent = new Intent(this, ShopActivity.class);
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

    @Override
    public void onItemClick(int position) {
        Log.e("VIDEOS", String.valueOf(position));
        currentVideo = adapter.getVideoItem(position);
        Intent youtubeIntent = new Intent(this, YouTubeActivity.class);
        youtubeIntent.putExtra("ID", currentVideo.getVideoID());
        startActivity(youtubeIntent);
    }

}
