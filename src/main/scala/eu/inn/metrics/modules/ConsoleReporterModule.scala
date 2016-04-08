package eu.inn.metrics.modules

import eu.inn.metrics.loaders.{ConsoleReporterLoader, MetricsReporterLoader}

class ConsoleReporterModule extends MetricsModule {
  bind [MetricsReporterLoader] to injected[ConsoleReporterLoader]
}
