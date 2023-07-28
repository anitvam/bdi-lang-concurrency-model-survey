import jason.asSemantics.Agent;

public class CustomAgent extends Agent {

    public CustomAgent() {
        super.initAg();
        super.setIA("ThreadAction", new ThreadAction());
    }
}
