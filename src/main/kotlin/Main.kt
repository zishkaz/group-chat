import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import kotlin.system.exitProcess


class GroupChatCli : CliktCommand(name = "UnitTestBot TypeScript Command Line Interface") {

    override fun run() {}
}

fun main(args: Array<String>) = try {
    GroupChatCli().subcommands(
        ConnectCommand(),
    ).main(args)
} catch (e: Throwable) {
    e.printStackTrace()
    exitProcess(1)
}