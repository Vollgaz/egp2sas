package org.vollgaz.sas.egp.model

class ElementWorkflow(id: String, ancestor: String, nodetype: String, val workflow: Seq[String])
    extends Element(id: String, ancestor: String, nodetype: String)
