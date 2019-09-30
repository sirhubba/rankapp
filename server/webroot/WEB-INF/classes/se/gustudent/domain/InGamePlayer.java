package se.gustudent.domain;

public class InGamePlayer{
  private String playerName, steamId, civ;
  private int endPos, gameId;

  public InGamePlayer(String playerName, String steamId,
                        String civ, int endPos, int gameId){
    this.playerName = playerName;
    this.steamId = steamId;
    this.civ = civ;
    this.endPos = endPos;
    this.gameId = gameId;
  }

  public String playerName(){ return playerName; }
  public String steamId()   { return steamId;    }
  public String civ()       { return civ;        }
  public int    endPos()    { return endPos;     }
  public int    gameId()    { return gameId;     }

  @Override
  public String toString(){
    return playerName;
  }

}
