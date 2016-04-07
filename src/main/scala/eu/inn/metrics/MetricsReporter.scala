package eu.inn.metrics

import java.util.concurrent.atomic.AtomicLong

import scala.concurrent.{ExecutionContext, Future}

trait MetricsReporter {
  def counter(name: String): metrics.Counter
  def meter(name: String): metrics.Meter
  def histogram(name: String): metrics.Histogram
  def getTimer(name: String): TimerContext
  def timer[A](name: String)(f: ⇒ A): A
  def futureTimer[T](name: String)(f: ⇒ Future[T])(implicit ec: ExecutionContext): Future[T]
  def newGauge(name: String)(f: ⇒ Long): Unit
  def gauge(name: String): GaugeWrapper
}

protected class GaugeWrapper {
  private val value = new AtomicLong()
  private[facade] def get: Long = value.get()

  def update(newValue: Long): Unit = value.set(newValue)
}
