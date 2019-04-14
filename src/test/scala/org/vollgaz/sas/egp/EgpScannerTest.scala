package org.vollgaz.sas.egp

import org.scalatest.FlatSpec

class EgpScannerTest extends FlatSpec {
    val egpscanner = new EgpScanner("src/")
    egpscanner.findEGPfiles().foreach(x => println(x.getAbsolutePath))


}
