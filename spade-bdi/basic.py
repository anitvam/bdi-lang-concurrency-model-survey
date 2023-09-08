import getpass
import os
from threading import current_thread
import asyncio
import spade
from spade_bdi.bdi import BDIAgent

class MyCustomBDIAgent(BDIAgent):
    def add_custom_actions(self, actions):
        @actions.add(".thread", 0)
        def _thread(agent, term, intention):
            print(current_thread())
            yield


async def main():
    jid = os.getenv('JID')
    password = os.getenv('XMPP_PASS')


    a = MyCustomBDIAgent(jid, password, "basic.asl")
    await a.start()
    await asyncio.sleep(2)
    #await a.stop()

if __name__ == "__main__":
    spade.run(main())