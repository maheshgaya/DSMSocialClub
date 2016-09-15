package com.duse.android.dsmsocialclub.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duse.android.dsmsocialclub.R;

/**
 * Created by Mahesh Gaya on 9/15/16.
 */
public class SettingsAdapter extends BaseAdapter{
    private Context mContext;
    private String[] mSettingsArray;

    private final String TAG = getClass().getSimpleName();

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
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.settingsView.setText(mSettingsArray[position].toString());
        Log.d(TAG, "getView: " + mSettingsArray[position]);
        if (position == 1){
            Log.d(TAG, "getView: " + convertView.getResources().getString(R.string.settings_item_about));
            holder.settingsImageView.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_info, null));
        } else if (position == 0){
            holder.settingsImageView.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_label, null));
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView settingsView;
        ImageView settingsImageView;
        public ViewHolder(View view){
            settingsView = (TextView)view.findViewById(R.id.item_settings);
            settingsImageView = (ImageView)view.findViewById(R.id.image_settings);
        }
    }
}
