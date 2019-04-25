package com.example.notificationscheduler;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationListAdapter extends ArrayAdapter<Notification> {

    private Context context;

    public NotificationListAdapter(ArrayList<Notification> data, Context context) {
        super(context, R.layout.notification_row_item, data);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).
                    inflate(R.layout.notification_row_item, parent, false);
        }

        // get current item to be displayed
        Notification currentItem = getItem(position);

        TextView notificationTitle = convertView.findViewById(R.id.notification_title);
        TextView notificationDate = convertView.findViewById(R.id.notification_date);
        TextView notificationTime = convertView.findViewById(R.id.notification_time);

        notificationTitle.setText(currentItem.getTitle());
        notificationDate.setText(currentItem.getDate());
        notificationTime.setText(currentItem.getTime());


        // returns the view for the current row
        return convertView;
    }
}

