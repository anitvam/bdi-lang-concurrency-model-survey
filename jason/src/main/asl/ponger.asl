+ball[source(Sender)] <-
    .my_name(N);
    jia.revealCurrentThread(N, 5);
    //-ball[source(Sender)];
    //.wait(500);
    .send(Sender, tell, ball);
    !!showThread(6);
    jia.revealCurrentThread(N, 5).

+!showThread(X) <- 
    .my_name(N);
    jia.revealCurrentThread(N, X).
    