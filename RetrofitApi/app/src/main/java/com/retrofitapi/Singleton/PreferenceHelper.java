package com.retrofitapi.Singleton;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created on 31-01-2018.
 */

public class PreferenceHelper {
    private SharedPreferences mSharedPrefrences;
    private static PreferenceHelper mInstance = null;//Instance means object in java, hear object is initialized
    private Context mContext;
    private static final String myprefK = "myprefKey";
    private SharedPreferences.Editor editor;

    private PreferenceHelper(Context context) {
        mContext = context;
        mSharedPrefrences = mContext.getSharedPreferences(myprefK, Context.MODE_PRIVATE);
        editor = mSharedPrefrences.edit();
    }

    public static PreferenceHelper getInstance(Context context) {
        if (mInstance == null) mInstance = new PreferenceHelper(context);
        return mInstance;
    }

    //For String
    public String getString(String key, String defValue) {
        //here we want to fetch the value so we are using String method to return i.e. get String value
        return mSharedPrefrences.getString(key, defValue);
    }

    public void setString(String key, String value) {
        //here we want to put the value so we are using void i.e. normal method to set i.e. to put String value
        editor.putString(key, value);
        editor.commit();
    }

    //For Int
    public int getInt(String key, int defValue) {
        //here we want to fetch the value so we are using int method to return i.e. get int value
        return mSharedPrefrences.getInt(key, defValue);
    }

    public void setInt(String key, int value) {
        //here we want to put the value so we are using void i.e. normal method to set i.e. to put int value
        editor.putInt(key, value);
        editor.commit();
    }

    //for clearing all data in SharedPreferences
    public void clearAllData() {
        editor.clear();
        editor.commit();
    }
}
