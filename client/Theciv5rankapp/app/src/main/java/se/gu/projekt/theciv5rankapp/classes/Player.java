package se.gu.projekt.theciv5rankapp.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Comparable<Player>, Serializable {
    private String playerNick, steamId;
    private int rank, rating;
    private ArrayList<Game> gamesParticipated;

    public Player(String playerName, int rank, int rating, String steamId){
        if(playerName == null){
            throw new NullPointerException("Name can't be null");
        }
        else if(playerName.equals("")){
            throw new IllegalArgumentException("Name can't be empty");
        }
        else{
            this.playerNick = playerName;
        }
            this.rank = rank;
            this.rating = rating;
            this.steamId = steamId;
    }

    public String playerName(){ return this.playerNick; }
    public int rank(){ return this.rank; }
    public int rating(){ return this.rating; }
    public String steamId(){ return this.steamId; }

    @Override
    public String toString(){
        return playerNick;
    }
    @Override
    public int compareTo(Player other){
        if (other.playerNick == null){ throw new NullPointerException();
        }else{
            return this.playerNick.compareTo(((Player)other).playerName());
        }
    }
}
