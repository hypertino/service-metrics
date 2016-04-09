package eu.inn.metrics.modules

import com.codahale.metrics.ScheduledReporter
import eu.inn.metrics.loaders.{MetricsReporterLoader, Slf4jReporterLoader}

import scala.concurrent.duration.Duration

class Slf4jReporterModule(period: Duration) extends MetricsModule {
  bind [MetricsReporterLoader] identifiedBy 'logMetricsReporterLoader toNonLazy new Slf4jReporterLoader(period)
  bind [ScheduledReporter] identifiedBy 'logMetricsReporter to {
    inject[MetricsReporterLoader](
      identified by 'logMetricsReporterLoader
    ).asInstanceOf[Slf4jReporterLoader].slf4jReporter
  }
}
