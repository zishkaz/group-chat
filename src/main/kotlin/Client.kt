import java.net.DatagramPacket
import java.net.InetAddress
import java.net.MulticastSocket
import java.util.*
import kotlin.concurrent.thread

private const val maxMessageLength = 1000

class Client(
    ip: String,
    private val userName: String,
    private val port: Int
) {

    private val address: InetAddress = InetAddress.getByName(ip).also {
        if (!it.isMulticastAddress) throw UnsupportedOperationException("Error: ip address must be in range from 224.0.0.0 to 239.255.255.255 ")
    }

    private val socket = MulticastSocket(port).apply {
        this.timeToLive = 0
        try {
            this.joinGroup(address)
        } catch (e: Exception) {
            throw Exception("Error while connecting to group chat.")
        }
        println("You are successfully connected!")
    }

    init {
        println("Start typing messages:")
        startListenThread()
        startMessageLoop()
    }

    private fun startListenThread() {
        thread {
            while (true) {
                val buffer = ByteArray(maxMessageLength)
                val datagram = DatagramPacket(buffer, buffer.size, address, port)
                val message = try {
                    socket.receive(datagram)
                    String(buffer, 0, datagram.length)
                } catch (e: Exception) {
                    throw Exception("Error while sending message:\n ${e.message}")
                }
                println(message)
            }
        }
    }

    private fun startMessageLoop() {
        val scanner = Scanner(System.`in`)
        while (true) {
            if (!scanner.hasNextLine()) continue
            val message = "$userName: ${scanner.nextLine()}"
            val buf = message.toByteArray()
            val datagram = DatagramPacket(buf, buf.size, address, port)
            socket.send(datagram)
        }
    }
}