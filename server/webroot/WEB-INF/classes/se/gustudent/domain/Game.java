package se.gustudent.domain;

//import se.gustudent.domain.InGamePlayer;
import java.util.ArrayList;

public class Game{

  public static enum victoryType{
    DOMINATION, TOURISM, SCIENCE, DIPLOMACY
  }
  private String dateSubmitted;
  private Enum victoryType;
  private ArrayList<InGamePlayer> playersParticipated;
  private int gameId;

  public Game(String dateSubmitted, Enum victoryType,
                ArrayList<InGamePlayer> playersParticipated, int gameId){
    this.dateSubmitted       = dateSubmitted;
    this.victoryType         = victoryType;
    this.playersParticipated = playersParticipated;
    this.gameId              = gameId;
  }

  public String dateSubmitted(){ return dateSubmitted; }
  public Enum victoryType()    { return victoryType;   }
  public int gameId()          { return gameId;        }
  public ArrayList<InGamePlayer> playersParticipated(){
    return playersParticipated;
  }

  public String toString(){
    return dateSubmitted + " " + victoryType + " " + playersParticipated;
  }

}
