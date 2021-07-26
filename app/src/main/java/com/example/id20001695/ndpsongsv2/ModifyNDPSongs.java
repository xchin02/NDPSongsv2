package com.example.id20001695.ndpsongsv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ModifyNDPSongs extends AppCompatActivity {
    EditText etID, etTitle, etSinger, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_ndpsongs);

        etID = findViewById(R.id.etSongID2);
        etTitle = findViewById(R.id.etTitle2);
        etSinger = findViewById(R.id.etSingers2);
        etYear = findViewById(R.id.etYear2);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        rg = findViewById(R.id.radioGroup);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(String.valueOf(data.getId()));
        etID.setEnabled(false);
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));


        int stars = data.getStars();

        if(stars == 1) {
            rb1.setChecked(true);
        }
        else if (stars == 2) {
            rb2.setChecked(true);
        }
        else if (stars == 3) {
            rb3.setChecked(true);
        }
        else if (stars == 4) {
            rb4.setChecked(true);
        }
        else if (stars == 5) {
            rb5.setChecked(true);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = Integer.parseInt(etYear.getText().toString());
                DBHelper dbh = new DBHelper(ModifyNDPSongs.this);
                data.setTitle(etTitle.getText().toString()); //Note Class Method
                data.setSingers(etSinger.getText().toString());
                data.setYear(year);
                data.setStars(getStars());
                dbh.updateSong(data); //DBHelper Class Method
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyNDPSongs.this);
                dbh.deleteSong(data.getId());
                dbh.close();
            }
        });
    }
    private int getStars() {
        int stars = 1;
        if (rg.getCheckedRadioButtonId() == R.id.radioButton1) {
            stars = 1;
        }
        else if (rg.getCheckedRadioButtonId() == R.id.radioButton2) {
            stars = 2;
        }
        else if (rg.getCheckedRadioButtonId() == R.id.radioButton3) {
            stars = 3;
        }
        else if (rg.getCheckedRadioButtonId() == R.id.radioButton4) {
            stars = 4;
        }
        else if (rg.getCheckedRadioButtonId() == R.id.radioButton5) {
            stars = 5;
        }
        return stars;
    }
}