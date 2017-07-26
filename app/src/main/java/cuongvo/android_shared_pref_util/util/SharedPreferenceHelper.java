package cuongvo.android_shared_pref_util.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by cuongvo.
 */
public class SharedPreferenceHelper<T> {

    private String mPreferenceName;
    private String mKey;
    private T mValue;
    
    private static Context mContext;
    private static SharedPreferenceHelper mSharedPreferenceHelper;

    private SharedPreferenceHelper() {
    }

    public static SharedPreferenceHelper getInstance(Context context) {
        mContext = context;

        if (mSharedPreferenceHelper == null) {
            mSharedPreferenceHelper = new SharedPreferenceHelper();
        }
        return mSharedPreferenceHelper;

    }

    private String getKey() {
        return mKey;
    }

    private void setKey(String key) {
        this.mKey = key;
    }

    private T getValue() {
        return mValue;
    }

    private void setValue(T value) {
        this.mValue = value;
    }

    private boolean addPreference() {
        boolean result = true;

        try {
            SharedPreferences sharedPreferences = getSharedPreferences(mPreferenceName);
            buildPreference(sharedPreferences);
        } catch (Exception ex) {
            result = false;
        }

        return result;
    }

    public void putString(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Key value can not be empty");
        }

        if (TextUtils.isEmpty(value)) {
            throw new IllegalArgumentException("Value can not be empty, It could be an empty string or NULL");
        }

        this.setKey(key);
        this.setValue((T) value);

        addPreference();
    }

    private T getPreference(String key, Class<T> classType) {
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

    public boolean isContainKey(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(mPreferenceName);
        return sharedPreferences.contains(key);
    }

    public SharedPreferences getSharedPreferences(String preferenceName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public void clearAllSharedPreferences(Context context, String preferenceName) {
        SharedPreferences settings = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }

    public String getString(String key) {
        return (String) getPreference(key, (Class<T>) String.class);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) getPreference(key, (Class<T>) Boolean.class);
    }

    public Integer getInteger(String key) {
        return (Integer) getPreference(key, (Class<T>) Integer.class);
    }

    public Float getFloat(String key) {
        return (Float) getPreference(key, (Class<T>) Integer.class);
    }

    public Long getLong(String key) {
        return (Long) getPreference(key, (Class<T>) Long.class);
    }

    public String getPreferenceName() {
        return mPreferenceName;
    }

    public SharedPreferenceHelper setPreferenceName(String preferenceName) {
        this.mPreferenceName = preferenceName;
        return this.mSharedPreferenceHelper;
    }

}
