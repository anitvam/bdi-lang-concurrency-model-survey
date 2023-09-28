import getpass
import os
from threading import current_thread
import asyncio
import spade
from spade_bdi.bdi import BDIAgent

class Pinger(BDIAgent):
    def add_custom_actions(self, actions):
        @actions.add(".revealCurrentThread", 1)
        def _revealCurrentThread(agent, term, intention):
            print(f"{term} is executed on {current_thread()}")
            yield


async def main():
    pinger_jid = os.getenv('JID_PINGER')
    ponger_jid = os.getenv('JID_PONGER')
    password = os.getenv('XMPP_PASS')

    #a = Pinger(pinger_jid, password, "pinger.asl")
    a = Pinger("pinger@localhost", "password", "pinger.asl")
    a.bdi.set_belief("receiver", "ponger")

    await a.start()
    await asyncio.sleep(200)
    #await a.stop()

if __name__ == '__main__':
    spade.run(main())
