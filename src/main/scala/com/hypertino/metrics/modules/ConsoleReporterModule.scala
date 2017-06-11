package com.hypertino.metrics.modules

import com.codahale.metrics.ScheduledReporter
import com.hypertino.metrics.loaders.{ConsoleReporterLoader, MetricsReporterLoader}

import scala.concurrent.duration.Duration

class ConsoleReporterModule(period: Duration) extends MetricsModule {
  bind [MetricsReporterLoader] identifiedBy 'consoleMetricsReporterLoader toNonLazy new ConsoleReporterLoader(period)
  bind [ScheduledReporter] identifiedBy 'consoleMetricsReporter to {
    inject[MetricsReporterLoader](
      identified by 'consoleMetricsReporterLoader
    ).asInstanceOf[ConsoleReporterLoader].consoleReporter
  }
}
