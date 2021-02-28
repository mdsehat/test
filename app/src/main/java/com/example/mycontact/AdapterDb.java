package com.example.mycontact;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterDb extends ArrayAdapter {
    private List<Contact> ctList;
    private DbHelper dbHelper;

    public AdapterDb(@NonNull Activity activity, @NonNull List<Contact> ctList) {
        super(activity, R.layout.adapter_layout, ctList);
        this.ctList = ctList;
        dbHelper = new DbHelper(activity);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_layout, (ViewGroup) convertView, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.fill(position);
        return convertView;
    }


    private class ViewHolder {
        public TextView tvName, tvNumber;

        public ViewHolder(View conterView) {
            tvName = conterView.findViewById(R.id.tvName);
            tvNumber = conterView.findViewById(R.id.tvNumber);
        }

        public void fill(final int position) {
            tvName.setText(ctList.get(position).getName());
            tvNumber.setText(ctList.get(position).getNumber());
        }
    }


}
