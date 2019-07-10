package org.vollgaz.sas.egp.model

object EnumNodeElement extends Enumeration {
  val CODE          : EnumNodeElement.Value = Value("SAS.EG.ProjectElements.Code")
  val CODETASK      : EnumNodeElement.Value = Value("SAS.EG.ProjectElements.CodeTask")
  val EGTASK        : EnumNodeElement.Value = Value("SAS.EG.ProjectElements.EGTask")
  val LINK          : EnumNodeElement.Value = Value("SAS.EG.ProjectElements.Link")
  val LOG           : EnumNodeElement.Value = Value("SAS.EG.ProjectElements.Log")
  val ODSRESULT     : EnumNodeElement.Value = Value("SAS.EG.ProjectElements.ODSResult")
  val PFD           : EnumNodeElement.Value = Value("SAS.EG.ProjectElements.PFD")
  val QUERY         : EnumNodeElement.Value = Value("SAS.EG.ProjectElements.Query")
  val SHORTCUTTODATA: EnumNodeElement.Value = Value("SAS.EG.ProjectElements.ShortCutToData")
}
