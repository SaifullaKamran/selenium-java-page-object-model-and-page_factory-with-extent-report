package framework.automation.tests;

import org.testng.annotations.Test;

import framework.automation.drivers.PageDriver;
import framework.automation.utilities.CommonMethods;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class EmployeeTest extends CommonMethods{
	@Test 
	void addEmployee() {
		System.out.println(PageDriver.getCurrentDriver().getTitle());
	}

}
