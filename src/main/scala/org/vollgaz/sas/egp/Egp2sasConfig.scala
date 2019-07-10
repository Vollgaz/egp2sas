package org.vollgaz.sas.egp

import java.io.File

case class Egp2sasConfig(src: File = new File("."),
                         dest: File = new File(".")                        )
