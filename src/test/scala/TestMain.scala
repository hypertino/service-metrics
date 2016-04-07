import com.codahale.metrics.ConsoleReporter
import eu.inn.metrics.modules.ConsoleReporterModule
import eu.inn.metrics.{MetricReporter, ProcessMetrics}

import scala.io.StdIn

object TestMain extends ConsoleReporterModule("test-service") {

  def main(args: Array[String]): Unit = {
    ProcessMetrics.startReporting(inject[MetricReporter])
    val consoleReporter = inject[ConsoleReporter]
    var break = false
    while(!break) {
      break = StdIn.readLine() == "quit"
      consoleReporter.report()
    }
  }
}

