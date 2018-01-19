package com.item.dialog.bittom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.item.dialog.R;

import java.util.List;

/**
 * Created by Jie on 2018/1/16.
 * 适配器
 */

public class SelectAdapter extends BaseAdapter {

    private List<ISelectAble> data;

    int selectedIndex = Selector.INDEX_INVALID;

    public SelectAdapter(List<ISelectAble> data) {
        this.data = data;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_select, parent, false);
            holder = new Holder();
            holder.textView = convertView.findViewById(R.id.textView);
            holder.imageView = convertView.findViewById(R.id.imageViewCheckMark);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        ISelectAble item = (ISelectAble) getItem(position);
        holder.textView.setText(item.getName());
        boolean checked = selectedIndex != Selector.INDEX_INVALID && data.get(selectedIndex).getId() == item.getId();
        holder.textView.setEnabled(!checked);
        holder.imageView.setVisibility(checked ? View.VISIBLE : View.GONE);
        return convertView;
    }

    class Holder {
        TextView textView;
        ImageView imageView;
    }
}
