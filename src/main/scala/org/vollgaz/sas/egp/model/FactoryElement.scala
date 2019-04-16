package org.vollgaz.sas.egp.model

import scala.collection.immutable
import scala.xml.{Elem, Node}

object FactoryElement {

    def buildElementsCollection(xmlModel: Elem): Seq[Element] = {
        val xmlElements = xmlModel \\ "Elements" \ "Element"
        xmlElements.map(buildElement)
    }

    def buildElement(node: Node): Element = {
        val nodetype = extractType(node)
        nodetype match {
            case x if x.equals(EnumNodeElement.PFD.toString) => doElementWorkflow(node)
            case x if x.equals(EnumNodeElement.CODE.toString) => doElementCode(node)
            case _ => doElement(node)
        }
    }

    def doElement(node: Node): Element = {
        new Element(extractId(node),
            extractAncestor(node),
            extractType(node))
    }

    def doElementWorkflow(node: Node): ElementWorkflow = {
        new ElementWorkflow(extractId(node),
            extractAncestor(node),
            extractType(node),
            extractProcessFlow(node))
    }

    def doElementCode(node: Node): ElementCode = {
        new ElementCode(extractId(node),
            extractAncestor(node),
            extractType(node),
            extractCodeSubmitted(node))
    }

    private def extractProcessFlow(node: Node): immutable.Seq[String] = (node \ "PFD" \ "Process").map(x => (x \ "Element" \ "ID").text)

    private def extractId(node: Node): String = (node \ "Element" \ "ID").text

    private def extractAncestor(node: Node): String = (node \ "Element" \ "InputIDs").text

    private def extractType(node: Node): String = (node \@ "Type").toString

    private def extractCodeSubmitted(node: Node): String = (node \ "Code" \ "TaskCode").text
}
