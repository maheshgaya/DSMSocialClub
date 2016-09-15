package com.duse.android.dsmsocialclub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duse.android.dsmsocialclub.R;

/**
 * Created by Mahesh Gaya on 9/15/16.
 */
public class SettingsAdapter extends BaseAdapter{
    private Context mContext;
    private String[] mSettingsArray;

    public SettingsAdapter(Context context, String[] settingsArray){
        mContext = context;
        mSettingsArray = settingsArray;

    }
    @Override
    public int getCount() {
        return mSettingsArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mSettingsArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.settings_item_list, null);
            holder = new ViewHolder();
            holder.settingsView = (TextView)convertView.findViewById(R.id.item_settings);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.settingsView.setText(mSettingsArray[position].toString());
        return convertView;
    }

    private static class ViewHolder {
        TextView settingsView;
    }
}
