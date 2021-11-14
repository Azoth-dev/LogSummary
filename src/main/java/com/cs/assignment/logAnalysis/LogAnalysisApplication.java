package com.cs.assignment.logAnalysis;

import com.cs.assignment.logAnalysis.controller.LogAnalysisController;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j

/**
 * Spring boot to initialize spring context and load HSQL embedded data base
 *  please provide log location as runtime argument to application.
 *
 */
public class LogAnalysisApplication {

		public static void main(String[] args) {
			log.info("Starting Log Analysis tool for CS Assignment....");

			if(!(args.length==1))
				throw new RuntimeException("Please provide log file location in order to start the application");

			ConfigurableApplicationContext context = SpringApplication.run(LogAnalysisApplication.class, args);
			LogAnalysisController bean = context.getBean(LogAnalysisController.class);
			bean.process(args[0]);
		}

}
