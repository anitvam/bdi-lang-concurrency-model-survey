import astra.core.Module;

public class ThreadPrinter extends Module {
    @ACTION
    public boolean printThread() {
        System.out.println("Hello! I'm " + super.agent.name() + "and my thread is " + Thread.currentThread().getName());
        return true;
    }
}
