package com.lvg.resttest.handlers;

import java.util.Properties;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeFieldHandler extends GeneralizedFieldHandler {
	
	private static String dateFormatPattern;
	
	
	
	@Override
	public void setConfiguration(Properties config) throws ValidityException {
		dateFormatPattern = config.getProperty("date-format");
	}

	@Override
	public Object convertUponGet(Object obj) {
		DateTime dateTime = (DateTime)obj;
		return format(dateTime);
	}

	@Override
	public Object convertUponSet(Object obj) {
		String stringDateTime = (String)obj;
		return parse(stringDateTime);
	}

	@Override
	public Class<DateTime> getFieldType() {		
		return DateTime.class;
	}
	
	protected static String format(final DateTime dateTime){
		String dateTimeString = "";
		if(dateTime != null){
			DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormatPattern);
			dateTimeString = formatter.print(dateTime);
		}
		return dateTimeString;
	}
	
	protected static DateTime parse(final String str){
		DateTime dateTime = new DateTime();
		if(str != null){
			DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormatPattern);
			dateTime = formatter.parseDateTime(str);
		}
		return dateTime;
	}
	
	

}
