package org.vollgaz.sas.egp

import java.io.{File, FileWriter}

import org.scalatest.FlatSpec

class EgpReaderTest extends FlatSpec {
    val egpreader: EgpReader  = new EgpReader(new File("src/test/resources/KPI_Telephonie.egp"))
    val filew    : FileWriter = new FileWriter("target/project-KPI-Telephonie.xml")
    filew.write(egpreader.getProjectStream.read().toString)
    filew.close()

}
