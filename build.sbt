name := "egp2sas"
organization := "org.vollgaz.sas"
scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
    "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
    "com.github.scopt" %% "scopt" % "4.0.0-RC2",
    "org.scalatest" %% "scalatest" % "3.1.0-RC1" % Test
)