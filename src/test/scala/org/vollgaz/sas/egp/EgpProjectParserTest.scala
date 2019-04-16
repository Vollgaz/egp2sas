package org.vollgaz.sas.egp

import java.io.{FileInputStream, FileWriter}

import org.scalatest.FlatSpec

class EgpProjectParserTest extends FlatSpec {
    val parser: String = new EgpProjectParser().parseStream(new FileInputStream("src/test/resources/project-KPI-Telephonie.xml"))
    val filew: FileWriter = new FileWriter("target/sasout.sas")
    filew.write(parser)
    filew.close()
}
