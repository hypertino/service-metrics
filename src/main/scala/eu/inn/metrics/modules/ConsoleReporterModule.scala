package eu.inn.metrics.modules

import eu.inn.metrics.loaders.{ConsoleReporterLoader, MetricsReporterLoader}

class ConsoleReporterModule(prefix: String) extends MetricsModule(prefix) {
  bind [MetricsReporterLoader] to injected[ConsoleReporterLoader]
}
