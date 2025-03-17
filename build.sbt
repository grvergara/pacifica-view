import play.ebean.sbt.PlayEbean
import play.sbt.PlayJava
import sbt.{TestFrameworks, Tests}

name := """pacifica"""
organization := "cl.ro"

version := "0.1"

lazy val `pacifica` = (project in file(".")).enablePlugins(PlayJava, PlayEbean, SbtWeb)

scalaVersion := "2.13.16"                                                                                                                                                                                                 
//scalaVersion := "2.12.20" // For Akka 2.5.30

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += ws
libraryDependencies += evolutions
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.18"
libraryDependencies += "com.typesafe.play" %% "play-jdbc" % "2.7.9"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.31"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster" % "2.5.31"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster-tools" % "2.5.31"
libraryDependencies += "com.typesafe.akka" %% "akka-contrib" % "2.5.31"
libraryDependencies += "com.typesafe.akka" %% "akka-persistence" % "2.5.31"

//[warn] a.u.ManifestInfo - Detected possible incompatible versions on the classpath. 
//Please note that a given Akka version MUST be the same across all modules of Akka that you are using, 
//e.g. if you use [2.5.31] all other modules that are released together MUST be of the same version. Make sure you're using a compatible set of libraries.
//Possibly conflicting versions [2.5.23, 2.5.31] in libraries [akka-persistence:2.5.23, akka-actor:2.5.31, akka-slf4j:2.5.31, akka-cluster:2.5.23, akka-cluster-tools:2.5.23, akka-protobuf:2.5.31, akka-coordination:2.5.23, akka-remote:2.5.23, akka-stream:2.5.31]


libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0" % Test
libraryDependencies += javaJdbc % Test
libraryDependencies += scalaOrganization.value % "scala-compiler" % scalaVersion.value

libraryDependencies += "de.grundid.opendatalab" % "geojson-jackson" % "1.1"

libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"
libraryDependencies += "org.webjars" % "jquery" % "3.7.1"
libraryDependencies += "org.webjars" % "bootstrap" % "3.4.1"
libraryDependencies += "org.webjars" % "leaflet" % "1.7.1"
libraryDependencies += "org.webjars" % "knockout" % "2.3.0"
libraryDependencies += "org.webjars" % "requirejs" % "2.1.11-1"
libraryDependencies += "org.webjars" % "rjs" % "2.1.11-1-trireme" % "test"
libraryDependencies += "org.webjars" % "squirejs" % "0.1.0" % "test"
libraryDependencies += "org.webjars" %% "webjars-play" % "2.7.3"
libraryDependencies += "org.webjars" % "flot" % "0.8.3"
libraryDependencies += "org.webjars.npm" % "three" % "0.169.0"


testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")

Keys.fork in Test := true
javaOptions in Test ++= Seq("-Dconfig.file=conf/application.test.conf")
parallelExecution in Test := false

MochaKeys.requires += "SetupMocha.js"

pipelineStages := Seq(rjs, digest, gzip)
