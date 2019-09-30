package se.gustudent.util;

import java.util.List;
import java.util.ArrayList;
import se.gustudent.util.FetchPlayers;
import se.gustudent.domain.Player;
import se.gustudent.domain.Game;

public class TestFetchPlayers{
  public static void main(String[] args) {

    for(Player p : FetchPlayers.getPlayers()) {
      System.out.println("Player: " + p.toString());
      for (Game g : FetchGames.getSteamIdsWithGames().get(p.steamId())) {
        System.out.println("Game: " + g.toString());
      }
    }
  }
}
