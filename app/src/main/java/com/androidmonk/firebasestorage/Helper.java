package com.androidmonk.firebasestorage;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class Helper {
    private static Dialog mDialog;
    private static ProgressDialog mProgressDialog;

    public static String getPath(Context context, Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }


    public static void showDialog(Context context){
        mDialog = new Dialog(context, R.style.NewDialog);
        mDialog.addContentView(
                new ProgressBar(context),
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        mDialog.setCancelable(true);
        mDialog.show();
    }

    private static void dismissDialog(){
        if (mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    private static void initProgressDialog(Context context){
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.loading));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    private static void setProgress(int i){
        mProgressDialog.setProgress(i);
    }

    private static void dismissProgressDialog(){
        if (mProgressDialog!= null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }




}
