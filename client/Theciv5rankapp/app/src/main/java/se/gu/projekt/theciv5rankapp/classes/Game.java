package se.gu.projekt.theciv5rankapp.classes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Game implements Serializable {

    public static enum victoryType{
        DOMINATION,
        DIPLOMACY,
        TOURISM,
        SCIENCE

    }

    private int gameId;
    //private Date dateSubmitted;
    private String dateSubmitted;
    private ArrayList<InGamePlayer> players;
    private Enum victoryType;

    public void newGame(){
        players = new ArrayList<>();
    }

    private int numberOfPlayers(){
        return players.size();
    }

    public void addPlayer(InGamePlayer i){
        players.add(i);
    }

    public Game(int gameId, ArrayList<InGamePlayer> players, String dateSubmitted, Enum victoryType){

        //DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
        this.gameId = gameId;
        this.players = players;
        this.dateSubmitted = dateSubmitted;
        //this.dateSubmitted = (Date)formatter.parse(dateSubmitted);
        this.victoryType = victoryType;

    }

    public int getGameId(){ return this.gameId; }
    public ArrayList getPlayers(){ return this.players; }
    //public Date dateSubmitted(){ return this.dateSubmitted; }
    public String dateSubmitted(){ return this.dateSubmitted; }
    public Enum victoryType(){ return this.victoryType; }

    //String parsedDate = dateSubmitted.format(dateSubmitted);

    @Override
    public String toString() {
        return dateSubmitted;
    }
}
