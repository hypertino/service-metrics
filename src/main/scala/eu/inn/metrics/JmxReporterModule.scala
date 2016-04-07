package eu.inn.metrics

import com.codahale.metrics.{JmxReporter, MetricRegistry}
import scaldi.Module

class JmxReporterModule(prefix: String) extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [MetricReporter] to new MetricReporterImpl(prefix, inject[MetricRegistry])
  bind [JmxReporter] to JmxReporter.forRegistry(inject[MetricRegistry]).build()
}
