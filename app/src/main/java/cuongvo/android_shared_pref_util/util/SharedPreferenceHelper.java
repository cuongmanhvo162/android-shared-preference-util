package cuongvo.android_shared_pref_util.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by cuongvo.
 */
public class SharedPreferenceHelper<T> implements ISharedPreference<T> {

    private String mPreferenceName;
    private String mKey;
    private T mValue;

    private static Context mContext;

    private static SharedPreferenceHelper mSharedPreferenceHelper;

    private SharedPreferenceHelper() {}

    public static SharedPreferenceHelper getInstance(Context context) {
        mContext = context;

        if (mSharedPreferenceHelper == null) {
            return new SharedPreferenceHelper();
        } else {
            return mSharedPreferenceHelper;
        }
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    public String getPreferenceName() {
        return mPreferenceName;
    }

    public void setPreferenceName(String preferenceName) {
        this.mPreferenceName = preferenceName;
    }

    @Override
    public T getValue() {
        return mValue;
    }

    @Override
    public void setValue(T value) {
        this.mValue = value;
    }

    public boolean addPreference() {
        boolean result = true;

        try {
            SharedPreferences sharedPreferences = getSharedPreferences(mPreferenceName);
            buildPreference(sharedPreferences);
        } catch (Exception ex) {
            result = false;
        }

        return result;
    }

    public T getPreference(String key, Class<T> classType) {
        T result = null;

        SharedPreferences sharedPreferences = getSharedPreferences(mPreferenceName);
        Log.d("SharedPrefHelper", "getPreference result ");

        if (sharedPreferences.contains(key)) {
            if (classType.equals(Boolean.class)) {
                Log.d("SharedPrefHelper", "getPreference result Boolean");
                result = classType.cast(sharedPreferences.getBoolean(key, false));

            } else if (classType.equals(Integer.class)) {
                Log.d("SharedPrefHelper", "getPreference result Integer");
                result = classType.cast(sharedPreferences.getInt(key, -1));

            } else if (classType.equals(Float.class)) {
                Log.d("SharedPrefHelper", "getPreference result Float");
                result = classType.cast(sharedPreferences.getFloat(key, -1.0F));

            } else if (classType.equals(Long.class)) {
                Log.d("SharedPrefHelper", "getPreference result Long");
                result = classType.cast(sharedPreferences.getLong(key, -1L));

            } else if (classType.equals(String.class)) {
                Log.d("SharedPrefHelper", "getPreference result String");
                result = classType.cast(sharedPreferences.getString(key, null));

            }
        }
        Log.d("SharedPrefHelper", "getPreference result " + result);
        return result;
    }

    public boolean isContainKey(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(mPreferenceName);
        return sharedPreferences.contains(key);
    }

    private void buildPreference(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (mValue.getClass().equals(Integer.class)) {
            editor.putInt(mKey, (Integer) mValue);

        } else if (mValue.getClass().equals(Boolean.class)) {
            editor.putBoolean(mKey, (Boolean) mValue);

        } else if (mValue.getClass().equals(Float.class)) {
            editor.putFloat(mKey, (Float) mValue);

        } else if (mValue.getClass().equals(Long.class)) {
            editor.putLong(mKey, (Long) mValue);

        } else if (mValue.getClass().equals(String.class)) {
            editor.putString(mKey, (String) mValue);

        }

        editor.commit();
    }

    public SharedPreferences getSharedPreferences(String preferenceName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public void clearAllSharedPreferences(Context context, String preferenceName) {
        SharedPreferences settings = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }
}
