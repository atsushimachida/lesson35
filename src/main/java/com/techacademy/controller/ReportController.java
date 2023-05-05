package com.techacademy.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.service.AuthenticationService;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.ReportService;

import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ReportController {
   private final ReportService service;
   public ReportController(ReportService service) {
       this.service = service;
   }

    // --- 一覧画面 ---

    @GetMapping("/reportindex")
    public String getIndex(Model model) {
        //全件検索をModelに登録
        model.addAttribute("ReportList",service.getReportList());
        // index.htmlに遷移
        return"employee/report/index";
    }
}