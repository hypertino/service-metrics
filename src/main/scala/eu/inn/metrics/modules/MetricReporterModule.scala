package eu.inn.metrics.modules

import com.codahale.metrics.MetricRegistry
import eu.inn.metrics.{MetricReporter, MetricReporterImpl}
import scaldi.Module

class MetricReporterModule(prefix: String) extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [MetricReporter] to new MetricReporterImpl(prefix, inject[MetricRegistry])
}
