import java.util.concurrent.TimeUnit

import com.codahale.metrics.{ConsoleReporter, Gauge, MetricRegistry}
import eu.inn.metrics.Metrics
import eu.inn.metrics.modules.ConsoleReporterModule
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FreeSpec, Matchers}
import scaldi.Injectable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class TestMetricsReporter extends FreeSpec with Matchers with Injectable with ScalaFutures {
  implicit val injector = new ConsoleReporterModule

  "MetricsReporter should instantiate" - {
    val metrics = inject[Metrics]

    metrics.counter("counter").inc(100500)
    metrics.meter("meter").mark(15)
    metrics.histogram("histogram").update(13)
    metrics.histogram("histogram").update(12)
    metrics.histogram("histogram").update(13)
    metrics.histogram("histogram").update(15)

    metrics.timer("timer1").update(10, TimeUnit.SECONDS)

    metrics.timerOf("measure-sync") {
      Thread.sleep(10)
    }

    metrics.timerOfFuture("measure-async") {
      Future {
        Thread.sleep(10)
      }
    }.futureValue

    metrics.gauge("gauge1") {
      100500
    }

    metrics.gauge("gauge1") {
      100501
    }

    metrics.gauge("gauge2", new Gauge[Int] {
      override def getValue = 3
    })

    val registry = inject[MetricRegistry]
    val consoleReporter = ConsoleReporter.forRegistry(registry).build()
    consoleReporter.report()
    true shouldBe true
  }
}
