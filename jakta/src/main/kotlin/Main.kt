import it.unibo.jakta.agents.bdi.dsl.internalAction
import it.unibo.jakta.agents.bdi.dsl.mas
import it.unibo.jakta.agents.bdi.dsl.plans
import it.unibo.jakta.agents.bdi.executionstrategies.ExecutionStrategy

val revealCurrentThread = internalAction("revealCurrentThread", 1) {
    val intentionId = arguments[0].castToInteger()
    println("Intention " + intentionId + " is executed on thread: " + Thread.currentThread().name)
}

val showThread = plans {
    +achieve("showThread"(Y)) then {
        execute("revealCurrentThread"(Y))
    }
}
fun main() {
    mas {
        environment(TestEnvironment.create())
        agent("ping") {
            actions { action(revealCurrentThread) }
            goals { achieve("send") }
            plans {
                +achieve("send") then {
                    execute("revealCurrentThread"(1))
                    execute("send"("pong", "tell", "ball"))
                    spawn("showThread"(2))
                    execute("revealCurrentThread"(1))
                }
                +"ball"("source"(X)) then {
                    spawn("showThread"(4))
                    execute("revealCurrentThread"(3))
                    execute("stop")
                }
            }
            plans(showThread)
        }
        agent("pong") {
            actions { action(revealCurrentThread) }
            plans {
                +"ball"("source"(X)) then {
                    execute("revealCurrentThread"(1))
                    execute("send"("ping", "tell", "ball"))
                    spawn("showThread"(2))
                    execute("revealCurrentThread"(1))
                    execute("stop")
                }
            }
            plans(showThread)
        }
        executionStrategy { ExecutionStrategy.oneThreadPerAgent() }
    }.start()
}