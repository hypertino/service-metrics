import com.codahale.metrics.ScheduledReporter
import eu.inn.metrics.loaders.MetricsReporterLoader
import eu.inn.metrics.modules.ConsoleReporterModule
import eu.inn.metrics.{MetricsTracker, ProcessMetrics}

import scala.concurrent.duration.Duration
import scala.io.StdIn

object TestMain extends ConsoleReporterModule(Duration.Inf) {

  def main(args: Array[String]): Unit = {
    ProcessMetrics.startReporting(inject[MetricsTracker])
    val reporterLoader = inject[MetricsReporterLoader]
    var break = false
    reporterLoader.run()
    while(!break) {
      break = StdIn.readLine() == "quit"
      inject[ScheduledReporter].report()
    }
  }
}

