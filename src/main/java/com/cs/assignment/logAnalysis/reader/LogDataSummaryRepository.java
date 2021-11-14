package com.cs.assignment.logAnalysis.reader;

import com.cs.assignment.logAnalysis.pojo.LogDataSummary;
import org.springframework.data.repository.CrudRepository;

public interface LogDataSummaryRepository extends CrudRepository<LogDataSummary, Long> {
}
