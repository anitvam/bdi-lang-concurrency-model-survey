# A survey over notable BDI Languages concurrency model configuration

This repository is a collection of notable BDI programming languages and/or libraries usages to highlight their concurrency model configuration.

## Motivations for this study

- Controlling (non-)determinism of MAS execution, in order to control reproducibility and therefore enable simulatability
- Controlling the amount of parallelism, in order to efficiently exploit the computational resources of the host infrastructure

## Concurrency model

Informal insight: "how agents activities are scheduled onto threads".

Nomenclature: each agent has a control loop, that is cycle where, at each iteration, the following stages are performed:
1. perception
2. deliberation
3. action
Overall, the control loop of an agent may, in turn, carry on multiple concurrent courses of actions (i.e. intentions) via cooperative scheduling.

What is a thread?
To us, it's the elementary unit of control flow, meaning that every computational activity performed by a single thread is sequential, and parallelism can only be attained by spawning multiple threads.

Interesting patterns for multi-threading:
- process = group of threads sharing the same memory space
- multi-process = different groups of threads sharing no inter-group memory space
- event-loop = single thread carrying on multiple tasks in a FIFO way
- executor service = event loop backed by multiple threads (i.e. supporting task parallelism)
- coroutine = sequence of tasks which is executed by an execution service in such a way that the relative ordering of tasks is preserved
- fixed-size executor service = executor service with a fixed threads pool size
- cached executor service = executor service with a scalable threads pool size

Which activities?
Various granularity levels, depending on what is executed by individual threads:
- whole control loop
- single control loop iteration
- intention
- single stage of each iteration of the control loop

This impacts how interleaving occurs, and greatly impacts the properties of the MAS as a whole.

### Interesting concurrency models

- __One-agent-one-thread__: each agent is associated to a single thread, which is responsible for the whole control loop
    * interleaving among agents' activities is delegated to the host OS scheduler, and therefore non-controllable

- __Multiple-agents-one-thread__: multiple agents are associated to a single thread, which executes the control loop of each agent according to some internal cooperative scheduling policy
    * there is no interleaving, the execution of the whole MAS is sequential and therefore deterministic
    * this is very good for simulations

- __Multiple-agents-one-event-loop__: like the above, but the internal cooperative scheduling policy is "round-robin"

- __Multiple-agents-one-executor-service__: each iteration of each agent's control loop is a task on the executor service.
Relevant sub-cases:
    * __Fixed-size executor service__: there is a maximum level of parallelism in the MAS: e.g. if there are M agents and N threads, and M > N, and N or more agents are doing blocking operations, then some other agents must wait
    * __Cached executor service__: there is no maximum level of parallelism in the MAS: e.g. if there are M agents and N threads, and M > N, and N or more agents are doing blocking operations, then more threads may be spawned
        - Potential issue: if the number of threads is not bounded, then the OS scheduler may be overwhelmed, and the overall performance may degrade

Other niche relevant patterns to discuss although not used in practice:

- __Control-loop-as-coroutine__: each control loop of each agent is a coroutine, and each stage of a control loop is a task.
When executed on an executor service, control loops may interleave at the stage level

- __Intentions-as-threads__: the agent is essentially a process carrying on multiple parallel intentions
    * interleaving, and coordination among intentions is delegated to the OS, and therefore non-controllable
    * handling synchronization issues is a responsibility of the agent programmer
    * many situations exists depending on how intentions are scheduled on threads (they are essentially analogous to the above cases)

Others situations can be reduced to the ones above.

## General test pattern

The test should reveal which and how many threads are used by a MAS.
This should be caught ba a simple MAS with 2 agents doing ping-pong interaction, plus an internal action for logging the current thread.

Pinger agent:
```jason
!ping.

+!ping <- 
    .revealCurrentThread("intention1");
    .send(pong, tell, ball);
    !!showThread(2);
    .revealCurrentThread("intention1").

+ball <-
    !!showThread(4);
    .revealCurrentThread("intention3").

+!showThread(X) <- .revealCurrentThread("intention" + X).
```

Ponger agent:
```jason
+ball[source(X)] <-
    .revealCurrentThread("intention1");
    .send(X, tell, ball);
    !!showThread(2);
    .revealCurrentThread("intention1").

+!showThread(X) <- .revealCurrentThread("intention" + X).
```