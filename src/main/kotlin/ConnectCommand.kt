import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.check
import org.apache.hc.core5.net.InetAddressUtils

class ConnectCommand: CliktCommand(name = "connect", help = "Connects user to group chat.") {


    private val ip by argument(help = "Specifies group chat ip address.")
        .check("Must be IPv4 address.") {
            InetAddressUtils.isIPv4Address(it)
        }

    private val port by argument(help = "Specifies port to make connection with.")

    private val name by argument(help = "Specifies user's name that will be visible to other users.")

    override fun run() {
        Client(ip, name, port.toInt())
    }
}