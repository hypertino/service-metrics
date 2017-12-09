package com.hypertino.metrics.modules

import com.codahale.metrics.MetricRegistry
import com.hypertino.metrics.registry.HdrMetricRegistry
import com.hypertino.metrics.{MetricsTracker, MetricsTrackerImpl}
import scaldi.Module

class HdrMetricsModule extends Module {
  bind [MetricRegistry] to injected[HdrMetricRegistry]
  bind [MetricsTracker] to injected[MetricsTrackerImpl]
}
