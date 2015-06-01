package com.doctor.slf4j.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Logback Mapped Diagnostic Contexts (MDC)
 * 
 * Dapper分布式跟踪系统
 * 
 * @see http://examples.javacodegeeks.com/enterprise-java/logback/logback-mapped-diagnostic-contexts-mdc-example/
 *      http://logback.qos.ch/manual/mdc.html
 *      https://github.com/bigbully/Dapper-translation
 * 
 * @author doctor
 *
 * @time 2015年6月1日 上午9:32:22
 */
public class Slf4jMdcPractice {

	public static void main(String[] args) {
		// You can put values in the MDC at any time. Before anything else
		// we put the first name

		MDC.put("first", "Dorothy");

		Logger logger = LoggerFactory.getLogger(Slf4jMdcPractice.class);
		// We now put the last name
		MDC.put("last", "Parker");

		logger.info("I am not a crook.");
		logger.info("I am not a crook.2");

	}

}
