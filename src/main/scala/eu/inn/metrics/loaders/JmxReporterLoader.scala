package eu.inn.metrics.loaders

import com.codahale.metrics.{JmxReporter, MetricRegistry}
import scaldi.{Injectable, Injector}

import scala.concurrent.duration.Duration

class JmxReporterLoader(period: Duration)(implicit injector: Injector) extends MetricsReporterLoader with Injectable {
  lazy val jmxReporter = {
    JmxReporter.forRegistry(inject[MetricRegistry]).build()
  }

  override def run(): Unit = {
    jmxReporter.start()
  }
}
