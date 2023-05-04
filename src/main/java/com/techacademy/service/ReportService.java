package com.techacademy.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {

        this.reportRepository = reportRepository;
    }

    /** 全件を検索して返す */
    public List<Report> getReportList(){
        return reportRepository.findAll();
    }

    /** Employee登録処理　  */
    @Transactional
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    /** Employeeを1件検索して返す　*/
    public Report getReport(Integer id) {
        return reportRepository.findById(id).get();
    }
}