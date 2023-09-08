package jadex.bdi.examples.helloworld;

import jadex.base.IPlatformConfiguration;
import jadex.base.PlatformConfigurationHandler;
import jadex.base.Starter;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.annotation.OnStart;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;

/**
 *  Main for starting the example programmatically.
 *  
 *  To start the example via this Main.java Jadex platform 
 *  as well as examples must be in classpath.
 */
@Agent
public class BobAgent {
	/**
	 *  The agent says hello every five seconds.
	 */
	@OnStart
	void hello(IInternalAccess me) {
		me.repeatStep(0, 5000, dummy -> {
			System.out.println("Hello, from Bob! " + Thread.currentThread().getName());
			return IFuture.DONE;
		});
	}
	/**
	 *  Start a platform and the example.
	 */
	public static void main(String[] args) {
		IPlatformConfiguration config = PlatformConfigurationHandler.getMinimal();
		config.addComponent(BobAgent.class);
		config.addComponent(AliceAgent.class);
		Starter.createPlatform(config).get();
	}
}