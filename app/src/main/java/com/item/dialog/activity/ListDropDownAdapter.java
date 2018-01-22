package com.item.dialog.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.item.dialog.R;

import java.util.List;

/**
 * Created by Jie on 2018/1/20.
 */

public class ListDropDownAdapter extends BaseAdapter{
    private Context context;
    private List<String> list;
    private int checkItemPosition = 0 ;
    public void setCheckItem (int position){
        checkItemPosition = position;
        notifyDataSetChanged();
    }
    public ListDropDownAdapter (Context context , List<String> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_default_drop_down,null);
            holder = new ViewHolder();
            holder.mText = view.findViewById(R.id.text);
            // 将设置好的布局保存到缓存中，并将其设置在Tag中，以便后面方便取出Tag
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        fillValue(i,holder);
        return view;
    }
    private void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.mText.setText(list.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_selected));
                viewHolder.mText.setBackgroundResource(R.color.check_bg);
            } else {
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
                viewHolder.mText.setBackgroundResource(R.color.white);
            }
        }
    }
    private static class ViewHolder {
        private TextView mText;
    }
}
