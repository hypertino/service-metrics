package eu.inn.metrics.modules

import eu.inn.metrics.loaders.{JmxReporterLoader, MetricsReporterLoader}

class JmxReporterModule(prefix: String) extends MetricsModule(prefix) {
  bind [MetricsReporterLoader] to injected[JmxReporterLoader]
}
