package se.gustudent.domain;

import java.util.ArrayList;

public class Player{

  private String playerName, steamId;
  private int rating;
  private ArrayList<Game> gamesFeatured;

  public Player(String playerName, String steamId, int rating,
                  ArrayList<Game> gamesFeatured) {

    this.playerName    = playerName;
    this.steamId       = steamId;
    this.rating        = rating;
    this.gamesFeatured = gamesFeatured;

  }

  public String playerName()            { return playerName;    }
  public String steamId()               { return steamId;       }
  public int    rating()                { return rating;        }
  public ArrayList<Game> gamesFeatured(){ return gamesFeatured; }

  @Override
  public String toString(){
    //System.out.println("Player: " + playerName);
    return playerName + " " + steamId + " " + rating/* + " " + gamesFeatured*/;
  }
}
