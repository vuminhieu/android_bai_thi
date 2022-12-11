package com.example.de_thi_on_tap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.de_thi_on_tap.adapter.Adapter_MaDe;
import com.example.de_thi_on_tap.database.DBManager;
import com.example.de_thi_on_tap.model.Taxi_MaDe;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listViewDanhSach;
    Adapter_MaDe customAdapter;
    List<Taxi_MaDe> taxi_maDes;
    DBManager dbManager = new DBManager(this);
    public void mapping() {
        listViewDanhSach = (ListView) findViewById(R.id.lv_danh_sach);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new Adapter_MaDe(this, R.layout.dong_taxi, taxi_maDes);
            listViewDanhSach.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            listViewDanhSach.setSelection(customAdapter.getCount() - 1);
        }
    }

    public void updateListTaxi() {
        taxi_maDes.clear();
        taxi_maDes.addAll(dbManager.getAllTaxi());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        taxi_maDes = dbManager.getAllTaxi();
        setAdapter();
        registerForContextMenu(listViewDanhSach);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Taxi_MaDe taxi_maDe = taxi_maDes.get(i.position);
        if (item.getItemId() == R.id.item_sua) {
            String soxe = taxi_maDe.getSoXe();
            double quangduong = taxi_maDe.getQuangDuong();//list item title
            double dongia = taxi_maDe.getDonGia();//list item title
            int khuyenmai = taxi_maDe.getPhanTramKhuyenMai();//list item title
            Intent intent = new Intent(MainActivity.this, Activity_Sua.class);
            intent.putExtra("so_xe",  soxe );
            intent.putExtra("quang_duong",  quangduong );
            intent.putExtra("don_gia",  dongia );
            intent.putExtra("khuyen_mai", khuyenmai );

            startActivity(intent);
        }
        if (item.getItemId() == R.id.item_xoa) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Thông báo");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("Bạn có chắc chắn xoá không");
            alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int result = dbManager.Delete(taxi_maDe.getMa());
                    if (result > 0) {
                        updateListTaxi();
                        Toast.makeText(MainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Xoá bị lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alertDialog.setNegativeButton("Quay lai", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.show();
        }
        return super.onContextItemSelected(item);
    }
}