import astra.core.Module;

public class CustomConsole extends Module {

    @ACTION
    public boolean thread() {
        System.out.println("Hello! I'm " + super.agent.name() + " and my thread is " + Thread.currentThread().getName());
        return true;
    }

    @ACTION
    public boolean print(int value) {
        System.out.println("The Plan " + value + " is executed on thread " + Thread.currentThread().getName());
        return true;
    }
}
