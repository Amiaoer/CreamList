package com.example.a73645.listviewtesst;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 73645 on 2018/1/25.
 */

public class DayLineAdapter extends ArrayAdapter<dayline> {
    private  int resourceId;
    public DayLineAdapter(Context context, int textViewResourceId, List<dayline> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @SuppressLint("CutPasteId")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        dayline dayline = getItem(position);

        View view;
        ViewHolder viewHolder;
        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.daylineImage = (ImageView) view.findViewById(R.id.dayline_image);
            viewHolder.daylineName = (TextView) view.findViewById(R.id.dayline_name);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.daylineImage.setImageResource(dayline.getImageId());
        viewHolder.daylineName.setText(dayline.getName());

        ImageView daylineImage = (ImageView) view.findViewById(R.id.dayline_image);
        TextView daylineName = (TextView) view.findViewById(R.id.dayline_name);
        daylineImage.setImageResource(dayline.getImageId());
        daylineName.setText(dayline.getName());
        return view;
    }

    private class ViewHolder {
        ImageView daylineImage;
        TextView daylineName;
    }
}
