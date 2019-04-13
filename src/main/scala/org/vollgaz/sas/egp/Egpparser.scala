package org.vollgaz.sas.egp

import scala.xml.{Elem, NodeSeq, XML}


class Egpparser(filepath : String){

  val xmlModel: Elem = XML.loadFile(filepath)

  /**
    * Give the all the 'runnable' elements of the project.
    * @return The collection of all elements of the SAS EGP Project.
    */
  def extractElements(): NodeSeq = xmlModel \\  "Elements" \ "Element"


}
