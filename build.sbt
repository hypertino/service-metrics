import sbt.Keys._

name := "service-metrics"

version := "0.2"

organization := "eu.inn"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8")

libraryDependencies ++= Seq(
  "io.dropwizard.metrics" % "metrics-core" % "3.1.0",
  "org.scaldi" %% "scaldi" % "0.5.6",
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)
