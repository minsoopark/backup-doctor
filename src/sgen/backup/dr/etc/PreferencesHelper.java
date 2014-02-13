package sgen.backup.dr.etc;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    public static void setLoginUserId(Context context, String id) {
        SharedPreferences pref = context.getSharedPreferences("login.pref", Context.MODE_PRIVATE);
        pref.edit().putString("login_user_id", id).commit();
    }

    public static String getLoginUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences("login.pref", Context.MODE_PRIVATE);
        return pref.getString("login_user_id", "");
    }

    public static void appendHistory(Context context, String history) {
        String key = "history.pref." + getLoginUserId(context);
        SharedPreferences pref = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        String str = pref.getString("history", "");

        StringBuilder builder = new StringBuilder(str);
        builder.append(",");
        builder.append(history);

        pref.edit().putString("history", builder.toString()).commit();
    }

    public static String getHistory(Context context) {
        String key = "history.pref." + getLoginUserId(context);
        SharedPreferences pref = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return pref.getString("history", "");
    }
}
