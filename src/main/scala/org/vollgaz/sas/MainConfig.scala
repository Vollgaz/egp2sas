package org.vollgaz.sas

import java.io.File

case class MainConfig(src     : File = new File("."),
                      dest    : File = new File("."),
                      noprompt: Boolean = false)
