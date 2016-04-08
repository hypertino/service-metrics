package eu.inn.metrics.loaders

import java.util.concurrent.TimeUnit

import com.codahale.metrics.{ConsoleReporter, MetricRegistry}

class ConsoleReporterLoader(registry: MetricRegistry) extends MetricsReporterLoader {
  override def run(): Unit = {
    val consoleReporter = ConsoleReporter.forRegistry(registry).build()
    consoleReporter.start(1, TimeUnit.MINUTES) // todo: move to config
  }
}
