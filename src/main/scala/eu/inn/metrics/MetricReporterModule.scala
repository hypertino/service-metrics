package eu.inn.metrics

import com.codahale.metrics.MetricRegistry
import scaldi.Module

class MetricReporterModule(prefix: String) extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [MetricReporter] to new MetricReporterImpl(prefix, inject[MetricRegistry])
}
