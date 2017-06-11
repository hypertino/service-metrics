package com.hypertino.metrics.loaders

import java.util.concurrent.TimeUnit

import com.codahale.metrics.{MetricRegistry, Slf4jReporter}
import scaldi.{Injectable, Injector}

import scala.concurrent.duration.Duration

class Slf4jReporterLoader(period: Duration)(implicit injector: Injector) extends MetricsReporterLoader with Injectable {
  lazy val slf4jReporter = {
    Slf4jReporter.forRegistry(inject[MetricRegistry]).build()
  }

  override def run(): Unit = {
    if (period.isFinite) {
      slf4jReporter.start(period.toMillis, TimeUnit.MILLISECONDS)
    } else {
      slf4jReporter.hashCode() //instantiate lazy val
    }
  }
}
