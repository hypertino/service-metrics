package eu.inn.metrics

import com.codahale.metrics.{ConsoleReporter, MetricRegistry}
import scaldi.Module

class ConsoleReporterModule(prefix: String) extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [MetricReporter] to new MetricReporterImpl(prefix, inject[MetricRegistry])
  bind [ConsoleReporter] to ConsoleReporter.forRegistry(inject[MetricRegistry]).build()
}
