package org.vollgaz.sas.egp

import java.io.File

/**
  *
  * @param file Is an arbitrary filepath. Can be a directory or a proper file.
  */
class EgpScanner(file: File) {

    def findEGPfiles(): Array[File] = {
        if (this.file.isDirectory) {
            getAllEgpFiles(this.file)
        }
        else if (this.file.isFile && "egp".equals(getFileExtension(this.file))) {
            Array[File](this.file)
        }
        else throw new Exception("Neither a folder nor an EGP file.")
    }

    private def getAllEgpFiles(folder: File): Array[File] = {
        recursiveListFiles(folder)
            .filter(_.isFile)
            .filter(_.getName.split("\\.").last.toLowerCase == "egp")
    }

    private def recursiveListFiles(folder: File): Array[File] = {
        val files = folder.listFiles
        files ++ files.filter(_.isDirectory).flatMap(recursiveListFiles)
    }

    private def getFileExtension(file: File): String = {
        file.getName.split("\\.").last.toLowerCase
    }
}
