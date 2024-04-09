!send_ping.

+!send_ping <-
    .my_name(N);
    jia.revealCurrentThread(N, 1);
    .send(ponger, tell, ball);
    !!showThread(2);
    jia.revealCurrentThread(N, 1).

+ball[source(Sender)] <-
    !!showThread(4);
    .my_name(N);
    jia.revealCurrentThread(N, 3).
    //-ball[source(Sender)];
    //.send(Sender, tell, ball).

+!showThread(X) <- 
    .my_name(N);
    jia.revealCurrentThread(N, X).
