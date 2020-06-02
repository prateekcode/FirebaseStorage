package com.androidmonk.firebasestorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "CLICKED_BUTTON";
    private static final int RC_STORAGE_PERMS1 = 101;
    private static final int RC_STORAGE_PERMS2 = 102;
    private int hasWriteExtStorePMS;

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
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case RC_STORAGE_PERMS1:
            case RC_STORAGE_PERMS2:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (requestCode == RC_STORAGE_PERMS1){
                        startActivity(new Intent(this, UploadActivity.class));
                    }else {
                        startActivity(new Intent(this, DownloadActivity.class));
                    }
                }else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setMessage("You need to allow Permission");
                    alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, requestCode);
                        }
                    });
                    alert.setCancelable(false);
                    alert.show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RC_STORAGE_PERMS1:
            case RC_STORAGE_PERMS2:
                hasWriteExtStorePMS = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (hasWriteExtStorePMS == PackageManager.PERMISSION_GRANTED){

                    }
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
