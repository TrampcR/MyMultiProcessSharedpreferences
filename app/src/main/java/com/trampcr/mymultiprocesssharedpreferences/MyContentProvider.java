package com.trampcr.mymultiprocesssharedpreferences;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    private static String EXTRA_TYPE = "type";
    private static String EXTRA_KEY = "key";
    private static String EXTRA_VALUE = "value";
    private static final int TYPE_STRING = 1;
    public static final Uri MY_CONTENT_PROVIDER_URI = Uri.parse("content://com.trampcr.provider");
    private static final int LENGTH_CONTENT_URI	= MY_CONTENT_PROVIDER_URI.toString().length() + 1;

    @Override
    public boolean onCreate() {
        return false;
    }

    public static void setStringValue(Context context, String key, String value) {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(EXTRA_TYPE, TYPE_STRING);
        contentvalues.put(EXTRA_KEY, key);
        contentvalues.put(EXTRA_VALUE, value);

        try {
            context.getContentResolver().insert(MY_CONTENT_PROVIDER_URI, contentvalues);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public static String getStringValue(Context context, String key, String defValue){
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(EXTRA_TYPE, TYPE_STRING);
        contentvalues.put(EXTRA_KEY, key);
        contentvalues.put(EXTRA_VALUE, defValue);

        Uri result;

        try {
            result = context.getContentResolver().insert(MY_CONTENT_PROVIDER_URI, contentvalues);
        } catch (Exception e) {
            return defValue;
        }

        if (result == null) {
            return defValue;
        }

        return result.toString().substring(LENGTH_CONTENT_URI);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs,  @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (values == null) {
            return null;
        }

        String res = "";
        int type = values.getAsInteger(EXTRA_TYPE);
        if (type == TYPE_STRING) {
            res += SharedPreferencesManager.getInstance(getContext()).getString(
                    values.getAsString(EXTRA_KEY), values.getAsString(EXTRA_VALUE));
        }

        return Uri.parse(MY_CONTENT_PROVIDER_URI.toString() + "/" + res);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        if (values == null) {
            return 0;
        }

        int type = values.getAsInteger(EXTRA_TYPE);
        if (type == TYPE_STRING) {
            SharedPreferencesManager.getInstance(getContext()).setString(
                    values.getAsString(EXTRA_KEY), values.getAsString(EXTRA_VALUE));
        }

        return 1;
    }
}
