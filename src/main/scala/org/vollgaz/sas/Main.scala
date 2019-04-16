package org.vollgaz.sas

import java.io.{File, FileWriter}

import org.vollgaz.sas.egp._


object Main {
    def main(args: Array[String]): Unit = {
        val config: Egp2sasConfig = new Egp2sasArgsParser(args).getConfig.get
        runProgram(config.src, config.dest)
    }

    def runProgram(src: File, dest: File): Unit = {
        val fileList: Array[File] = new EgpScanner(src).findEGPfiles()
        println("File found : ")
        fileList.foreach(x => println(x.getAbsolutePath))
        if (isOkToProcess) {
            processFile(fileList, dest)
        }
    }

    def isOkToProcess: Boolean = {
        print("Process these files ? [y/n] : ")
        val char = scala.io.StdIn.readChar()
        char match {
            case 'y' => true
            case 'n' => false
            case _ => isOkToProcess
        }
    }

    def processFile(fileList: Array[File], dest : File): Unit = {
        fileList.foreach((file: File) => {
            println(s"${file.getAbsolutePath}  exist: ${file.exists()}")
            val filewriter: FileWriter = new FileWriter(s"${dest.getAbsolutePath}/${file.getName}.sas")
            val filestream = new EgpReader(file).getProjectStream
            val sasprogram: String = new EgpProjectParser().parseStream(filestream)
            filewriter.write(sasprogram)
            filewriter.close()
        })
    }
}
