package com.example.cowboy.sqlitemodule;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by Cowboy on 30.10.2017.
 */

public class DBContentProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static final String AUTHORITY = "com.slava.idetspat";
    static final String PERSON_PATH = "person";

    public static final Uri PERSONS_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + PERSON_PATH);

    private static final int URI_PERSON = 1;
    private static final int URI_PERSON_ID = 2;

    static final String PERSON_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + PERSON_PATH;

    static final String PERSON_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + PERSON_PATH;

    private DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    public boolean onCreate() {
        //address table
        // content://authority/path
        uriMatcher.addURI(AUTHORITY, PERSON_PATH, URI_PERSON);
        //addresses object
        // content://authority/path/1
        uriMatcher.addURI(AUTHORITY, PERSON_PATH + "/#", URI_PERSON_ID);

        dbHelper = new DBHelper(getContext());
        return true;
    }


    @Nullable
    @Override

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName;
        String id;

        switch (uriMatcher.match(uri)) {
            case URI_PERSON:
                //content//authority/path/1
                //   /1 = uri.getLastPathSegment();
                // /path = uri.getEncodedPath();
                // tableName = uri.getEncodedPath();
                tableName = Config.DB_TABLE;
                break;
            case URI_PERSON_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    selection = PersonContract._ID + " = " + id;
                } else {
                    selection = selection + " AND " + PersonContract._ID + " = " + id;
                }
                tableName = Config.DB_TABLE;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(tableName, projection, selection,
                selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(),
                PERSONS_CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_PERSON:
                return PERSON_CONTENT_TYPE;
            case URI_PERSON_ID:
                return PERSON_CONTENT_ITEM_TYPE;
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName;
        Uri resultUri;
        if (uriMatcher.match(uri) == URI_PERSON) {
            tableName = Config.DB_TABLE;
            resultUri = PERSONS_CONTENT_URI;
        }
        else
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();

        long indexRow_ID = db.insert(tableName, null, values);
        resultUri = ContentUris.withAppendedId(resultUri, indexRow_ID);

        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String id;
        String tableName;
        switch (uriMatcher.match(uri)) {
            case URI_PERSON:
                tableName = Config.DB_TABLE;
                break;
            case URI_PERSON_ID:
                tableName = Config.DB_TABLE;
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = PersonContract._ID + " = " + id;
                } else {
                    selection = selection + " AND " + PersonContract._ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(tableName, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String id;
        String tableName;
        switch (uriMatcher.match(uri)) {
            case URI_PERSON:
                tableName = uri.getEncodedPath();

                break;
            case URI_PERSON_ID:
                tableName = Config.DB_TABLE;
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = PersonContract._ID + " = " + id;
                } else {
                    selection = selection + " AND " + PersonContract._ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(tableName, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }
}
