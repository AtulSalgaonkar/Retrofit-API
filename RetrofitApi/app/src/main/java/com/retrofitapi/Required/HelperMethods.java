package com.retrofitapi.Required;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created on 30-01-2018.
 */

public class HelperMethods {
    private static final String TAG = "myTag";

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLog(String message) {
        Log.d(TAG, message);
    }

    public static void showSnackBarShort(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackBarLong(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static boolean isEmpty(EditText editText) {
        boolean isEmptyResult = false;
        if (editText.getText().toString().trim().length() == 0 || editText.getText().toString().trim().equals("")) {
            isEmptyResult = true;
        }
        return isEmptyResult;
    }
}
