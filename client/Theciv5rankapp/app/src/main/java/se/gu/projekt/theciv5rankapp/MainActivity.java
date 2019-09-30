package se.gu.projekt.theciv5rankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import se.gu.projekt.theciv5rankapp.adapters.PlayerListAdapter;
import se.gu.projekt.theciv5rankapp.classes.Player;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public PlayerListAdapter adapter;
    public ArrayList<Player> playerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started!");
        ListView mListview = (ListView) findViewById(R.id.mainRankList);

        //Create player objects
        Player john = new Player("John", 1, 1, "SteamID1");
        Player jason = new Player("Jason", 2, 2, "SteamID2");
        Player james = new Player("James", 3, 3, "SteamID3");
        Player jonas = new Player("Jonas", 15, 15, "SteamID3");
        Player jonathan = new Player("Jonathan", 4, 4, "SteamID4");
        Player johanna = new Player("Johanna", 5, 5, "SteamID5");
        Player johan = new Player("Johan", 6, 6, "SteamID6");
        Player johannes = new Player("Johannes", 7, 7, "SteamID7");
        Player jesus = new Player("Jesus", 8, 8, "SteamID8");
        Player jesse = new Player("Jesse", 9, 9, "SteamID9");
        Player jeff = new Player("Jeff", 10, 10, "SteamID10");
        Player jack = new Player("Jack", 11, 11, "SteamID11");
        Player jackson = new Player("Jackson", 12, 12, "SteamID12");
        Player josef = new Player("Josef", 13, 13, "SteamID13");
        Player json = new Player("Json", 14, 14, "SteamID14");


        //Add players to arraylist
        playerList.add(john);
        playerList.add(jason);
        playerList.add(james);
        playerList.add(jonas);
        playerList.add(jonathan);
        playerList.add(johanna);
        playerList.add(johan);
        playerList.add(johannes);
        playerList.add(jesus);
        playerList.add(jesse);
        playerList.add(jeff);
        playerList.add(jack);
        playerList.add(jackson);
        playerList.add(josef);
        playerList.add(json);

        adapter = new PlayerListAdapter(this, R.layout.main_list_layout, playerList);
        mListview.setAdapter(adapter);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("passToProfile", playerList.get(position));
                Log.d(TAG, "onItemClick: Player sent: " + position + " :" + playerList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu, menu);

        //Koppla ihop sök-ikonen med koden för att söka
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
}
