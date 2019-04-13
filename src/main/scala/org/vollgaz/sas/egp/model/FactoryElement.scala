package org.vollgaz.sas.egp.model

import scala.collection.immutable
import scala.xml.{Node, NodeSeq}

object FactoryElement {

    def buildElementsCollection(nodeseq: NodeSeq): Seq[Element] = {
        nodeseq.map(buildElement)
    }

    def buildElement(node: Node): Element = {
        getNodeType(node) match {
            case x if x.equals(EnumNodeElement.PFD.toString)  => getElementWorkflow(node)
            case x if x.equals(EnumNodeElement.CODE.toString) => getElementCode(node)
            case _                                            => getElement(node)
        }
    }

    private def getElementCode(node: Node): ElementCode = {
        new ElementCode(getNodeId(node),
            getNodeAncestor(node),
            getNodeType(node),
            getCodeSubmitted(node))
    }

    private def getCodeSubmitted(node: Node): String = (node \ "Code" \ "TaskCode").text

    private def getElement(node: Node): Element = {
        new Element(getNodeId(node),
            getNodeAncestor(node),
            getNodeType(node))
    }

    private def getElementWorkflow(node: Node): ElementWorkflow = {
        new ElementWorkflow(getNodeId(node),
            getNodeAncestor(node),
            getNodeType(node),
            getProcessFlow(node))
    }

    private def getNodeType(node: Node): String = (node \@ "Type").toString

    private def getNodeId(node: Node): String = (node \ "Element" \ "ID").text

    private def getNodeAncestor(node: Node): String = (node \ "Element" \ "InputIDs").text

    private def getProcessFlow(node: Node): immutable.Seq[String] = (node \ "PFD" \ "Process").map(x => (x \ "Element" \ "ID").text)
}
