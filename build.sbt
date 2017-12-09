name := "service-metrics"

version := "0.3-SNAPSHOT"

organization := "com.hypertino"

crossScalaVersions := Seq("2.12.4", "2.11.12")

scalaVersion := crossScalaVersions.value.head

libraryDependencies ++= Seq(
  "io.dropwizard.metrics" % "metrics-core" % "3.2.5",
  "org.scaldi" %% "scaldi" % "0.5.8",
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "org.mpierce.metrics.reservoir" % "hdrhistogram-metrics-reservoir" % "1.1.0" % "provided",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % "test",
  "org.mpierce.metrics.reservoir" % "hdrhistogram-metrics-reservoir" % "1.1.0" % "test"
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("public")
)
