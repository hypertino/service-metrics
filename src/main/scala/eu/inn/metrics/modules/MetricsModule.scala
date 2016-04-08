package eu.inn.metrics.modules

import com.codahale.metrics.MetricRegistry
import eu.inn.metrics.{Metrics, MetricsImpl}
import scaldi.Module

class MetricsModule(prefix: String) extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [Metrics] to new MetricsImpl(prefix, inject[MetricRegistry])
}
