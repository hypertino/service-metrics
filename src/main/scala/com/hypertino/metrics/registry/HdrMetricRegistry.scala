package com.hypertino.metrics.registry

import com.codahale.metrics.{MetricRegistry, Timer}
import org.mpierce.metrics.reservoir.hdrhistogram.HdrHistogramResetOnSnapshotReservoir

class HdrMetricRegistry extends MetricRegistry {
  override def timer(name: String): Timer = timer(name, HdrTimerSupplier)

  object HdrTimerSupplier extends MetricRegistry.MetricSupplier[Timer] {
    override def newMetric(): Timer = new Timer(new HdrHistogramResetOnSnapshotReservoir);
  }
}
