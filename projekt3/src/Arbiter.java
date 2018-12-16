public class Arbiter {
    public Arbiter(){

    }
    public void meetAgents(Agent agent1, Agent agent2){
        String viewOfAgent1 = agent1.getState();
        String viewOfAgent2 = agent2.getState();
        if(!viewOfAgent1.equals(viewOfAgent2)){
            if((viewOfAgent1.equals("Y") && viewOfAgent2.equals("N")) ||
                    (viewOfAgent1.equals("N") && viewOfAgent2.equals("Y")) ){
                agent1.setState("U");
                agent2.setState("U");
            }
            if((viewOfAgent1.equals("Y") && viewOfAgent2.equals("U")) ||
                    (viewOfAgent1.equals("U") && viewOfAgent2.equals("Y")) ){
                agent1.setState("Y");
                agent2.setState("Y");
            }if((viewOfAgent1.equals("N") && viewOfAgent2.equals("U")) ||
                    (viewOfAgent1.equals("U") && viewOfAgent2.equals("N")) ){
                agent1.setState("N");
                agent2.setState("N");
            }
        }
    }
}
