package ru.art241111.clubolympus.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static ru.art241111.clubolympus.data.ClubOlympusContract.*;

public class OlympusContentProvider extends ContentProvider {
    OlympusDbHelper dbHelper;

    public static final int MEMBERS = 111;
    public static final int MEMBERS_ID = 222;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY,PATH_MEMBERS,MEMBERS);
        uriMatcher.addURI(AUTHORITY,PATH_MEMBERS + "/#",MEMBERS_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new OlympusDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;

        int match = uriMatcher.match(uri);
        switch (match){
            case MEMBERS:
                cursor = db.query(MemberEntry.TABLE_NAME, projection,
                        selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case MEMBERS_ID:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(MemberEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);

                break;
            default:
                throw new IllegalArgumentException("Can't query incorrect URI");
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        String firstName = values.getAsString(MemberEntry.COLUMN_FIRST_NAME);
        if(firstName == null){
            throw new IllegalArgumentException("You have to input first name");
        }

        String lastName = values.getAsString(MemberEntry.COLUMN_LAST_NAME);
        if(lastName == null){
            throw new IllegalArgumentException("You have to input last name" );
        }

        Integer gender = values.getAsInteger(MemberEntry.COLUMN_GENDER);
        if (gender == null || !(gender == MemberEntry.GENDER_UNKNOWN || gender ==
                MemberEntry.GENDER_MALE || gender == MemberEntry.GENDER_FEMALE)){
            throw new IllegalArgumentException("You have to input correct gender");
        }

        String sport = values.getAsString(MemberEntry.COLUMN_SPORT);
        if(sport == null){
            throw new IllegalArgumentException("You have to input sport " + uri);
        }




        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        switch (match){
            case MEMBERS:
                long id = db.insert(MemberEntry.TABLE_NAME, null, values);
                if(id == -1) {
                    Log.e("insertMethod", "Insertion of data in the table failed for " + uri);
                    return null;
                }
                getContext().getContentResolver().notifyChange(uri,null);
                return ContentUris.withAppendedId(uri,id);
            default:
                throw new IllegalArgumentException("Insertion of data in the table failed for " + uri);
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsDelete;

        int match = uriMatcher.match(uri);
        switch (match){
            case MEMBERS:
                rowsDelete = db.delete(MemberEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case MEMBERS_ID:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDelete = db.delete(MemberEntry.TABLE_NAME,selection,selectionArgs);

                break;
            default:
                throw new IllegalArgumentException("Can't delete this URI" + uri);
        }
        if(rowsDelete != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDelete;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(values.containsKey(MemberEntry.COLUMN_FIRST_NAME)){
            String firstName = values.getAsString(MemberEntry.COLUMN_FIRST_NAME);
            if(firstName == null){
                throw new IllegalArgumentException("You have to input first name");
            }
        }
        if(values.containsKey(MemberEntry.COLUMN_LAST_NAME)) {
            String lastName = values.getAsString(MemberEntry.COLUMN_LAST_NAME);
            if (lastName == null) {
                throw new IllegalArgumentException("You have to input last name");
            }
        }
        if(values.containsKey(MemberEntry.COLUMN_GENDER)) {

            Integer gender = values.getAsInteger(MemberEntry.COLUMN_GENDER);
            if (gender == null || !(gender == MemberEntry.GENDER_UNKNOWN
                    || gender == MemberEntry.GENDER_MALE
                    || gender == MemberEntry.GENDER_FEMALE)) {
                throw new IllegalArgumentException("You have to input correct gender");
            }
        }
        if(values.containsKey(MemberEntry.COLUMN_SPORT)) {
            String sport = values.getAsString(MemberEntry.COLUMN_SPORT);
            if (sport == null) {
                throw new IllegalArgumentException("You have to input sport " + uri);
            }
        }

        int match = uriMatcher.match(uri);
        int rowsUpdate;
        switch (match){
            case MEMBERS:
                rowsUpdate = db.update(MemberEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            case MEMBERS_ID:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                rowsUpdate = db.update(MemberEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Can't update this URI" + uri);
        }

        if(rowsUpdate != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdate;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match){
            case MEMBERS:

                return MemberEntry.CONTENT_MULTIPLE_ITEM;
            case MEMBERS_ID:
                return MemberEntry.CONTENT_SINGLE_ITEM;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

}
