package jia;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.InternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.StringTerm;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;


public class revealCurrentThread extends DefaultInternalAction {

    private static InternalAction singleton = null;
    public static InternalAction create() {
        if (singleton == null)
            singleton = new revealCurrentThread();
        return singleton;
    }

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        
        try {
            String name = args[0].toString();
            int intentionid = (int)((NumberTerm)args[1]).solve();
            System.out.println("[" +name+  "] Intention " + intentionid + " is executed on thread: " + Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println("Error in function 'revealCurrentThread'! "+e);
        }

        return true;
    }

}
