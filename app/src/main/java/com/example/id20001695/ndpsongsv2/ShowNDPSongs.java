package com.example.id20001695.ndpsongsv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowNDPSongs extends AppCompatActivity {
    Spinner spnFilter;
    ListView lv;
    Button btnShow;
    ArrayList<Song> al;
    CustomAdapter customSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ndpsongs);

        lv = findViewById(R.id.lv);
        btnShow = findViewById(R.id.buttonShow5Star);
        spnFilter = findViewById(R.id.spinner);

        al = new ArrayList<>();
        customSong = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(customSong);

        DBHelper dbh = new DBHelper(ShowNDPSongs.this);
        al.addAll(dbh.getAllSongs());
        customSong.notifyDataSetChanged();

        ArrayList<Integer> items = new ArrayList<>();
        items = dbh.getDate();
        dbh.close();

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, items);
        spnFilter.setAdapter(adapter);

        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(ShowNDPSongs.this);
                al.clear();
                al.addAll(dbh.getAllSongsByDate(Integer.parseInt(spnFilter.getSelectedItem().toString())));
                customSong.notifyDataSetChanged();
                dbh.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowNDPSongs.this);
                al.clear();
                al.addAll(dbh.getAllSongsByStars(5));
                customSong.notifyDataSetChanged();
                dbh.close();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Song data = al.get(position);
                Intent i = new Intent(ShowNDPSongs.this, ModifyNDPSongs.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }

}