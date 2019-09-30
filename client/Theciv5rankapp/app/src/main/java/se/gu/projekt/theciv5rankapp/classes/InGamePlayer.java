package se.gu.projekt.theciv5rankapp.classes;

import java.io.Serializable;

import se.gu.projekt.theciv5rankapp.SelectedGameActivity;

public class InGamePlayer implements Serializable{
    private String playerName;
    private int rank;
    private int rating;
    private String steamId;
    private String civ;
    private int endPos;

    public InGamePlayer(String playerName, int rank, int rating, String steamId, String civ, int endPos) {

        this.playerName = playerName;
        this.rank = rank;
        this.rating = rating;
        this.steamId = steamId;
        this.civ = civ;
        this.endPos = endPos;
    }

    public String playerName() { return playerName; }
    public String civ() { return civ; }
    public int endPos() { return endPos; }
    public int rating() { return rating; }
    public int rank() { return rank; }
    public String steamId() { return steamId; }

    @Override
    public String toString(){
        return playerName;
    }
}

