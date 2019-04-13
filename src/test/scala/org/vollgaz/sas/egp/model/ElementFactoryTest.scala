package org.vollgaz.sas.egp.model

import org.scalatest.FlatSpec
import org.vollgaz.sas.egp.Egpparser

class ElementFactoryTest extends FlatSpec {
    val egpparser = new Egpparser("src/test/resources/project-KPI-Telephonie.xml")
    getNodeTypeTest




    def getNodeTypeTest()  = {
        egpparser.extractElements().foreach(
            x => println(ElementFactory.getNodeType(x))
        )
    }
}
