package com.example.de_thi_on_tap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.de_thi_on_tap.database.DBManager;
import com.example.de_thi_on_tap.model.Taxi_MaDe;

public class Activity_Sua extends AppCompatActivity {
    DBManager dataBase = new DBManager(this);
    TextView textViewSoXe, textViewQuangDuong, textViewDonGia, textViewKhuyenMai;
    Button btnUpdate, btnBack;

    public void mapping() {
        textViewDonGia = (TextView) findViewById(R.id.edt_sua_don_gia);
        textViewSoXe = (TextView) findViewById(R.id.edt_sua_so_xe);
        textViewKhuyenMai = (TextView) findViewById(R.id.edt_sua_khuyen_mai);
        textViewQuangDuong = (TextView) findViewById(R.id.edt_sua_quang_duong);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnBack = (Button) findViewById(R.id.btn_huy);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        mapping();
        Intent intent = getIntent();
        int khuyenmai = intent.getIntExtra("khuyen_mai", 0);
        String soxe = intent.getStringExtra("so_xe");
        double quangduong = intent.getDoubleExtra("quang_duong", 0.0);
        double dongia = intent.getDoubleExtra("don_gia", 0.0);
        textViewSoXe.setText(soxe + "");
        textViewQuangDuong.setText(quangduong + "");
        textViewDonGia.setText(dongia+ "");
        textViewKhuyenMai.setText(khuyenmai+ "");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Taxi_MaDe taxi_maDe = new Taxi_MaDe(textViewSoXe.getText().toString(), Double.parseDouble(textViewQuangDuong.getText().toString()), Double.parseDouble(textViewDonGia.getText().toString()), Integer.parseInt(textViewKhuyenMai.getText().toString()));
                dataBase.updateTaxi(taxi_maDe);
                Intent it = new Intent(Activity_Sua.this, MainActivity.class);
                startActivity(it);
            }
        });

    }
}