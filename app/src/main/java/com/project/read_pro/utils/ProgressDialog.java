package com.project.read_pro.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.project.read_pro.R;

public class ProgressDialog {

    private ProgressDialog(){}

    public static AlertDialog createAlertDialog(Activity activity){
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.custom_progress_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(dialogLayout);
        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.show();
        alert.getWindow().setLayout(600, 280);
        return alert;
    }
}
