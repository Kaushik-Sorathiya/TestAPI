package framework.ExtentReports;

import org.apache.log4j.AppenderSkeleton;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import com.aventstack.extentreports.Status;

public class ExtentReportAppender extends AppenderSkeleton {

	// Custom Appender for Extent Reports
	@Override
	protected void append(final LoggingEvent event) {

		try {
		//ExtentTestManager.getTest().log(Status.INFO, eventToString(event));
		
		// Changed from getTest to GetNode to save the logs inside the current node
			
			ExtentTestManager.getNode();
		ExtentTestManager.getNode().log(Status.INFO, eventToString(event));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private String eventToString(final LoggingEvent event) {
		final StringBuilder result = new StringBuilder(layout.format(event));

		if (layout.ignoresThrowable()) {
			final String[] s = event.getThrowableStrRep();
			if (s != null) {
				for (final String value : s) {
					result.append(value).append(Layout.LINE_SEP);
				}
			}
		}
		return result.toString();
	}

	public void close() {

	}

	public boolean requiresLayout() {
		return true;
	}
}
