package com.cs.assignment.logAnalysis.reader;

import com.cs.assignment.logAnalysis.pojo.LogData;
import com.cs.assignment.logAnalysis.pojo.State;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Slf4j
@Component
public class FileLogReader implements ILogReader {

    private LogData parseLogData(String line) {
        JSONObject jsonObject = new JSONObject(line);
        return LogData.builder().id(jsonObject.getString("id")).host(jsonObject.getString("host")).stateEnum(jsonObject.getEnum(State.class, "state")).timeStamp(jsonObject.getLong("timestamp")).type(jsonObject.getString("type"))
                .build();
    }

    @Override
    public Set<LogData> getLogData(String path) {
        Set<LogData> logDataSet= new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                log.debug("Reading line: {}",line);
                if(line!=null && !line.isEmpty())
                    logDataSet.add(parseLogData(line));
                else
                    log.warn("Blank or empty lines in the file");
            }
        } catch (FileNotFoundException e) {
            log.error("Error while reading file from file location:{}", path, e);
            throw new RuntimeException(e); //wrapping IO to runtime exception to save writing catch blocks everywhere
        }
        log.info("Total lines parsed from the file is "+ logDataSet.size());
        return logDataSet;
    }
}