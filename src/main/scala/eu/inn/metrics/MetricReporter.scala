package eu.inn.metrics

import com.codahale.metrics._

import scala.concurrent.{ExecutionContext, Future}

trait MetricReporter {
  def counter(name: String): Counter
  def meter(name: String): Meter
  def histogram(name: String): Histogram
  def timer(name: String): Timer
  def gauge[T](name: String, gauge: Gauge[T]): Unit
  def remove(name: String): Unit
  def removeAll(): Unit

  def timerOf[A](name: String)(f: ⇒ A): A = {
    val timerContext = timer(name).time()
    try {
      f
    } finally {
      timerContext.close()
    }
  }

  def timerOfFuture[A](name: String)(f: ⇒ Future[A])(implicit ec: ExecutionContext): Future[A] = {
    val timerContext = timer(name).time()
    f andThen { case _ ⇒
      timerContext.close()
    }
  }

  def gauge[T](name: String)(f: ⇒ T): Unit = {
    gauge(name, new Gauge[T] {
      override def getValue: T = f
    })
  }
}
