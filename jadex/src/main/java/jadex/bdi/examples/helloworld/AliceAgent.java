package jadex.bdi.examples.helloworld;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.annotation.OnStart;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;

@Agent
public class AliceAgent {
    @OnStart
    void hello(IInternalAccess me) {
        me.repeatStep(0, 5000, dummy -> {
            System.out.println("Hello, from Alice! " + Thread.currentThread().getName());
            return IFuture.DONE;
        });
    }
}
