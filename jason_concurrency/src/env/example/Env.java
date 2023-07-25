package example;

// Environment code for project jason_concurrency

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

public class Env extends Environment {

    private Logger logger = Logger.getLogger("jason_concurrency."+Env.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: "+action+", but not implemented!");
        if (action.getFunctor().equals("thread")) { // you may improve this condition
            System.out.println(Thread.currentThread().getName());
            // informAgsEnvironmentChanged();
        }
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
