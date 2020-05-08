package ru.art241111.clubolympus.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import ru.art241111.clubolympus.R;
import ru.art241111.clubolympus.data.ClubOlympusContract.*;

public class MembersCursorAdapter extends CursorAdapter {

    public MembersCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.member_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv_firstName = view.findViewById(R.id.tv_firstName);
        TextView tv_lastName = view.findViewById(R.id.tv_lastName);
        TextView tv_sport = view.findViewById(R.id.tv_sport);

        String firstName = cursor.getString(cursor.getColumnIndex(MemberEntry.COLUMN_FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(MemberEntry.COLUMN_LAST_NAME));
        String sport = cursor.getString(cursor.getColumnIndex(MemberEntry.COLUMN_SPORT));

        tv_firstName.setText(firstName);
        tv_lastName.setText(lastName);
        tv_sport.setText(sport);

    }
}
