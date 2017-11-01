package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void search() {
        assertEquals(stats.search("Kurri"), new Player("Kurri", "EDM", 37, 53));
        assertEquals(stats.search("Selänne"), null);
        
    }

    @Test
    public void team() {
        ArrayList<Player> rightOrder = new ArrayList<Player>();
        rightOrder.add(new Player("Semenko", "EDM", 4, 12));
        rightOrder.add(new Player("Kurri", "EDM", 37, 53));
        rightOrder.add(new Player("Gretzky", "EDM", 35, 89));

        ArrayList<Player> edmPlayers = new ArrayList<Player>();
        for (Player player : stats.team("EDM")) {
            edmPlayers.add(player);
        }

        for (int i = 0; i < rightOrder.size(); i++) {
            assertEquals(edmPlayers.get(i), rightOrder.get(i));
        }

    }

    @Test
    public void topScores() {
        ArrayList<Player> rightOrder = new ArrayList<Player>();
        rightOrder.add(new Player("Gretzky", "EDM", 35, 89));
        rightOrder.add(new Player("Lemieux", "PIT", 45, 54));
        rightOrder.add(new Player("Yzerman", "DET", 42, 56));
        rightOrder.add(new Player("Kurri", "EDM", 37, 53));

        ArrayList<Player> topList = new ArrayList<Player>();
        for (Player player : stats.topScorers(4)) {
            topList.add(player);
        }

        for (int i = 0; i < rightOrder.size(); i++) {
            assertEquals(topList.get(i), rightOrder.get(i));
        }

    }
}
