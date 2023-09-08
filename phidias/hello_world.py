#
#
#

from phidias.Types  import *
from phidias.Main import *
from phidias.Lib import *
from threading import current_thread

class say_hello(Procedure): pass

def thread():
    return current_thread()


say_hello() >> [ show_line("Value: ", thread()) ]



PHIDIAS.run()
PHIDIAS.shell(globals())

