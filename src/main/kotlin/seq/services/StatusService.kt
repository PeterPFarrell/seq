package seq.services

import java.io.BufferedReader
import java.io.InputStreamReader

const val CONTAINER_CMD = "docker compose ps"

class StatusService {

    fun getContainerInfo() : String {
        return runCommand(listOf(CONTAINER_CMD))
    }



    private fun runCommand(command: List<String>): String {
        val process = ProcessBuilder(command)
            .redirectErrorStream(true)
            .start()

        val output = StringBuilder()
        BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
            reader.forEachLine { output.appendLine(it) }
        }

        process.waitFor()
        return output.toString()
    }
}