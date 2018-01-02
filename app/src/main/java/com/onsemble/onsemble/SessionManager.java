package com.onsemble.onsemble;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "AndroidHiveLogin";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final  String USER_ID = "userId";
    private static final  String WORKING_STATUS = "workingStatus";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setUserId(int userId){
        editor.putInt(USER_ID,userId);
        editor.commit();
        Log.d(TAG, "Current user id: " + userId);
    }
    public int getUserId(){
        return pref.getInt(USER_ID, 0);
    }
    public void setWorkingStatus(boolean workingStatus){
        editor.putBoolean(WORKING_STATUS, workingStatus);
        editor.commit();
        Log.d(TAG, "Current working status: " + workingStatus);
    }
    public boolean getWorkingStatus(){
        return pref.getBoolean(WORKING_STATUS, false);
    }
}
