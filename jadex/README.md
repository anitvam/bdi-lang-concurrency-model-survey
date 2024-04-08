The output of the example, without any configuration, shows this result:
```
Hello, from Bob! Thread-3
Hello, from Alice! Thread-7
Hello, from Bob! Thread-2
Hello, from Alice! Thread-2
Hello, from Bob! Thread-2
Hello, from Alice! Thread-2
Hello, from Bob! Thread-2
Hello, from Alice! Thread-2
Hello, from Bob! Thread-2
Hello, from Alice! Thread-2
Hello, from Bob! Thread-2
Hello, from Alice! Thread-2
```

Its execution is ruled by a CachedThreadPool: https://github.com/actoron/jadex/blob/master/util/concurrent/src/main/java/jadex/commons/concurrent/JavaThreadPool.java

They provide two implementation of their Executors: one where each agent runs on different executors and one another in which not.
There's no customization other than these two: https://github.com/actoron/jadex/blob/3da9958522bc55548ac60cd2634ffc00ca25d502/platform/base/src/main/java/jadex/platform/PlatformAgent.java#L157
