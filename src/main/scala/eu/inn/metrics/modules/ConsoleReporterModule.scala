package eu.inn.metrics.modules

import com.codahale.metrics.{ConsoleReporter, MetricRegistry}
import eu.inn.metrics.{MetricReporter, MetricReporterImpl}
import scaldi.Module

class ConsoleReporterModule(prefix: String) extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [MetricReporter] to new MetricReporterImpl(prefix, inject[MetricRegistry])
  bind [ConsoleReporter] to ConsoleReporter.forRegistry(inject[MetricRegistry]).build()
}
