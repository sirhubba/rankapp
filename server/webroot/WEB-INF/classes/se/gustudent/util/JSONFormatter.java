package se.gustudent.util;

import java.util.List;

import se.gustudent.domain.*;
import se.gustudent.util.*;
import org.json.*;

public class JSONFormatter{
  private static JSONObject JSONPlayer(Player player) {
    JSONObject JSONPlayer = new JSONObject();
    JSONPlayer.put("name", player.playerName());
    JSONPlayer.put("steam_id", player.steamId());
    JSONPlayer.put("rating", player.rating());

    JSONArray JSONGames = new JSONArray();
    for(Game g : player.gamesFeatured()){
      JSONObject JSONGame = new JSONObject();
      JSONGame.put("date", g.dateSubmitted());
      JSONGame.put("victory_type", g.victoryType());
      JSONGame.put("game_id", g.gameId());
      JSONGames.put(JSONGame);

      JSONArray JSONInGamePlayers = new JSONArray();
      for(InGamePlayer igp : g.playersParticipated()) {
        JSONObject JSONInGamePlayer = new JSONObject();
        JSONInGamePlayer.put("name", igp.playerName());
        JSONInGamePlayer.put("steam_id", igp.steamId());
        JSONInGamePlayer.put("civ", igp.civ());
        JSONInGamePlayer.put("end_pos", igp.endPos());
        JSONInGamePlayer.put("game_id", igp.gameId());
        JSONInGamePlayers.put(JSONInGamePlayer);
      }
      JSONGame.put("in_game_players", JSONInGamePlayers);
    }
    JSONPlayer.put("games_featured", JSONGames);
    return JSONPlayer;
  }

  public static String format(List<Player> players) {
    JSONArray JSON = new JSONArray();
    for (Player pl : players) {
      JSON.put(JSONPlayer(pl));
    }
    return JSON.toString(2);
  }
}
