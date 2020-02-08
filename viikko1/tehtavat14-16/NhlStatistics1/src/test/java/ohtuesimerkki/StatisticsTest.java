package ohtuesimerkki;

import java.util.List;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {
    	 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
        
    @Test
    public void existingPlayerCanBeFound(){
        
    	Player player = stats.search("urr");
    	assertTrue(player.getName().equals("Kurri"));
    }
    
    @Test
    public void nonExistingPlayerCannotBeFound(){
        
    	Player player = stats.search("qwerty");
    	assertTrue(player == null);
    }
    
    @Test
    public void teamIncludesAllRelevantPlayersButNotMore(){
    	        
    	List<Player> players = stats.team("EDM");
    	assertTrue(players.size() == 3);

    	boolean semenko = false;
    	boolean kurri = false;
    	boolean gretzky = false;

    	for(Player player : players) {
    		if(player.getName().equals("Semenko")) {
    			semenko = true;
    			continue;
    		}
    		if(player.getName().equals("Kurri")) {
    			kurri = true;
    			continue;
    		}
    		if(player.getName().equals("Gretzky")) {
    			gretzky = true;
    		}
    	}
    	
    	assertTrue(semenko);
    	assertTrue(kurri);
    	assertTrue(gretzky);
    }
    
    @Test
    public void teamIsEmptyIfNoTeamMembersExist(){
    	List<Player> players = stats.team("WRONG");
    	assertTrue(players.size() == 0);
    }
    
    @Test
    public void top3ScorersCorrect(){
    	
    	List<Player> topPlayers = stats.topScorers(3);
    	assertTrue(topPlayers.size()==3);
    	
    	assertTrue(topPlayers.get(0).getName().equals("Gretzky"));
    	assertTrue(topPlayers.get(1).getName().equals("Lemieux"));
    	assertTrue(topPlayers.get(2).getName().equals("Yzerman"));
    }
}