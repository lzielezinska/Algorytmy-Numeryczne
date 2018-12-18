import org.junit.Test;

import static org.junit.Assert.*;

public class SimulationTest {

    @Test
    public void initTest() {
        Simulation simulation = new Simulation(1,2,4);
        String result = simulation.getStringValue();
        assertEquals("YNNU", result);
    }
    @Test
    public void initTestLen() {
        Simulation simulation = new Simulation(1,2,4);
        assertEquals(4, simulation.getAgents().size());
    }
    @Test
    public void isSimulationShouldEndTrue() {
        Simulation simulation = new Simulation(3,0,3);
        assertTrue(simulation.isSimulationShouldEnd());
    }

    @Test
    public void isSimulationShouldEndFalse() {
        Simulation simulation = new Simulation(3,1,4);
        assertFalse(simulation.isSimulationShouldEnd());
    }
}