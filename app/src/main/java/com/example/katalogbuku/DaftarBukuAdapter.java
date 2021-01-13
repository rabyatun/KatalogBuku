package com.example.katalogbuku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.katalogbuku.model.Buku;

import java.util.List;

public class DaftarBukuAdapter extends ArrayAdapter<Buku> {
    Context context;

    public DaftarBukuAdapter(@NonNull Context context, @NonNull List<Buku> objects) {
        super(context, R.layout.row_buku, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txTgl;
        TextView txJudul;
        TextView txPengarang;
        TextView txTahunTerbit;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Buku tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_buku,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txTgl = convertView.findViewById(R.id.row_tx_tgl_buku);
            viewHolder.txPengarang = convertView.findViewById(R.id.row_tx_pengarang_buku);
            viewHolder.txJudul = convertView.findViewById(R.id.row_tx_judul_buku);
            viewHolder.txTahunTerbit = convertView.findViewById(R.id.row_tx_tahunterbit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txTgl.setText(GenericUtility.DATE_FORMAT.format(tr.getTanggal()));
        viewHolder.txPengarang.setText(tr.getPengarang());
        viewHolder.txJudul.setText(tr.getJudulbuku());
        if (tr.getJenis().equals(Buku.KOMIK)) {
            viewHolder.txTahunTerbit.setText(tr.getTahunterbit());
        } else {
            viewHolder.txTahunTerbit.setText((tr.getTahunterbit()));
        }
        return convertView;
    }
}
