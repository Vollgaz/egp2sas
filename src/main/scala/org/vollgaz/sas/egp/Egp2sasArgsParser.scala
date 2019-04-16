package org.vollgaz.sas.egp

import java.io.File

import scopt.{OParser, OParserBuilder}

class Egp2sasArgsParser(args: Array[String]) {
    val builder: OParserBuilder[Egp2sasConfig] = OParser.builder[Egp2sasConfig]
    val parser: OParser[Unit, Egp2sasConfig] = {
        import builder._
        OParser.sequence(
            programName("Egp2sas"),
            head("Egp2sas", "0.1.0"),
            opt[File]('f', "src")
                .required()
                .valueName("<filepath>")
                .action((file, config) => config.copy(src = file))
                .validate(file =>
                    if (!file.exists()) failure("-f --src doesn't exist")
                    else success
                )
                .text("can be file or folder."),
            opt[File]('o', "dest")
                .required()
                .valueName("<filepath>")
                .action((file, config) => config.copy(dest = file))
                .validate(file =>
                    if (!file.exists()) failure("-o --dest must point to an existing folder")
                    else if (!file.isDirectory) failure("-o --dest must be a folder")
                    else success
                )
                .text("must be a folder."))

    }

    def getConfig: Option[Egp2sasConfig] = {
        OParser.parse(parser, args, Egp2sasConfig()) match {
            case Some(config) => Some(config)
            case _ => None
        }
    }

}
