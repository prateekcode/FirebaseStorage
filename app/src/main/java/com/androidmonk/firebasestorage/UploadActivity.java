package com.androidmonk.firebasestorage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "UploadActivity";
    public static final int RC_UPLOAD_STREAM = 101;
    public static final int RC_UPLOAD_FILE = 102;
    private ImageView imageView;
    private StorageReference folderRef, imageRef;
    private TextView textView;
    private UploadTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        bindWidget();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        folderRef = storageReference.child("photos");
        imageRef = folderRef.child("firebase_logo.png");

        Log.d(TAG, imageRef.getPath());
        Log.d(TAG, imageRef.getParent().getPath());

    }

    private void bindWidget() {
        imageView = findViewById(R.id.imageView3);
        textView = findViewById(R.id.text_view);
        findViewById(R.id.button_upload_from_memory).setOnClickListener(this);
        findViewById(R.id.button_upload_from_stream).setOnClickListener(this);
        findViewById(R.id.button_upload_from_file).setOnClickListener(this);
        findViewById(R.id.button_upload_resume).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        switch (view.getId()){
            case R.id.button_upload_from_memory:
                uploadDataInMemory();
                break;

            case R.id.button_upload_from_stream:
                startActivityForResult(intent, RC_UPLOAD_STREAM);
                break;

            case R.id.button_upload_from_file:
                startActivityForResult(intent, RC_UPLOAD_FILE);
                break;

            case R.id.button_upload_resume:
                Helper.mProgressDialog.show();
                mUploadTask.resume();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK){
            assert data != null;
            String path = Helper.getPath(this, Uri.parse(data.getData().toString()));
            switch (requestCode){
                case RC_UPLOAD_STREAM:
                    uploadFromStream(path);
                    break;
                case RC_UPLOAD_FILE:
                    uploadFromFile(path);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Helper.dismissProgressDialog();
        Helper.dismissDialog();
    }

    private void uploadFromFile(String path) {
        //Write some code
    }

    private void uploadFromStream(String path) {
        //Write Some Code
    }

    private void uploadDataInMemory() {
        //Write Some code
    }
}
