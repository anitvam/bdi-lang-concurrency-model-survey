import getpass
import os
from threading import current_thread
import asyncio
import spade
from spade_bdi.bdi import BDIAgent

class PrintingAgent(BDIAgent):
    def add_custom_actions(self, actions):
        @actions.add(".revealCurrentThread", 1)
        def _revealCurrentThread(agent, term, intention):
            print(f"{term} is executed on {current_thread()}")
            yield

async def main():
    a = PrintingAgent("pinger@localhost", "password", "pinger.asl")
    a.bdi.set_belief("receiver", "ponger")
    b = PrintingAgent("ponger@localhost", "password", "ponger.asl")

    await b.start()
    await a.start()
    await asyncio.sleep(5)
    #await a.stop()

if __name__ == '__main__':
    spade.run(main())
