# Astra language execution

Use the following line to execute the test:
```
mvn clean compile astra:deploy
```

Default outcome: agents are built to run on different thread by default.
```
Hello! I'm Alice and my thread is pool-1-thread-2
Hello! I'm Bob and my thread is pool-1-thread-6

```

Changing the execution strategy according to needs;
see the available implementations: https://gitlab.com/astra-language/astra-core/-/tree/master/astra-interpreter/src/main/java/astra/execution?ref_type=heads
see the Scheduler interface: https://gitlab.com/astra-language/astra-core/-/blob/master/astra-interpreter/src/main/java/astra/core/Scheduler.java?ref_type=heads

Configuration for the execution is performed using `system.setSchedulingStrategy(string strategy)` in the agent body.

They also implement the execution strategy with a state machine -> See `scheduler` interface.
