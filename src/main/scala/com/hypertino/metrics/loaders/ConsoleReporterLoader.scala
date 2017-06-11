package com.hypertino.metrics.loaders

import java.util.concurrent.TimeUnit

import com.codahale.metrics.{ConsoleReporter, MetricRegistry}
import scaldi.{Injectable, Injector}

import scala.concurrent.duration.Duration

class ConsoleReporterLoader(period: Duration)
                           (implicit injector: Injector) extends MetricsReporterLoader with Injectable {
  lazy val consoleReporter = {
    ConsoleReporter.forRegistry(inject[MetricRegistry]).build()
  }

  override def run(): Unit = {
    if (period.isFinite) {
      consoleReporter.start(period.toMillis, TimeUnit.MILLISECONDS)
    } else {
      consoleReporter.hashCode() //instantiate lazy val
    }
  }
}
