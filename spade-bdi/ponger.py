import asyncio
import os
from threading import current_thread
import spade

from spade_bdi.bdi import BDIAgent

class Ponger(BDIAgent):
    def add_custom_actions(self, actions):
        @actions.add(".revealCurrentThread", 1)
        def _revealCurrentThread(agent, term, intention):
            print(current_thread())
            yield


async def main():
    ponger_jid = os.getenv('JID_PONGER')
    password = os.getenv('XMPP_PASS')

    a = Ponger("ponger@localhost", "password", "ponger.asl")

    await a.start()
    await asyncio.sleep(200)
    #await a.stop()

if __name__ == '__main__':
    spade.run(main())
