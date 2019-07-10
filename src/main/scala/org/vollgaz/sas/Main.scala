package org.vollgaz.sas

import java.io.{File, FileWriter}

import org.vollgaz.sas.egp._

object Main {
    var config: MainConfig = _

    def main(args: Array[String]): Unit = {
        this.config = new MainArgsParser(args).getConfig.get
        runProgram()
    }

    def runProgram(): Unit = {
        val fileList: Array[File] = new EgpScanner(config.src).findEGPfiles()
        println("File found : ")
        fileList.foreach(x => println(x.getAbsolutePath))
        if (config.noprompt || isOkToProcess) {
            processFile(fileList, config.dest)
        }

    }

    def isOkToProcess: Boolean = {
        print("Process these files ? [y/n] : ")
        val char = Some(scala.io.StdIn.readLine())
        char match {
            case Some("y") => true
            case Some("n") => false
            case _         => isOkToProcess
        }
    }

    def processFile(fileList: Array[File], dest: File): Unit = {
        fileList.foreach((file: File) => {
            println(s"${file.getAbsolutePath}  converti : ${file.exists()}")
            val filewriter: FileWriter = new FileWriter(s"${dest.getAbsolutePath}/${file.getName.replace(".egp", "")}.sas")
            val filestream = new EgpReader(file).getProjectStream
            val sasprogram: String = new EgpProjectParser(file.getParent).parseStream(filestream)
            filewriter.write(sasprogram)
            filewriter.close()
        })
    }
}
