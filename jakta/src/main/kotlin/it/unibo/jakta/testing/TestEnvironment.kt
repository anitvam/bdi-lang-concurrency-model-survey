package it.unibo.jakta.testing

import it.unibo.jakta.agents.bdi.dsl.environment
import it.unibo.jakta.agents.bdi.messages.Achieve
import it.unibo.jakta.agents.bdi.messages.Message
import it.unibo.jakta.agents.bdi.messages.Tell
import it.unibo.tuprolog.core.Atom
import it.unibo.tuprolog.core.Struct

object TestEnvironment {
    fun create() = environment {
        actions {
            action("send", 3) {
                val receiver: Atom = argument(0)
                val type: Atom = argument(1)
                val message: Struct = argument(2)
                when (type.value) {
                    "tell" -> this.sendMessage(receiver.value, Message(this.sender, Tell, message))
                    "achieve" -> sendMessage(
                        receiver.value,
                        Message(this.sender, Achieve, message),
                    )
                }
            }
        }
    }
}