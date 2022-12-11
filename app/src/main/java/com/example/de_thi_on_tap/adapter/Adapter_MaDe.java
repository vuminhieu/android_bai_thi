package com.example.de_thi_on_tap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.de_thi_on_tap.R;
import com.example.de_thi_on_tap.model.Taxi_MaDe;

import java.util.List;

public class Adapter_MaDe extends ArrayAdapter<Taxi_MaDe> {
    private Context context;
    private int resource;
    List<Taxi_MaDe> taxi_maDeList;

    public Adapter_MaDe(@NonNull Context context, int resource, @NonNull List<Taxi_MaDe> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.taxi_maDeList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dong_taxi, parent, false);
            viewHolder.textViewSoXe = convertView.findViewById(R.id.tv_so_xe);
            viewHolder.textViewQuangDuong = convertView.findViewById(R.id.tv_quang_duong);
            viewHolder.textViewTongTien = convertView.findViewById(R.id.tv_tong_tien);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Taxi_MaDe taxi_maDe = taxi_maDeList.get(position);
        viewHolder.textViewSoXe.setText(String.valueOf(taxi_maDe.getSoXe()));
        viewHolder.textViewQuangDuong.setText(String.valueOf(taxi_maDe.getQuangDuong()));
        viewHolder.textViewTongTien.setText(String.valueOf(taxi_maDe.GetTongTien()));

        return convertView;
    }

    public class ViewHolder{
        TextView textViewSoXe, textViewQuangDuong, textViewTongTien;
    }
}
