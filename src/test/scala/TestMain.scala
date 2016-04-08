import eu.inn.metrics.loaders.MetricsReporterLoader
import eu.inn.metrics.modules.ConsoleReporterModule
import eu.inn.metrics.{Metrics, ProcessMetrics}

import scala.io.StdIn

object TestMain extends ConsoleReporterModule("test-service") {

  def main(args: Array[String]): Unit = {
    ProcessMetrics.startReporting(inject[Metrics])
    val reporterLoader = inject[MetricsReporterLoader]
    var break = false
    reporterLoader.run()
    while(!break) {
      break = StdIn.readLine() == "quit"
    }
  }
}

