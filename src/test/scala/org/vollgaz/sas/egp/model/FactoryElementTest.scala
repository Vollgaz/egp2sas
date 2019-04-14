package org.vollgaz.sas.egp.model

import org.scalatest.FlatSpec
import org.vollgaz.sas.egp.EgpProjectParser

class FactoryElementTest extends FlatSpec {
    val egpparser = new EgpProjectParser().parseFile("src/test/resources/project-KPI-Telephonie.xml")


}
