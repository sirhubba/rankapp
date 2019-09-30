package se.gu.projekt.theciv5rankapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import se.gu.projekt.theciv5rankapp.classes.Game;

public class SelectedGameActivity extends Activity {

    private static final String TAG = "SelectedGameActivity";
    private Game selectedGame;
    private ArrayAdapter a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_game_layout);

        Log.d(TAG, "onCreate: SelectedGameActivity launched successfully!");

        selectedGame = (Game) getIntent().getSerializableExtra("passToSelectedGame");

        TextView game_date = (TextView) findViewById(R.id.game_date);
        TextView victory_type = (TextView) findViewById(R.id.victory_type);
        final ListView participating_players = (ListView) findViewById(R.id.participating_players);

        victory_type.setText(selectedGame.victoryType().toString());
        game_date.setText(selectedGame.dateSubmitted());
        a = new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, selectedGame.getPlayers());
        participating_players.setAdapter(a);

        /*participating_players.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("passToProfile", participating_players.get(position));
                Log.d(TAG, "onItemClick: Player sent: " + position + " :" + participating_players.get(position));
                startActivity(intent);
            }
        }*/
    }
}
