package com.xtremelabs.robolectric.shadows;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.xtremelabs.robolectric.WithTestDefaultsRunner;

@RunWith(WithTestDefaultsRunner.class)
public class Log {
	
	
	@Test
	public void ensureDisplayLogMessageIsFormattedCorrectly(){
		String logMessage = "Test Log Message";
		String level = "DEBUG";
		String tag = "MyTag";
		String expectedMessage = "ANDROID LOG:\n\tLEVEL:" + level + "TAG:"+ tag + "Message:" + logMessage;
		
		String actual = ShadowLog.buildLogMessageForDisplay(level, tag, logMessage);
		
		assertThat(actual, equalTo(expectedMessage));
		
	}

}
