package com.martinhan.xposeddemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class FileProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(@NonNull  Uri uri,   String[] projection,   String selection,   String[] selectionArgs,   String sortOrder) {
        return null;
    }



    @Override
    public String getType(@NonNull  Uri uri) {
        return null;
    }



    @Override
    public Uri insert(@NonNull  Uri uri,   ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull  Uri uri,   String selection,   String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull  Uri uri,   ContentValues values,   String selection,   String[] selectionArgs) {
        return 0;
    }

    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        System.out.println("method:" + method);
        SharedPreferences sp = getContext().getSharedPreferences("default", Context.MODE_PRIVATE);
        Bundle bundle = new Bundle();
        bundle.putString("region",sp.getString("region","默认"));
        return bundle;
    }
}
