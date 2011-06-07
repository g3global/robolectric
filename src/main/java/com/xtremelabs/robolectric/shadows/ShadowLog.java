package com.xtremelabs.robolectric.shadows;

import java.text.MessageFormat;

import android.util.Log;

import com.xtremelabs.robolectric.internal.Implements;


@Implements(Log.class)
public class ShadowLog {
	
	private static final String messageFormat = "ANDROID LOG-[LEVEL:{0}] TAG:{1}\tMSG:{2}";
	private static final String DEBUG_LEVEL = "DEBUG";
	private static final String INFO_LEVEL = "INFO";
	private static final String WARNING_LEVEL = "WARN";
	private static final String VERBOSE_LEVEL = "VERBOSE";
	
	
	public static int d(String tag, String message){
		writeMessage(DEBUG_LEVEL, tag, message);
		return 0;
	}

	public static int i(String tag, String message){
		writeMessage(INFO_LEVEL, tag, message);
		return 0;
	}
	
	public static int w(String tag, String message){
		writeMessage(WARNING_LEVEL, tag, message);
		return 0;
	}
	
	public static int v(String tag, String message){
		writeMessage(VERBOSE_LEVEL, tag, message);
		return 0;
	}
	
	private static void writeMessage(String level, String tag, String message){
		System.out.println(buildLogMessageForDisplay(level, tag, message));
	}

	static String buildLogMessageForDisplay(String level, String tag,
			String message) {
		return MessageFormat.format(messageFormat, level, tag, message);
	}
}
