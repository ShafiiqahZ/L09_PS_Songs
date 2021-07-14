package sg.edu.rp.c346.id20037987.l09_ps_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etSinger;
    EditText etYear;
    RadioGroup rg;
    Button insertBtn;
    Button showListBtn;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.radioGroup);
        insertBtn = findViewById(R.id.buttonInsert);
        showListBtn = findViewById(R.id.buttonShow);
        

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String singer = etSinger.getText().toString().trim();

                if(title.length() == 0 || singer.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete Data", Toast.LENGTH_LONG).show();
                }

                String year = etYear.getText().toString();
                int yearInt = 0;
                try {
                    yearInt = Integer.valueOf(year);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Entered year is Invalid", Toast.LENGTH_LONG).show();
                    return;
                }
                //int chosenRb = rg.getCheckedRadioButtonId();
                int stars = getStar();
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertSong(title, singer, yearInt, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Successfully Inserted", Toast.LENGTH_LONG).show();

                etTitle.setText("");
                etSinger.setText("");
                etYear.setText("");
                //long inserted_id = dbh.insertSong(title, singer);

                /*if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }*/
                //etContent.setText("");
            }
        });

        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goShow = new Intent(MainActivity.this, ViewSongs.class);
                startActivity(goShow);
            }
        });

    }

        private int getStar() {
            int stars = 1;
            //String star = "";
            switch(rg.getCheckedRadioButtonId()) {
                case R.id.radioButton1:
                    stars = 1;
                    //star = "*";
                    break;
                case R.id.radioButton2:
                    stars = 2;
                    //star = "**";
                    break;
                case R.id.radioButton3:
                    stars = 3;
                    //star = "***";
                    break;
                case R.id.radioButton4:
                    stars = 4;
                    //star = "****";
                    break;
                case R.id.radioButton5:
                    stars = 5;
                    //star = "*****";
                    break;
            }
            //String star1 = stars + "stars";
            //return Integer.parseInt(star1);
            return stars;
        }
}