package sg.edu.rp.c346.id20037987.l09_ps_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewSongs extends AppCompatActivity {

    Button btn5Stars;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    ListView lv;

    @Override
    protected void onResume() {
        super.onResume();
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();
        //btn5Stars.performClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_songs);

        lv = findViewById(R.id.lvSongs);
        btn5Stars = findViewById(R.id.buttonFilter);

        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllSongs();
        dbh.close();


        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song songs = al.get(position);
                Intent i = new Intent(ViewSongs.this, EditDeleteSongs.class);
                //i.putExtra("song", al.get(position));
                i.putExtra("song", songs);
                //startActivityForResult(i, requestCode);
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ViewSongs.this);
                al.clear();
                al.addAll(dbh.getAllSongsByStars(5));
                aa.notifyDataSetChanged();
            }
        });

    }
}