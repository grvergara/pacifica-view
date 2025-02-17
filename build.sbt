import play.ebean.sbt.PlayEbean
import play.sbt.PlayJava
import sbt.{TestFrameworks, Tests}

name := """pacifica"""
organization := "cl.ro"

version := "0.1"

lazy val `pacifica` = (project in file(".")).enablePlugins(PlayJava, PlayEbean, SbtWeb)

//scalaVersion := "2.13.16"                                                                                                                                                                                                 
scalaVersion := "2.12.20" // For Akka 2.5.30

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += ws
libraryDependencies += evolutions
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.18"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.20"
libraryDependencies += "com.typesafe.akka" %% "akka-contrib" % "2.4.20"

libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0" % Test
libraryDependencies += javaJdbc % Test
libraryDependencies += scalaOrganization.value % "scala-compiler" % scalaVersion.value

libraryDependencies += "de.grundid.opendatalab" % "geojson-jackson" % "1.1"

libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"
libraryDependencies += "org.webjars" % "jquery" % "3.7.1"
libraryDependencies += "org.webjars" % "bootstrap" % "3.0.0"
libraryDependencies += "org.webjars" % "leaflet" % "1.7.1"
libraryDependencies += "org.webjars" % "knockout" % "2.3.0"
libraryDependencies += "org.webjars" % "requirejs" % "2.1.11-1"
libraryDependencies += "org.webjars" % "rjs" % "2.1.11-1-trireme" % "test"
libraryDependencies += "org.webjars" % "squirejs" % "0.1.0" % "test"

testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")

Keys.fork in Test := true
javaOptions in Test ++= Seq("-Dconfig.file=conf/application.test.conf")
parallelExecution in Test := false

MochaKeys.requires += "SetupMocha.js"

pipelineStages := Seq(rjs, digest, gzip)
