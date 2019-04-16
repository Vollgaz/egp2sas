package org.vollgaz.sas.egp

import java.io.InputStream

import org.vollgaz.sas.egp.model._

import scala.xml.{Elem, XML}


class EgpProjectParser() {
    def parseStream(stream: InputStream): String = {
        generateSASProgram(XML.load(stream))
    }

    private def generateSASProgram(xmlModel: Elem): String = {
        val elements = FactoryElement.buildElementsCollection(xmlModel)
        val taskToCode: Map[String, String] = linkTaskToCode(elements)
        val flowToTask: Seq[String] = linkWorkflowToTask(elements)

        val strBuilder = new StringBuilder
        flowToTask.foreach(taskid => {
            val strCode = taskToCode.getOrElse(taskid, "")
            strBuilder.append(s"$strCode\n")
        })
        strBuilder.mkString
    }

    private def linkTaskToCode(elements: Seq[Element]): Map[String, String] = {
        elements
            .filter(_.nodetype == EnumNodeElement.CODE.toString)
            .filter(_.ancestor != "")
            .map(x => {
                println(s"id : ${x.id}    type : ${x.nodetype}   class : ${x.getClass}")
                x.ancestor -> x.asInstanceOf[ElementCode].code
            }).toMap
    }

    private def linkWorkflowToTask(elements: Seq[Element]): Seq[String] = {
        elements
            .filter(_.nodetype == EnumNodeElement.PFD.toString)
            .foldLeft(Seq.empty[String]) {
                (tasklist, element) => tasklist ++ element.asInstanceOf[ElementWorkflow].workflow
            }
    }


}
