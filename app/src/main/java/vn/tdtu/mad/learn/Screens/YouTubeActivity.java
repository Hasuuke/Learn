package vn.tdtu.mad.learn.Screens;

import android.content.Intent;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import vn.tdtu.mad.learn.R;


public class YouTubeActivity extends AppCompatActivity {
    Intent intent;
    private YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        intent = getIntent();
        Log.e("Youtube","ID:  "+intent.getStringExtra("ID"));
        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);

        LinearLayout layout = findViewById(R.id.LayoutYoutube);
        layout.addView(youTubePlayerView);

        youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> {
            youTubePlayer.loadVideo(intent.getStringExtra("ID"), 0.0f);
        });
    }
}