package org.vollgaz.sas.egp

import java.io.{File, FileWriter}

import org.scalatest.FlatSpec

class EgpIntegrationTest extends FlatSpec {

    val file = new File("src/test/resources/KPI_Telephonie.egp")
    val filewriter: FileWriter = new FileWriter(s"target/${file.getName}.sas")
    val filestream = new EgpReader(file).getProjectStream
    val sasprogram: String = new EgpProjectParser().parseStream(filestream)
    filewriter.write(sasprogram)
    filewriter.close()

}
