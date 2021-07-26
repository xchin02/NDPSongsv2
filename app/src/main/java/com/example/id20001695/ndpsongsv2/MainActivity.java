package com.example.id20001695.ndpsongsv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etSingers, etYear;
    Button btnInsert, btnShow;
    RadioGroup rg;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle2);
        etSingers = findViewById(R.id.etSingers2);
        etYear = findViewById(R.id.etYear2);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShow);
        rg = findViewById(R.id.radioGroup);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(etTitle.getText().toString(), etSingers.getText().toString(), Integer.parseInt(etYear.getText().toString()), getStars());
                if(inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowNDPSongs.class);
                startActivity(intent);
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