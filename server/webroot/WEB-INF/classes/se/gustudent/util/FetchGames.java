package se.gustudent.util;

import se.gustudent.domain.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.sql.*;
import java.io.IOException;
// DOMINATION, TOURISM, SCIENCE, DIPLOMACY
public class FetchGames{
private static Game.victoryType getVicType(String type) {
  switch (type) {
    case "DOMINATION":
      return Game.victoryType.DOMINATION;
    case "TOURISM":
      return Game.victoryType.TOURISM;
    case "SCIENCE":
      return Game.victoryType.SCIENCE;
    case "DIPLOMACY":
      return Game.victoryType.DIPLOMACY;
    default:
      throw new IllegalArgumentException("Unknown vic type: " + type);
  }
}
  public static Map<String, ArrayList<Game>> getSteamIdsWithGames(){

    //String query = "SELECT player.steam_id from player;";
    String query = "SELECT nick, player.steam_id as pstid, civ, end_pos, game.game_id, rating FROM player JOIN ingame_player ON pstid = ingame_player.steam_id JOIN game ON ingame_player.game_id = game.game_id ORDER BY game.game_id;";
    String query2 = "Select game_id, date, victory_type from game;";
    ArrayList<InGamePlayer> allInGamePlayers = null;

    // Maps a game-id to the list of in-game-players
    Map<Integer, ArrayList<InGamePlayer>> inGamePlayers = new HashMap<>();
    // Maps a game-id to the Game information
    Map<Integer, Game> games = new HashMap<>();
    // Maps a steamId to a player object
    Map<String, ArrayList<Integer>> steamIdToGames = new HashMap<>();

    // Maps a steamId to a list of Games (the player was in)
    Map<String, ArrayList<Game>> steamIdToGamesPlayed = new HashMap<>();

    // Get all information about games and their players

    try{
      Connection con = DriverManager.getConnection("jdbc:sqlite:civ5rankapp.db");
      Statement stm  = con.createStatement();
      /*public InGamePlayer(String playerName, String steamId,
      String civ, int endPos, int gameId)*/
      ResultSet rs   = stm.executeQuery(query);
      int gameId=-1;
      while (rs.next()) {
        // If we find a new game, make the list a new list
        if (rs.getInt("game_id") != gameId) {
          allInGamePlayers = new ArrayList<>();
        }
        // Add the in-game player to the list
        allInGamePlayers.add(new InGamePlayer(rs.getString("nick"),
                             rs.getString("pstid"),
                             rs.getString("civ"),
                             rs.getInt("end_pos"),
                             rs.getInt("game_id")));
        // Save the list of in-game players for this game-id
        inGamePlayers.put(rs.getInt("game_id"), allInGamePlayers);
        // Save the game-id, so that we can see if we find a new game

        // Save the games for a unique steamId
        ArrayList<Integer> gameIds = null;
        if (steamIdToGames.containsKey(rs.getString("pstid"))) {
          gameIds = steamIdToGames.get(rs.getString("pstid"));
        } else {
          gameIds = new ArrayList<>();
        }
        gameIds.add(rs.getInt("game_id"));
        steamIdToGames.put(rs.getString("pstid"), gameIds);
        gameId = rs.getInt("game_id");
      }
      //System.out.println(inGamePlayers);
      //System.out.println(steamIdToGames);
      con.close();

    }catch(SQLException sqle){
      System.err.println("Something went wrong with database: " + sqle);
    }


    // Get the date and type for all games,
    // and add the list of in-game-players from our map created above
    try{
      Connection con = DriverManager.getConnection("jdbc:sqlite:civ5rankapp.db");
      Statement stm  = con.createStatement();
      /*public Game(String dateSubmitted, Enum victoryType,
                    ArrayList<InGamePlayer> playersParticipated, int gameId)*/
      ResultSet rs   = stm.executeQuery(query2);
      while(rs.next()){
        games.put(rs.getInt("game_id"),
                  new Game(rs.getString("date"),
                  getVicType(rs.getString("victory_type")),
                  // We get the list of in-game-players from our map
                  inGamePlayers.get(rs.getInt("game_id")),
                  rs.getInt("game_id")));
      }
      //System.out.println(games);
      con.close();
    }catch(SQLException sqle){
      System.err.println("Something went wrong with database: " + sqle);
    }

    ArrayList<Game> gameList = null;
    // Build the map with steamid -> player
    for (String steamId : steamIdToGames.keySet()) {
      // steamId is e.g. abc1
      if (!steamIdToGamesPlayed.containsKey(steamId)) {
        gameList = new ArrayList<>();
      } else {
        gameList = steamIdToGamesPlayed.get(steamId);
      }
      // Get all gameIds for e.g. abc1 (could be [1, 2])
      for (int gameId : steamIdToGames.get(steamId)) {
        // add the whole game for this gameID:
        gameList.add(games.get(gameId));
      }
      // Save the list of games in the map for this steamID
      steamIdToGamesPlayed.put(steamId, gameList);
    }
    //System.out.println(steamIdToGamesPlayed);
    return steamIdToGamesPlayed;
  }
}
