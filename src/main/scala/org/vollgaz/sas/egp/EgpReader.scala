package org.vollgaz.sas.egp

import java.io.{File, InputStream}
import java.util.zip.{ZipEntry, ZipFile}

/**
  *
  * @param filepath File path to the EGP file.
  */
class EgpReader(filepath: File) {

    private final val PROJECT_FILE           = "project.xml"
    private       val zipFile     : ZipFile  = new ZipFile(filepath)
    private       val projectEntry: ZipEntry = zipFile.getEntry(PROJECT_FILE)

    def getProjectStream: InputStream = zipFile.getInputStream(projectEntry)
}
