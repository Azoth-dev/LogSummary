package com.cs.assignment.logAnalysis.controller;

import com.cs.assignment.logAnalysis.pojo.LogData;
import com.cs.assignment.logAnalysis.pojo.LogDataSummary;
import com.cs.assignment.logAnalysis.pojo.State;
import com.cs.assignment.logAnalysis.reader.ILogReader;
import com.cs.assignment.logAnalysis.reader.LogDataSummaryRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main controller responsible for
 * a. Loading log file
 * b. run the analytics
 * c. Persist data in DB
 */
@Slf4j
@Data
public class LogAnalysisController {
    @Autowired
    private ILogReader logReader;

    @Autowired
    private LogDataSummaryRepository summaryRepository;

    public void process(String filePath) {
        log.info("Starting to load file from {}", filePath);

        //read log data from file
        Set<LogData> logData = logReader.getLogData(filePath);
        //create summary of logs
        Set<LogDataSummary> logDataSummary = getLogDataSummaries(logData);
        //save summary in embedded DB
        save(logDataSummary);
    }

    private Set<LogDataSummary> getLogDataSummaries(Set<LogData> logData) {
        Map<String, Map<State, List<LogData>>> logsById = logData.stream().collect(Collectors.groupingBy(LogData::getId, Collectors.groupingBy(LogData::getStateEnum)));
        Set<LogDataSummary> logDataSummary = logsById.entrySet().stream().map(x -> mapToLogSummary(x.getKey(), x.getValue())).collect(Collectors.toSet());
        return logDataSummary;
    }

    private void save(Set<LogDataSummary> logDataSummary) {
        log.info("Saving log data summary in the DB");
        summaryRepository.saveAll(logDataSummary);
        log.info("Log data summary is saved in DB and inserted {} records",logDataSummary.size());
    }

    private LogDataSummary mapToLogSummary(String id, Map<State, List<LogData>> logDataByState) {
        LogDataSummary logDataSummary = null;
        LogData startLogData = getLogDataByState(State.STARTED, logDataByState);
        LogData endLogData = getLogDataByState(State.FINISHED, logDataByState);

        if (startLogData != null && endLogData != null) {
            long timeTaken=startLogData.getTimeStamp()-endLogData.getTimeStamp();
            logDataSummary = new LogDataSummary(id,startLogData.getType(),startLogData.getHost(),timeTaken>4,timeTaken);
        }else
            log.warn("For ID: {} incorrect data present in the file, either we are missing start or finished state. ");
        return logDataSummary;
    }

    private LogData getLogDataByState(State state, Map<State, List<LogData>> logDataByState) {
        List<LogData> startLogDataList = logDataByState.entrySet().stream().filter(x -> state.equals(x.getKey())).map(x -> x.getValue()).flatMap(List::stream).collect(Collectors.toList());
        if (startLogDataList != null && !startLogDataList.isEmpty())
            return startLogDataList.get(0);
        else
            return null;
    }


}
