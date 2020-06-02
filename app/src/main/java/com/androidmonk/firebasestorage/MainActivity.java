package com.androidmonk.firebasestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "CLICKED_BUTTON";

    private ImageView headerImageView;
    private Button uploadBtn, downloadBtn, cloudStorageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        //UI Initialize
        uploadBtn = findViewById(R.id.btn_upload);
        uploadBtn.setOnClickListener(this);
        downloadBtn = findViewById(R.id.btn_download);
        downloadBtn.setOnClickListener(this);
        cloudStorageBtn = findViewById(R.id.btn_cloud_storage);
        cloudStorageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
