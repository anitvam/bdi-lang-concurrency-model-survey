import socket
import sys
from threading import current_thread

from phidias.Types import *
from phidias.Main import *
from phidias.Lib import *
from phidias.Agent import *


class request(Reactor): pass
class reply(Reactor): pass

class ping(Procedure): pass
class showThread(Procedure): pass


class revealCurrentThread(Action):
    def execute(self, *args):
        print(str(args[0]) + " is executed on the thread named " + str(current_thread()))

def_vars("A", "X")


class ponger(Agent):

    def main(self):
        +request(X)[{'from': A}] >> [revealCurrentThread("[Ponger] intention 1"), +reply(X)[{'to': A}], showThread("2"), revealCurrentThread("[Ponger] intention 1")]
        showThread(X) >> [ revealCurrentThread("[Ponger] intention " , X, "?") ]


class main(Agent):

    def main(self):
        ping(X) >> [ revealCurrentThread("[Pinger] intention 1"), +request(X)[{'to': 'ponger@127.0.0.1'}], showThread(2), revealCurrentThread("[Pinger] intention 1") ]
        showThread(X) >> [ revealCurrentThread("[Pinger] intention ", X, "?") ]
        +reply(X)[{'from': A}] >> [ showThread(4), revealCurrentThread("[Pinger] intention 3") ]


if sys.argv[1] == "--ponger":
    ponger().start()
    PHIDIAS.run_net(globals(), 'http')

elif sys.argv[1] == "--pinger":
    main().start()
    PHIDIAS.run_net(globals(), 'http', 6767)
else:
    exit("Invalid command-line")

PHIDIAS.shell(globals())
