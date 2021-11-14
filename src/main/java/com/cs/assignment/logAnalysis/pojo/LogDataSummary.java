package com.cs.assignment.logAnalysis.pojo;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Entity
@Table(name = "LogDataSummary")
public class LogDataSummary {

    @Id
    private String id;
    private String type, host;
    private boolean alert;
    private long timeTaken;

    public LogDataSummary(){
    }

    public LogDataSummary(String id, String type, String host, boolean alert, long timeTaken) {
        this.id = id;
        this.type = type;
        this.host = host;
        this.alert = alert;
        this.timeTaken = timeTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogDataSummary that = (LogDataSummary) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
