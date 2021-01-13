package com.example.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.katalogbuku.model.Buku;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

public class FormBukuActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout tilJudul,tilPengarang, tilTahunTerbit;
    EditText edtTgl;
    Spinner spJenisBuku;
    Date tanggalBuku;
    final String[] tipeBuku = {Buku.KOMIK, Buku.NOVEL};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_buku);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        edtTgl = findViewById(R.id.edt_tgl);
        edtTgl.setOnClickListener(view -> pickDate());
        tilJudul = findViewById(R.id.til_judul_buku);
        tilPengarang = findViewById(R.id.til_pengarang_buku);
        tilTahunTerbit = findViewById(R.id.til_tahunterbit);
        spJenisBuku = findViewById(R.id.spn_jenis);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipeBuku
        );
        spJenisBuku.setAdapter(adapter);
        spJenisBuku.setSelection(0);
    }

    private void simpan() {
        if (isDataValid()) {
            Buku tr = new Buku();
            tr.setJudulbuku(tilJudul.getEditText().getText().toString());
            tr.setPengarang(tilPengarang.getEditText().getText().toString());
            tr.setTahunterbit(tilTahunTerbit.getEditText().getText().toString());
            tr.setJenis(spJenisBuku.getSelectedItem().toString());
            tr.setTanggal(tanggalBuku);
            SharedpreferenceUtility.addBuku(this,tr);
            Toast.makeText(this,"Data berhasil disimpan",Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (tilJudul.getEditText().getText().toString().isEmpty()
                || tilPengarang.getEditText().getText().toString().isEmpty()
                || spJenisBuku.getSelectedItem().toString().isEmpty()
        ) {
            Toast.makeText(this,"Lengkapi semua isian",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /*
        Dipanggil saat user ingin mengisi tanggal transaksi
        Menampilkan date picker dalam popup dialog
     */
    private void pickDate() {
        final Calendar c = Calendar.getInstance();
        int thn = c.get(Calendar.YEAR);
        int bln = c.get(Calendar.MONTH);
        int tgl = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (DatePickerDialog.OnDateSetListener) (view, yyyy, mm, dd) -> {
                    edtTgl.setText(dd + "-" + (mm + 1) + "-" + yyyy);
                    c.set(yyyy,mm,dd);
                    tanggalBuku = c.getTime();
                },
                thn, bln, tgl);
        datePickerDialog.show();
    }

}