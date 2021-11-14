package com.cs.assignment.logAnalysis.pojo;

import lombok.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class LogData {
    private String id, type, host;
    private long timeStamp;
    private State stateEnum;
}
