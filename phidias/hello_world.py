from phidias.Types  import *
from phidias.Main import *
from phidias.Lib import *
from threading import current_thread

class curr_thread(Action):
    def execute(self, *args):
        print(current_thread())

class say_hello(Procedure): pass

say_hello() >> [curr_thread() ]



PHIDIAS.run()
PHIDIAS.shell(globals())
