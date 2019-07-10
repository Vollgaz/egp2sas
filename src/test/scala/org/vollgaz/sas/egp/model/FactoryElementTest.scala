package org.vollgaz.sas.egp.model

import java.io.{File, FileInputStream}

import org.scalatest.FlatSpec
import org.vollgaz.sas.egp.EgpProjectParser

class FactoryElementTest extends FlatSpec {
    val file              = new File("src/test/resources/project-KPI-Telephonie.xml")
    val egpparser: String = new EgpProjectParser("valuemock").parseStream(new FileInputStream(file))


}
