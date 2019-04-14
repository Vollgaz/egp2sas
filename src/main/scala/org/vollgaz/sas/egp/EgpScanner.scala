package org.vollgaz.sas.egp

import java.io.File

class EgpScanner(folderpath: String) {

    def findEGPfiles(): Array[File] = {
        val file = new File(folderpath)
        if (file.isDirectory) {
            recursiveListFiles(file)
                .filter(_.isFile)
                .filter(_.getName.split("\\.").last.toLowerCase == "egp")
        }
        else throw new Exception("Not a directory")
    }

    def recursiveListFiles(f: File): Array[File] = {
        val files = f.listFiles
        files ++ files.filter(_.isDirectory).flatMap(recursiveListFiles)
    }
}
