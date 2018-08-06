package com.xczn.substation.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.xczn.substation.config.Config;

/**
 * Created by zhangxiao
 * Date on 2018/5/7.
 */
public class SharedPreferencesUtils {

    private static volatile SharedPreferencesUtils sharedPreferencesUtils;
    private static SharedPreferences mSharedPreferences;

    public static SharedPreferencesUtils getInstance(){
        if (sharedPreferencesUtils == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (sharedPreferencesUtils == null) {
                    sharedPreferencesUtils = new SharedPreferencesUtils();
                }
            }
        }
        return sharedPreferencesUtils;
    }

    public static void init(Context context) {
        if (context != null){
            mSharedPreferences = context.getSharedPreferences(Config.SP_NAME, Context.MODE_PRIVATE);
        }
    }

    /**
     * 首次进入app
     * @return 是否第一次使用，默认返回true
     */
    public boolean isFirstEnter() {
        return mSharedPreferences.getBoolean(Config.SP_FIRST_ENTER, true);
    }

    public void setFirstEnter() {
        mSharedPreferences.edit().putBoolean(Config.SP_FIRST_ENTER, false).apply();
    }

    public boolean isSubscribed() {
        return mSharedPreferences.getBoolean(Config.SP_SUBSCRIBE, false);
    }

    public void setSubscribe() {
        mSharedPreferences.edit().putBoolean(Config.SP_SUBSCRIBE, true).apply();
    }
}
