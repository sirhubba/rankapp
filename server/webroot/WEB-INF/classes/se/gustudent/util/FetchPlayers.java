package se.gustudent.util;

import se.gustudent.domain.*;
import se.gustudent.util.FetchGames;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.sql.*;
import java.io.IOException;

public class FetchPlayers{
  public static List<Player> getPlayers() {
    String query = "SELECT nick, steam_id, rating from player;";
    List<Player> allPlayers = new ArrayList<>();

    try{
      Connection con = DriverManager.getConnection("jdbc:sqlite:civ5rankapp.db");
      Statement stm  = con.createStatement();
      /*public Player(String playerName, String steamId, int rating,
                  ArrayList<Game> gamesFeatured) */
      ResultSet rs   = stm.executeQuery(query);
      while (rs.next()) {
        List<Game> gamesFeatured = new ArrayList<>();
        allPlayers.add(new Player(rs.getString("nick"),
                             rs.getString("steam_id"),
                             rs.getInt("rating"),
                             //gamesFeatured.add(FetchGames.getSteamIdsWithGames().get(rs.getString("steam_id")))));
                             /*for(Game g : FetchGames.getSteamIdsWithGames().get(rs.getString("steam_id"))) {
                               gamesFeatured.add(g);
                             }*/
                             FetchGames.getSteamIdsWithGames().get(rs.getString("steam_id"))));


      }
      //System.out.println(inGamePlayers);
      //System.out.println(steamIdToGames);
      con.close();

    }catch(SQLException sqle){
      System.err.println("Something went wrong with database: " + sqle);
    }
    return allPlayers;
  }
}
