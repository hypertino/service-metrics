name := "service-metrics"

version := "0.3-SNAPSHOT"

organization := "com.hypertino"

scalaVersion := "2.12.1"

crossScalaVersions := Seq("2.12.1", "2.11.8")

libraryDependencies ++= Seq(
  "io.dropwizard.metrics" % "metrics-core" % "3.2.2",
  "org.scaldi" %% "scaldi" % "0.5.8",
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % "test"
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("public")
)
