!send_ping.

+!send_ping <-
    jia.revealCurrentThread(1);
    .send(pong, tell, ball);
    !!showThread(2);
    jia.revealCurrentThread(1).


+ball <-
    !!showThread(4);
    jia.revealCurrentThread(3).

+!showThread(X) <- jia.revealCurrentThread(X).
