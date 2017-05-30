package cuongvo.android_shared_pref_util.util;

/**
 * Created by cuongvo.
 */
public interface ISharedPreference<T> {
    public T getValue();
    public void setValue(T value);
    public void setKey(String name);
}
