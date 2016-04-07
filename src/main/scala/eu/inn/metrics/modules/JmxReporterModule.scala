package eu.inn.metrics.modules

import com.codahale.metrics.{JmxReporter, MetricRegistry}
import eu.inn.metrics.{MetricReporter, MetricReporterImpl}
import scaldi.Module

class JmxReporterModule(prefix: String) extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [MetricReporter] to new MetricReporterImpl(prefix, inject[MetricRegistry])
  bind [JmxReporter] to JmxReporter.forRegistry(inject[MetricRegistry]).build()
}
