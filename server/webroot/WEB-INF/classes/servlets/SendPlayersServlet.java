package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;
import java.util.List;

import se.gustudent.domain.*;
import se.gustudent.util.FetchPlayers;
import se.gustudent.util.FetchGames;
import se.gustudent.util.JSONFormatter;

public class SendPlayersServlet extends HttpServlet{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    PrintWriter out =
      new PrintWriter(new OutputStreamWriter(response.getOutputStream(),
                                             UTF_8), true);
      List<Player> players = FetchPlayers.getPlayers();
      String JSON = JSONFormatter.format(players);
      //System.out.println(JSON);
      out.println(JSON);
      out.close();
    }
}
