It seems that each agent runs on the main thread.
There's no notion of environment. 

Spade BDI is a wrapper of Spade, and it inherits all his model definitions. 
Spade runs its agents as asynchronous task on a Event Loop, and there's no way to customize this behaviour.
The agent entity definition: https://github.com/javipalanca/spade/blob/master/spade/agent.py

The output I get from the execution of basic.py is indeed:
```<_MainThread(MainThread, started 139983526070080)>```

Notes: 
1. an XMPP server is required, I signed on a free online service to do so.
2. the execution of the example requires the dependencies in requirements.txt
3. spade-bdi needs to run with python < 3.9 to work
