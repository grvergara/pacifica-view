import play.ebean.sbt.PlayEbean
import play.sbt.PlayJava
import sbt.{TestFrameworks, Tests}

name := """pacifica"""
organization := "cl.ro"

version := "0.1"

lazy val `pacifica` = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.14"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += ws
libraryDependencies += evolutions
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.18"

libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0" % Test
libraryDependencies += javaJdbc % Test
libraryDependencies += scalaOrganization.value % "scala-compiler" % scalaVersion.value

testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")

Keys.fork in Test := true
javaOptions in Test ++= Seq("-Dconfig.file=conf/application.test.conf")
parallelExecution in Test := false
