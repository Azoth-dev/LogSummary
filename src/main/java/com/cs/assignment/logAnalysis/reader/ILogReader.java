package com.cs.assignment.logAnalysis.reader;

import com.cs.assignment.logAnalysis.pojo.LogData;

import java.util.Set;

public interface ILogReader {
    public Set<LogData> getLogData(String path);
}
