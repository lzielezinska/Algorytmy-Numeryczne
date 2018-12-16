import org.junit.Before;
import org.junit.Test;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

public class ArbiterTest {
    private Arbiter arbiter;
    @Before
    public void init() {
        arbiter = new Arbiter();
    }

    @Test
    public void meetAgentsNY() {
        Agent agent1 = new Agent("N");
        Agent agent2 = new Agent("Y");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("U U", result);
    }
    @Test
    public void meetAgentsYN() {
        Agent agent1 = new Agent("Y");
        Agent agent2 = new Agent("N");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("U U", result);
    }
    @Test
    public void meetAgentsYY() {
        Agent agent1 = new Agent("Y");
        Agent agent2 = new Agent("Y");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("Y Y", result);
    }
    @Test
    public void meetAgentsNN() {
        Agent agent1 = new Agent("N");
        Agent agent2 = new Agent("N");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("N N", result);
    }
    @Test
    public void meetAgentsUU() {
        Agent agent1 = new Agent("U");
        Agent agent2 = new Agent("U");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("U U", result);
    }
    @Test
    public void meetAgentsYU() {
        Agent agent1 = new Agent("Y");
        Agent agent2 = new Agent("U");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("Y Y", result);
    }
    @Test
    public void meetAgentsUY() {
        Agent agent1 = new Agent("U");
        Agent agent2 = new Agent("Y");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("Y Y", result);
    }
    @Test
    public void meetAgentsNU() {
        Agent agent1 = new Agent("N");
        Agent agent2 = new Agent("U");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("N N", result);
    }
    @Test
    public void meetAgentsUN() {
        Agent agent1 = new Agent("U");
        Agent agent2 = new Agent("N");
        arbiter.meetAgents(agent1, agent2);
        String result = agent1.getState() + " " + agent2.getState();
        assertEquals("N N", result);
    }

}