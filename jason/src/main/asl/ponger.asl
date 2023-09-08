

+ball[source(Sender)] <-
    jia.revealCurrentThread(21);
    -ball[source(Sender)];
    .wait(500);
    .send(Sender, tell, ball);
    !!showThread(22);
    jia.revealCurrentThread(21).

+!showThread(X) <- jia.revealCurrentThread(X).