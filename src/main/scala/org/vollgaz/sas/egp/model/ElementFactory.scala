package org.vollgaz.sas.egp.model

import scala.collection.immutable
import scala.xml.{Node, NodeSeq}

object ElementFactory {

    def buildElementsCollection(nodeseq: NodeSeq): immutable.Seq[Element] = {
        nodeseq.map(node => new Element(getNodeId(node), getNodeAncestor(node), getNodeType(node)))
    }

    def buildElement(node: Node): Option[Element] = {
        Option(getNodeId(node)) match {
            case Some(NodeElementEnum.PFD.toString)      => getElementWorkflow(node)
            case Some(NodeElementEnum.CODE.toString)     => getElementCode(node)
            case Some(NodeElementEnum.CODETASK.toString) => getElement(node)
            case Some(NodeElementEnum.QUERY.toString)    => getElement(node)
            case Some(NodeElementEnum.EGTASK.toString)   => getElement(node)
            case _                                       => None
        }
    }

    def getElementCode(node: Node): Option[ElementCode] = {
        throw new NotImplementedError()
    }

    def getElement(node: Node): Option[Element] = {
        throw new NotImplementedError()
    }

    def getElementWorkflow(node: Node): Option[ElementWorkflow] = {
        throw new NotImplementedError()
    }


    def getNodeType(node: Node): String = (node \@ "Type").toString

    def getNodeId(node: Node): String = (node \ "Element" \ "ID").text

    def getNodeAncestor(node: Node): String = (node \ "Element" \ "InputIDs").text

    def getProcessFlow(node: Node): immutable.Seq[String] = (node \ "PFD" \ "Process").map(x => (x \ "Element" \ "ID").text)

    def getCodeSubmitted(node: Node): String = (node \ "Code" \ "TaskCode").text
}
