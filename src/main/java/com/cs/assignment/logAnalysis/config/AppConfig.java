package com.cs.assignment.logAnalysis.config;

import com.cs.assignment.logAnalysis.controller.LogAnalysisController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.cs.assignment")
public class AppConfig {

    @Bean
    public com.cs.assignment.logAnalysis.controller.LogAnalysisController logAnalysisController(){
        return new LogAnalysisController();
    }


}
