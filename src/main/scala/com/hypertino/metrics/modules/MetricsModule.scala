package com.hypertino.metrics.modules

import com.codahale.metrics.MetricRegistry
import com.hypertino.metrics.{MetricsTracker, MetricsTrackerImpl}
import scaldi.Module

class MetricsModule extends Module {
  bind [MetricRegistry] to injected[MetricRegistry]
  bind [MetricsTracker] to injected[MetricsTrackerImpl]
}
