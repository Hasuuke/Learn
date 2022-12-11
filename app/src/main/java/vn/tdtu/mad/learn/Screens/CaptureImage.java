package vn.tdtu.mad.learn.Screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import vn.tdtu.mad.learn.R;

public class CaptureImage extends AppCompatActivity {
    ImageView imageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);
        final Object object = getIntent().getSerializableExtra("detail");
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        if(ContextCompat.checkSelfPermission(CaptureImage.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CaptureImage.this,new String[]{Manifest.permission.CAMERA},10);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            button.setText("Submit");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openTaskActivity();
                }
            });
        }
    }

    private void openTaskActivity() {
        setResult(100);

        finish();
    }
}