package org.vollgaz.sas.egp

import java.io.FileWriter

import org.scalatest.FlatSpec

class EgpProjectParserTest extends FlatSpec {
    val parser = new EgpProjectParser().parseFile("src/test/resources/project-KPI-Telephonie.xml")
    val filew  = new FileWriter("target/sasout.sas")
    filew.write(parser)
    filew.close()
}
