package se.gu.projekt.theciv5rankapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import se.gu.projekt.theciv5rankapp.classes.Game;
import se.gu.projekt.theciv5rankapp.classes.InGamePlayer;
import se.gu.projekt.theciv5rankapp.classes.Player;

import static se.gu.projekt.theciv5rankapp.classes.Game.victoryType.*;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private Player profilePlayer;
    private ArrayList<InGamePlayer> testSubjects = new ArrayList<>();
    private ArrayList<Game> testGames = new ArrayList<>();
    private ArrayList<String> test = new ArrayList<>();
    public ArrayAdapter secondAdapter;
    public String[] countries;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_profile_layout);

        Log.d(TAG, "onCreate: Profile created!");

        profilePlayer = (Player) getIntent().getSerializableExtra("passToProfile");
        Log.d(TAG, "onCreate: Got profile: " + profilePlayer);

        ListView recentGamesView = (ListView) findViewById(R.id.profile_recent_games);
        TextView name = (TextView) findViewById(R.id.profile_name);
        TextView current_rank = (TextView) findViewById(R.id.current_rank);
        TextView current_rating = (TextView) findViewById(R.id.current_rating);
        TextView steam_id = (TextView) findViewById(R.id.steam_id);

        name.setText(profilePlayer.playerName());
        current_rank.setText(Integer.toString(profilePlayer.rank()));
        current_rating.setText(Integer.toString(profilePlayer.rank()));
        steam_id.setText(profilePlayer.steamId());


        testSubjects.add(new InGamePlayer("Harald", 1, 1, "SteamID1", "Aztec", 1));
        testSubjects.add(new InGamePlayer("Halvard", 2, 2, "SteamID2", "Sweden", 2));
        testSubjects.add(new InGamePlayer("Horace", 3, 3, "SteamID3", "France", 3));
        testSubjects.add(new InGamePlayer("Hans", 4, 4, "SteamID4", "Egypt", 4));
        testSubjects.add(new InGamePlayer("Hugo", 5, 5, "SteamID5", "Babylon", 5));
        testSubjects.add(new InGamePlayer("Harry", 6, 6, "SteamID6", "Denmark", 6));
        Log.d(TAG, "onCreate: Created test subjects");

        testGames.add(new Game(1, testSubjects,"2019-01-01", SCIENCE));
        testGames.add(new Game(2, testSubjects,"2019-01-02", DIPLOMACY));
        testGames.add(new Game(3, testSubjects,"2019-01-03", TOURISM));
        testGames.add(new Game(4, testSubjects,"2019-01-04", DOMINATION));
        testGames.add(new Game(1, testSubjects,"2019-01-01", SCIENCE));
        testGames.add(new Game(2, testSubjects,"2019-01-02", DIPLOMACY));
        testGames.add(new Game(3, testSubjects,"2019-01-03", TOURISM));
        testGames.add(new Game(4, testSubjects,"2019-01-04", DOMINATION));
        testGames.add(new Game(1, testSubjects,"2019-01-01", SCIENCE));
        testGames.add(new Game(2, testSubjects,"2019-01-02", DIPLOMACY));
        testGames.add(new Game(3, testSubjects,"2019-01-03", TOURISM));
        testGames.add(new Game(4, testSubjects,"2019-01-04", DOMINATION));
        testGames.add(new Game(1, testSubjects,"2019-01-01", SCIENCE));
        testGames.add(new Game(2, testSubjects,"2019-01-02", DIPLOMACY));
        testGames.add(new Game(3, testSubjects,"2019-01-03", TOURISM));
        testGames.add(new Game(4, testSubjects,"2019-01-04", DOMINATION));
        Log.d(TAG, "onCreate: Created test games");

        secondAdapter = new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, testGames);
        recentGamesView.setAdapter(secondAdapter);

        recentGamesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), SelectedGameActivity.class);
                i.putExtra("passToSelectedGame", testGames.get(position));

                Log.d(TAG, "onItemClick: Game sent: " + position + " :" + testGames.get(position));
                startActivity(i);

            }
        });
    }
}
