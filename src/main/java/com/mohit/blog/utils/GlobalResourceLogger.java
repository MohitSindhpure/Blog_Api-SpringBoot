package com.mohit.blog.utils;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * @author Mohit Sindhpure
 * @useClass {GlobalResourceLogger.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use  Output is Not Show Console only Create Log File. 
 * @see PackageName, Methods
 * @use Logger,All Class Levels
 * 
 */
public class GlobalResourceLogger {

	public static org.slf4j.Logger getLogger(Class classname)
	{
		return LoggerFactory.getLogger(classname);
		
	}
}
