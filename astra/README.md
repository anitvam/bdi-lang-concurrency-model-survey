# Astra language execution

Use the following line to execute the test:
```
mvn clean compile astra:deploy
```


default outcome: agents are built to run on different thread by default.
```
Hello! I'm Alice and my thread is pool-1-thread-2
Hello! I'm Bob and my thread is pool-1-thread-6

```


There's the chance to change the execution strategy according to needs;
see the available implementations: https://gitlab.com/astra-language/astra-core/-/tree/master/astra-interpreter/src/main/java/astra/execution?ref_type=heads
see the Scheduler interface: https://gitlab.com/astra-language/astra-core/-/blob/master/astra-interpreter/src/main/java/astra/core/Scheduler.java?ref_type=heads

they let you change the configuration of the execution with `system.setSchedulingStrategy(string strategy)` in the agent body.


They also implemented the execution strategy with a sort of state machine, similar to ours -> See scheduler interface.
