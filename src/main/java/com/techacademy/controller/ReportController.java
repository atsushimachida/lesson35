package com.techacademy.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.techacademy.entity.Report;
import com.techacademy.service.AuthenticationService;
import com.techacademy.service.EmployeeDetail;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.ReportService;

import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ReportController {
   private final ReportService service;
   public ReportController(ReportService service) {
       this.service = service;
   }

    // ---　全員のレポート一覧画面 ---

    @GetMapping("/reportindex")
    public String getIndex(Model model) {
        //全件検索をModelに登録
        model.addAttribute("ReportList",service.getReportList());
        // index.htmlに遷移
        return"/report/reportindex";
    }

    /** 登録画面を表示*/
    @GetMapping("/report/reportregister")
    public String getRegister(@ModelAttribute Report report) {
        //登録画面に遷移
        return "report/reportregister";
    }

    /** report登録処理 */
    @PostMapping("/reportregister")
    public String postRegister(@Validated Report report,BindingResult res,@AuthenticationPrincipal EmployeeDetail employeeDetail){
        if(res.hasErrors()) {
            return getRegister(report);
        }
        Employee loginemp = employeeDetail.getEmployee();
       report.setEmployee(loginemp);
        LocalDateTime dateTime = LocalDateTime.now();
        report.setCreatedAt(dateTime);
        report.setUpdatedAt(dateTime);
        service.saveReport(report);
        return "redirect:/reportindex";
        }

    /** report詳細画面を表示　 */
    @GetMapping("/reportdetail/{id}/")
    public String getReportdetail(@PathVariable("id")Integer id,@AuthenticationPrincipal EmployeeDetail employeeDetail,Model model){
        Report tableReport = service.getReport(id);
        model.addAttribute("reportdetail",tableReport);
        //詳細画面に遷移
        return "report/reportdetail";
    }

    /** report更新画面を表示　 */
    @GetMapping("/reportupdate/{id}/")
    public String getReport(@PathVariable("id")Integer id,Model model){
     Report tableReport = service.getReport(id);
        //Modelに登録
        model.addAttribute("reportdetail",tableReport);
        //更新画面に遷移
        return "report/reportupdate";
    }

    /** report更新処理 */
    @PostMapping("/reportupdate/{id}/")
    public String postReport(@PathVariable("id")Integer id ,Report reportdetail,Model model) {
        Report tableReport = service.getReport(id);
        LocalDateTime updatetime = LocalDateTime.now();
        tableReport.setReport_date(reportdetail.getReport_date());
        tableReport.setUpdatedAt(updatetime);
        tableReport.setTitle(reportdetail.getTitle());
        tableReport.setContent(reportdetail.getContent());
        service.saveReport(tableReport);
        return"redirect:/reportindex";
    }
}