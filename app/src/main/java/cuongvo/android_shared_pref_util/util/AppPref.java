package cuongvo.android_shared_pref_util.util;

import android.content.Context;

/**
 * Created by cuongvo on 6/4/17.
 */

public class AppPref {
    public static final String APP_PREF = "AppPrefTest";
    public static final String USER_NAME = "USER_NAME";

    public static void saveUserName(Context context, String userName) {
        SharedPreferenceHelper helper = SharedPreferenceHelper.getInstance(context);
        helper.setPreferenceName(APP_PREF);
        helper.putString(USER_NAME, userName);
    }

    public static String getUserName(Context context) {
        return SharedPreferenceHelper.getInstance(context).setPreferenceName(APP_PREF).getString(USER_NAME);
    }
}
