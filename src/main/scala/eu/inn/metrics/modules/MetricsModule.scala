package eu.inn.metrics.modules

import com.codahale.metrics.MetricRegistry
import eu.inn.metrics.{MetricsTracker, MetricsTrackerImpl}
import scaldi.Module

class MetricsModule extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [MetricsTracker] to injected[MetricsTrackerImpl]
}
