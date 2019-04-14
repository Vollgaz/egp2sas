package org.vollgaz.sas.egp

import java.io.FileWriter

import org.scalatest.FlatSpec

class EgpReaderTest extends FlatSpec {
    val egpreader: EgpReader = new EgpReader("src/test/resources/KPI_Telephonie.egp")
    val filew                = new FileWriter("target/project.xml")
    filew.write(egpreader.getProjectContent)
    filew.close()


}
