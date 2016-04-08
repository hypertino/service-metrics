package eu.inn.metrics.loaders

import com.codahale.metrics.{JmxReporter, MetricRegistry}

class JmxReporterLoader(registry: MetricRegistry) extends MetricsReporterLoader {
  override def run(): Unit = {
    val jmxReporter = JmxReporter.forRegistry(registry).build()
    jmxReporter.start()
  }
}
