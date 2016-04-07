package eu.inn.metrics

import java.lang.management.ManagementFactory
import com.sun.management.{OperatingSystemMXBean, UnixOperatingSystemMXBean}
import scala.collection.JavaConversions._

object ProcessMetrics {
  def startReporting(reporter: MetricReporter): Unit = {
    ManagementFactory.getOperatingSystemMXBean match {
      case unix: UnixOperatingSystemMXBean ⇒
        reporter.gauge("system.fd.max")(unix.getMaxFileDescriptorCount)
        reporter.gauge("system.fd.open")(unix.getOpenFileDescriptorCount)

        reporter.gauge("system.cpu.time")(unix.getProcessCpuTime)
        reporter.gauge("system.cpu.load")((unix.getProcessCpuLoad * 100).toLong)

      case other: OperatingSystemMXBean ⇒
        reporter.gauge("system.cpu.time")(other.getProcessCpuTime)
        reporter.gauge("system.cpu.load")((other.getProcessCpuLoad * 100).toLong)
    }


    val mem = ManagementFactory.getMemoryMXBean
    reporter.gauge("system.heap.max")(mem.getHeapMemoryUsage.getMax)
    reporter.gauge("system.heap.used")(mem.getHeapMemoryUsage.getUsed)
    reporter.gauge("system.heap.committed")(mem.getHeapMemoryUsage.getCommitted)

    ManagementFactory.getGarbageCollectorMXBeans.foreach { gc ⇒
      reporter.gauge(s"gc.${gc.getName.replaceAll("[^\\w]+", "-")}.count")(gc.getCollectionCount)
      reporter.gauge(s"gc.${gc.getName.replaceAll("[^\\w]+", "-")}.time")(gc.getCollectionTime)
    }
  }
}
