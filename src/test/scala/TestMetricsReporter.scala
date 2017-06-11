import java.util.concurrent.TimeUnit

import com.codahale.metrics.{Gauge, ScheduledReporter}
import com.hypertino.metrics.MetricsTracker
import com.hypertino.metrics.modules.ConsoleReporterModule
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FreeSpec, Matchers}
import scaldi.Injectable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration

class TestMetricsReporter extends FreeSpec with Matchers with Injectable with ScalaFutures {
  implicit val injector = new ConsoleReporterModule(Duration.Undefined)

  "MetricsReporter should instantiate" - {
    val metrics = inject[MetricsTracker]

    metrics.counter("counter").inc(100500)
    metrics.meter("meter").mark(15)
    metrics.histogram("histogram").update(13)
    metrics.histogram("histogram").update(12)
    metrics.histogram("histogram").update(13)
    metrics.histogram("histogram").update(15)

    metrics.timer("timer1").update(10, TimeUnit.SECONDS)

    metrics.timeOf("measure-sync") {
      Thread.sleep(10)
    }

    metrics.timeOfFuture("measure-async") {
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

    val reporter = inject[ScheduledReporter]
    reporter shouldNot equal (null)
    reporter.report()
  }
}
