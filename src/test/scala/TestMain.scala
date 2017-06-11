import com.codahale.metrics.ScheduledReporter
import com.hypertino.metrics.{MetricsTracker, ProcessMetrics}
import com.hypertino.metrics.loaders.MetricsReporterLoader
import com.hypertino.metrics.modules.ConsoleReporterModule

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

