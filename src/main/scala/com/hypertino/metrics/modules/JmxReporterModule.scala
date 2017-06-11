package com.hypertino.metrics.modules

import com.hypertino.metrics.loaders.{JmxReporterLoader, MetricsReporterLoader}

class JmxReporterModule extends MetricsModule {
  bind [MetricsReporterLoader] to injected[JmxReporterLoader]
}
