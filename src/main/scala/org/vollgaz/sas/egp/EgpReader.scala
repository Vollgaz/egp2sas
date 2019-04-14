package org.vollgaz.sas.egp

import java.io.{ByteArrayOutputStream, InputStream}
import java.nio.charset.StandardCharsets
import java.util.zip.ZipFile

/**
  *
  * @param filepath Filepath to the EGP file.
  */
class EgpReader(val filepath: String) {

    private final val PROJECT_FILE = "project.xml"
    private       val zipFile      = new ZipFile(filepath)
    private       val projectEntry = zipFile.getEntry(PROJECT_FILE)

    def getProjectContent: String = {
        val stream: InputStream = zipFile.getInputStream(projectEntry)
        instreamToSring(stream, StandardCharsets.UTF_16LE.name())
    }

    def instreamToSring(inStream: InputStream, encoding: String): String = {
        var result = new ByteArrayOutputStream
        var buffer = new Array[Byte](1024)
        var length = inStream.read(buffer)
        while (length != -1) {
            result.write(buffer, 0, length)
            length = inStream.read(buffer)
        }
        result.toString(encoding)
    }
}
