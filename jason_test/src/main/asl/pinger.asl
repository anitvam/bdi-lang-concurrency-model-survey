!send_ping.

+!send_ping <-
    jia.revealCurrentThread(11);
    .send(pong, tell, ball);
    !!showThread(12);
    jia.revealCurrentThread(11).


+ball[source(Sender)] <-
    !!showThread(14);
    jia.revealCurrentThread(13);
    -ball[source(Sender)];
    .send(Sender, tell, ball).

+!showThread(X) <- jia.revealCurrentThread(X).
