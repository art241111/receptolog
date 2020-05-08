package ru.art241111.clubolympus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import ru.art241111.clubolympus.adapters.MembersCursorAdapter;
import ru.art241111.clubolympus.data.ClubOlympusContract.*;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int MEMBER_LOADER = 123;
    MembersCursorAdapter membersCursorAdapter;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.lw_member);
        membersCursorAdapter = new MembersCursorAdapter(this,null,false);
        listView.setAdapter(membersCursorAdapter);

        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri;
                Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
                Uri currentMemberUri = ContentUris
                        .withAppendedId(MemberEntry.CONTENT_URI, id);
                intent.setData(currentMemberUri);
                startActivity(intent);
            }
        });

        getSupportLoaderManager().initLoader(MEMBER_LOADER,null,this);

    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_FIRST_NAME,
                MemberEntry.COLUMN_LAST_NAME,
                MemberEntry.COLUMN_SPORT };

        return new CursorLoader(this,
                MemberEntry.CONTENT_URI,
                projection,
                null,
                null,
                null );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        membersCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        membersCursorAdapter.swapCursor(null);
    }
}
