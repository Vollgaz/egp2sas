package org.vollgaz.sas.egp

import org.vollgaz.sas.egp.model._

import scala.xml.{Elem, NodeSeq, XML}


class Egpparser(filepath : String) {

    private val xmlModel   : Elem         = XML.loadFile(filepath)
    private val xmlElements: NodeSeq      = xmlModel \\ "Elements" \ "Element"
    private val elements   : Seq[Element] = FactoryElement.buildElementsCollection(xmlElements)

    def generateSASProgramm(): String = {
        val strBuilder = new StringBuilder
        val taskToCode: Map[String, String] = linkTaskToCode()
        val flowToTask: Seq[String] = linkWorkflowToTask()

        flowToTask.foreach(taskid => {
            val strCode = taskToCode.getOrElse(taskid, "")
            strBuilder.append(s"$strCode\n")
        })
        strBuilder.mkString
    }

    private def linkTaskToCode(): Map[String, String] = {
        this.elements
            .filter(_.nodetype == EnumNodeElement.CODE.toString)
            .filter(_.ancestor != "")
            .map(x => x.ancestor -> x.asInstanceOf[ElementCode].code).toMap
    }

    private def linkWorkflowToTask(): Seq[String] = {
        this.elements
            .filter(_.nodetype == EnumNodeElement.PFD.toString)
            .foldLeft(Seq.empty[String]) {
                (tasklist, element) => tasklist ++ element.asInstanceOf[ElementWorkflow].workflow
            }
    }
}
