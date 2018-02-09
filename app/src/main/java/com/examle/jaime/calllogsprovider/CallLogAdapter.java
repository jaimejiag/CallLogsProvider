package com.examle.jaime.calllogsprovider;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by usuario on 9/02/18.
 */

public class CallLogAdapter extends CursorAdapter {

    public CallLogAdapter(Context context) {
        super(context, null, FLAG_REGISTER_CONTENT_OBSERVER);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_call, viewGroup, false);
        CallLogHolder holder = new CallLogHolder();

        holder.txvNumber = view.findViewById(R.id.txv_number);
        holder.txvdate = view.findViewById(R.id.txv_date);
        holder.txvDuration = view.findViewById(R.id.txv_duration);
        holder.txvType = view.findViewById(R.id.txv_type);

        view.setTag(holder);
        return view;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CallLogHolder holder = (CallLogHolder) view.getTag();
        String type = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(Long.parseLong(cursor.getString(2)));

        switch (cursor.getInt(4)) {
            case CallLog.Calls.INCOMING_TYPE:
                type = "Llamada entrante";
                break;

            case CallLog.Calls.OUTGOING_TYPE:
                type = "Llamada saliente";
                break;

            case CallLog.Calls.MISSED_TYPE:
                type = "Llamada perdida";
                break;
        }

        holder.txvNumber.setText(cursor.getString(1));
        holder.txvdate.setText(format.format(date));
        holder.txvDuration.setText(cursor.getString(3));
        holder.txvType.setText(type);
    }


    private class CallLogHolder {
        TextView txvNumber;
        TextView txvdate;
        TextView txvDuration;
        TextView txvType;
    }
}
