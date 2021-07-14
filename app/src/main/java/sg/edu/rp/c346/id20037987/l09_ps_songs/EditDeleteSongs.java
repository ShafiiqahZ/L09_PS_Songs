package sg.edu.rp.c346.id20037987.l09_ps_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.net.Inet4Address;

public class EditDeleteSongs extends AppCompatActivity {

    EditText etId, etTitle, etSinger, etYear;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_songs);

        etId = findViewById(R.id.etId);
        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        etSinger = findViewById(R.id.etSinger);
        rg = findViewById(R.id.radioGroup);

        Intent i = getIntent();
        final Song currentSong = (Song) i.getSerializableExtra("song");

        etId.setText(currentSong.get_id()+"");
        etTitle.setText(currentSong.getTitle());
        etSinger.setText(currentSong.getSingers());
        etYear.setText(currentSong.getYear()+"");
        switch (currentSong.getStar()) {
            case 1: rb1.setChecked(true);
                break;
            case 2: rb2.setChecked(true);
                break;
            case 3: rb3.setChecked(true);
                break;
            case 4: rb4.setChecked(true);
                break;
            case 5: rb5.setChecked(true);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditDeleteSongs.this);
                currentSong.setTitle(etTitle.getText().toString().trim());
                currentSong.setSingers(etSinger.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e) {
                    Toast.makeText(EditDeleteSongs.this, "Entered input is Invalid", Toast.LENGTH_LONG).show();
                    return;
                }
                currentSong.setYear(year);

                int selectedrb = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedrb);
                currentSong.setStar(Integer.parseInt(rb.getText().toString()));
                int result = dbh.updateSong(currentSong);
                if (result>0) {
                    Toast.makeText(EditDeleteSongs.this, "Song have been updated", Toast.LENGTH_LONG).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(EditDeleteSongs.this, "Song update failed", Toast.LENGTH_LONG).show();
                }
                /*dbh.updateNote(data);
                dbh.close();

                finish();*/
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditDeleteSongs.this);
                dbh.deleteSong(currentSong.get_id());

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}