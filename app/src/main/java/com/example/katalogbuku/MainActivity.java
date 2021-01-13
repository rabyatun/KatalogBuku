package com.example.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katalogbuku.model.Buku;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnTambahBuku;
    ImageButton btnChangeUserName;
    ListView lvDaftarBuku;
    TextView txNoData, txUsername, txSaldo;
    com.example.katalogbuku.DaftarBukuAdapter adapter;
    List<Buku> daftarBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadDataBuku();
        setupListview();
    }

    private void inisialisasiView() {
        btnTambahBuku = findViewById(R.id.btn_add_buku);
        btnTambahBuku.setOnClickListener(view -> bukaFormTambahBuku());
        btnChangeUserName = findViewById(R.id.btn_change_username);
        btnChangeUserName.setOnClickListener(view -> changeUserName());
        lvDaftarBuku = findViewById(R.id.lv_buku);
        txNoData = findViewById(R.id.tx_nodata);
        txUsername = findViewById(R.id.tx_user_name);
        txUsername.setText(SharedpreferenceUtility.getUserName(this));
        txSaldo = findViewById(R.id.tx_saldo);
    }

    private void setupListview() {
        adapter = new com.example.katalogbuku.DaftarBukuAdapter(this, daftarBuku);
        lvDaftarBuku.setAdapter(adapter);
    }

    private void loadDataBuku() {
        daftarBuku = SharedpreferenceUtility.getAllBuku(this);
        double saldo = 0;
        if (daftarBuku.size()>0) {
            txNoData.setVisibility(View.GONE);
            // hitung
        }
    }

    private void refreshListView() {
        adapter.clear();
        loadDataBuku();
        adapter.addAll(daftarBuku);
    }

    private void bukaFormTambahBuku() {
        Intent intent = new Intent(this, com.example.katalogbuku.FormBukuActivity.class);
        startActivity(intent);
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedpreferenceUtility.saveUserName(getApplicationContext(),input.getText().toString());
                Toast.makeText(getApplicationContext(),"Nama Buku berhasil disimpan",Toast.LENGTH_SHORT).show();
                txUsername.setText(SharedpreferenceUtility.getUserName(getApplicationContext()));
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }
}