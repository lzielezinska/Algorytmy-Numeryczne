import java.util.ArrayList;
import java.util.Collection;

public class Simulation {
    private int yes;
    private int no;
    private int n;
    private int undefined;
    private ArrayList<Agent> container;
    private ArrayList<Agent> savedContainer;

    public Simulation(int yes, int no, int n) {
        this.yes = yes;
        this.no = no;
        this.n = n;
        this.undefined = n - (yes + no);
        this.container = new ArrayList<Agent>();
        this.createEcosystem();
    }



    public String getResultOfSimulation(){
        doSimulation();
        return ((Agent)container.toArray()[0]).getState();
    }
    public void doSimulation(){
        Arbiter arbiter = new Arbiter();
        while (!isSimulationShouldEnd()){
            Agent agent1 = this.container.get(Math.abs(Randomizer.generateRandomShort() % n));
            Agent agent2 = container.get(Math.abs(Randomizer.generateRandomShort() % n));
            arbiter.meetAgents(agent1, agent2);
        }
    }
    public boolean isSimulationShouldEnd(){
        boolean result = true;
        String firstState = ((Agent)this.container.toArray()[0]).getState();
        for(Agent agent : this.container){
            if(!agent.getState().equals(firstState)){
                result = false;
                break;
            }
        }
        return result;
    }
    private void createEcosystem(){
        for(int i = 0; i < this.yes; i++){
            this.container.add(new Agent("Y"));
        }
        for(int i = 0; i < this.no; i++){
            this.container.add(new Agent("N"));
        }
        for(int i = 0; i < this.undefined; i++){
            this.container.add(new Agent("U"));
        }
    }
    public String getStringValue(){
        String result = "";
        for(Agent agent : this.container){
            result += agent.getState();
        }
        return result;
    }
    public Collection<Agent> getAgents(){
        return this.container;
    }

}
