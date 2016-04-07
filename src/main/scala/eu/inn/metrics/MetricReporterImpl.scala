package eu.inn.metrics

import java.util.concurrent.atomic.AtomicReference

import com.codahale.metrics._

import scala.collection.concurrent.TrieMap

private [metrics] class MetricReporterImpl(prefix: String, registry: MetricRegistry) extends MetricReporter {
  protected val gauges = TrieMap[String, ReplaceableGauge[_]]()

  override def counter(name: String): Counter = {
    registry.counter(prefix + "." + name)
  }

  override def meter(name: String): Meter = {
    registry.meter(prefix + "." + name)
  }

  override def histogram(name: String): Histogram = {
    registry.histogram(prefix + "." + name)
  }

  override def timer(name: String): Timer = {
    registry.timer(prefix + "." + name)
  }

  def gauge[T](name: String, gauge: Gauge[T]): Unit = {
    val fullName = prefix + "." + name
    gauges.putIfAbsent(fullName, new ReplaceableGauge[T](gauge)) match {
      case Some(existing) ⇒ existing.replaceUnderlying(gauge)
      case None ⇒ registry.register(name, gauge)
    }
  }

  def remove(name: String): Unit = {
    val fullName = prefix + "." + name
    gauges.remove(name)
    registry.remove(fullName)
  }

  def removeAll(): Unit = {
    gauges.clear()
    registry.removeMatching(MetricFilter.ALL)
  }
}

private [metrics] class ReplaceableGauge[T](initUnderlying: Gauge[T]) extends Gauge[T] {
  private val underlyingGauge = new AtomicReference[Gauge[T]](initUnderlying)

  override def getValue = {
    underlyingGauge.get().getValue
  }

  def replaceUnderlying[X](newUnderlyingGauge: Gauge[X]): Unit = {
    underlyingGauge.set(newUnderlyingGauge.asInstanceOf[Gauge[T]])
  }
}
