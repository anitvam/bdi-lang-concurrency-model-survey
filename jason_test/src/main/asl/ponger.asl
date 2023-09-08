

+ball[source(Sender)] <-
    jia.revealCurrentThread(1);
    .send(Sender, tell, ball);
    !!showThread(2);
    jia.revealCurrentThread(1).

+!showThread(X) <- jia.revealCurrentThread(X).