package org.vollgaz.sas.egp

import java.io.File

import org.scalatest.FlatSpec

class EgpScannerTest extends FlatSpec {
    val egpscanner: EgpScanner = new EgpScanner(new File("src/"))
    egpscanner.findEGPfiles().foreach(x => println(x.getAbsolutePath))


}
