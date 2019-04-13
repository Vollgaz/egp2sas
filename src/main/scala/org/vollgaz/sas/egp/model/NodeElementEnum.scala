package org.vollgaz.sas.egp.model

package object NodeElementEnum extends Enumeration {
  val CODE: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.Code")
  val CODETASK: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.CodeTask")
  val EGTASK: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.EGTask")
  val LINK: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.Link")
  val LOG: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.Log")
  val ODSRESULT: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.ODSResult")
  val PFD: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.PFD")
  val QUERY: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.Query")
  val SHORTCUTTODATA: NodeElementEnum.Value = Value("SAS.EG.ProjectElements.ShortCutToData")


  implicit def value2string(value: Value): String = value.toString
}
