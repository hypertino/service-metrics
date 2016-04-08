package eu.inn.metrics.modules

import eu.inn.metrics.loaders.{JmxReporterLoader, MetricsReporterLoader}

class JmxReporterModule(prefix: String) extends MetricsModule {
  bind [MetricsReporterLoader] to injected[JmxReporterLoader]
}
