name := "avrotuples"

version := "0.3-SNAPSHOT"

organization := "com.github.massie"

licenses := Seq("Apache 2.0" -> url("https://raw.githubusercontent.com/massie/avrotuples/master/LICENSE"))

homepage := Some(url("http://github.com/massie/avrotuples"))

crossScalaVersions := Seq("2.10.4", "2.11.6")

libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.7.7",
  "com.esotericsoftware" % "kryo" % "3.0.3",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

// To publish on maven-central, all required artifacts must also be hosted on maven central.
pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@github.com:massie/avrotuples.git</url>
    <connection>scm:git:git@github.com:massie/avrotuples.git</connection>
  </scm>
  <developers>
    <developer>
      <id>massie</id>
      <name>Matt Massie</name>
      <url>http://github.com/massie</url>
    </developer>
  </developers>)
