//OBS! TEST OBS!

import java.util.List;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.*;

import se.gustudent.domain.*;
import se.gustudent.util.FetchPlayers;
import se.gustudent.util.FetchGames;
import se.gustudent.util.JSONFormatter;

public class CreateDomain{

  public static void main(String[] args) {
    List<Player> players = FetchPlayers.getPlayers();
    String JSON = JSONFormatter.format(players);
    //Lägg till webserverskit här istället för print-to-file
    //writeFile(JSON);
    //System.out.println(players);
    System.out.println(JSON);
  }

  public static void writeFile(String JSON) {
    try {
      Path jsonFile = Paths.get("2019-01-29.json");
      Files.write(jsonFile, JSON.getBytes(StandardCharsets.UTF_8));
    } catch (IOException ioe) {
      System.err.println("Exception writing JSON file: " + ioe.getMessage());
    }
  }
}
