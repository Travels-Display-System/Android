package com.zhao.ui_basic.Utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;

public class Utils {
    public static boolean isEmpty(Object ob) {
        if (ob == null) {
            return true;
        }
        return false;
    }

    public static boolean isListEmpty(List<?> list) {
        if ( list != null&& list.size() != 0) {
            return false;
        }
        return true;
    }

    public static String getDataUrl(Context context, Uri uri) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, null, null,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }
}
