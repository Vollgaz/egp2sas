package org.vollgaz.sas.egp

import java.io.InputStream

import org.vollgaz.sas.egp.model._

import scala.util.matching.Regex
import scala.xml.{Elem, XML}


class EgpProjectParser(inputFolder: String) {


    def parseStream(stream: InputStream): String = {
        generateSASProgram(XML.load(stream))
    }

    def generateSASProgram(xmlModel: Elem): String = {
        val elements = FactoryElement.buildElementsCollection(xmlModel)
        val taskToCode: Map[String, String] = linkTaskToCode(elements)
        val flowToTask: Seq[String] = linkWorkflowToTask(elements)

        val strBuilder = new StringBuilder
        strBuilder.append(s"""%include "/data/sas/autoexec.sas";""")
        flowToTask.foreach(taskid => {
            val strCode = taskToCode.getOrElse(taskid, "")
            strBuilder.append(s"${cleanContent(strCode)}\n")
        })
        strBuilder.mkString
    }

    def linkTaskToCode(elements: Seq[Element]): Map[String, String] = {
        elements
            .filter(_.nodetype == EnumNodeElement.CODE.toString)
            .filter(_.ancestor != "")
            .map(x => {
                x.ancestor -> x.asInstanceOf[ElementCode].code
            }).toMap
    }

    def linkWorkflowToTask(elements: Seq[Element]): Seq[String] = {
        elements
            .filter(_.nodetype == EnumNodeElement.PFD.toString)
            .foldLeft(Seq.empty[String]) {
                (tasklist, element) => tasklist ++ element.asInstanceOf[ElementWorkflow].workflow
            }
    }

    def cleanContent(submitedCode: String): String = {
        (submitedCode match {
            case x if x.contains("proc lasr") => s""
            case x if x.contains("INFILE")    => correctFileImport(x)
            case x                            => x
        })
            .replaceAll("%put ERROR:.*\n", "")
            .replaceAll("(?s)%_eg_(.*?);\n", "")
    }

    def correctFileImport(submitedCode: String): String = {
        val cleanCode = submitedCode
            .replaceAll("Fichier source :", "Source file:")
            .replaceAll("Serveur", "Server")
        // Regex with multiline enable
        val pattern: Regex =
            """(?s)Source file:(.*?)(Server)""".r
        val filepath = pattern
            .findAllIn(cleanCode).matchData.toSeq.head.group(1)
            // Remove SAS Indent (3 spaces). Made for pathfiles containing FUCKING spaces
            .replaceAll("([ ]{3})", "")
            // Make one line for path write on two lines. Handle windows jumpline too
            .replaceAll("\r*\n", "")
            // For windows windows pathfile
            .replace("\\", "\\\\")
            .split("\\\\")
            .last
            .trim
        submitedCode.replaceAll("INFILE .*", "INFILE \"" + inputFolder + "/" + filepath + "\"")
    }


}
