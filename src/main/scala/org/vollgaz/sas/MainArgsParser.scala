package org.vollgaz.sas

import java.io.File

import scopt.{OParser, OParserBuilder}

class MainArgsParser(args: Array[String]) {
    val builder: OParserBuilder[MainConfig] = OParser.builder[MainConfig]
    val parser : OParser[Unit, MainConfig]  = {
        import builder._
        OParser.sequence(
            programName(this.getClass.getPackage.getImplementationTitle),
            head("version", this.getClass.getPackage.getImplementationVersion),
            help("help").text("Print help"),
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
                .text("must be a folder."),
            opt[Unit]('y', "no-prompt")
                .optional()
                .valueName("")
                .action((_, config) => config.copy(noprompt = true))
                .text("Hide prompt asking to process.")
        )

    }

    def getConfig: Option[MainConfig] = {
        OParser.parse(parser, args, MainConfig()) match {
            case Some(config) => Some(config)
            case _            => None
        }
    }

}
