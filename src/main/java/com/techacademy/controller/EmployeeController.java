package com.techacademy.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.FieldError;


@Controller
public class EmployeeController {
   private final EmployeeService service;
   private final AuthenticationService authService;

@Autowired
private PasswordEncoder passwordEncorder;

   public EmployeeController(EmployeeService service,AuthenticationService authService) {
       this.service = service;
       this.authService = authService;
   }
    // --- 一覧画面 ---


    @GetMapping("/employee/index")
    public String getIndex(Model model) {
        //全件検索をModelに登録
        model.addAttribute("EmployeeList",service.getEmployeeList());

        // index.htmlに遷移
        return"employee/index";
    }

    /** 登録画面を表示*/
    @GetMapping("/employee/register")
    public String getRegister(@ModelAttribute Employee employee) {
        //登録画面に遷移
        return "employee/register";
    }

    /** employee登録処理 */
    @PostMapping("/employee/register")
    public String postRegister(@Validated Employee employee,BindingResult res,Model model){
        Optional<Authentication> employeecode = authService.getEmployeeCode(employee.getAuthentication().getCode());
        if(res.hasErrors()||employeecode.isPresent()) {
            return getRegister(employee);
        }
        LocalDateTime dateTime = LocalDateTime.now();
        employee.setCreatedAt(dateTime);
        employee.setUpdatedAt(dateTime);
        employee.getAuthentication().setEmployee(employee);
        employee.getAuthentication().setPassword(passwordEncorder.encode(employee.getAuthentication().getPassword()));
        employee.setDelete_flag(0);
        service.saveEmployee(employee);
        return "redirect:/employee/index";
        }

    /** 従業員詳細画面を表示　 */
    @GetMapping("/employee/detail/{id}/")
    public String getEmployeedetail(@PathVariable("id")Integer id,Model model){
        //Modelに登録
        Employee tableEmployee = service.getEmployee(id);
        model.addAttribute("employeedetail",tableEmployee);
        //詳細画面に遷移
        return "employee/detail";
    }

    /** 更新画面を表示　 */
    @GetMapping("employee/update/{id}/")
    public String getEmployee(@PathVariable("id")Integer id,Model model){
        //Modelに登録
        model.addAttribute("employee",service.getEmployee(id));
        //更新画面に遷移
        return "employee/update";
    }

   /** Employee更新処理 */
   @PostMapping("employee/update/{id}/")
   public String postEmployee(@PathVariable("id")Integer id ,Employee employee,Model model) {
       Employee tableEmployee = service.getEmployee(id);
       LocalDateTime updatetime = LocalDateTime.now();
       tableEmployee.setUpdatedAt(updatetime);
       tableEmployee.setName(employee.getName());
       String emppass = employee.getAuthentication().getPassword();
       if(emppass == null || emppass.equals("")) {
           tableEmployee.getAuthentication().setRole(employee.getAuthentication().getRole());
           service.saveEmployee(tableEmployee);
           return"redirect:/employee/index";
       }
       employee.getAuthentication().setPassword(emppass);
       tableEmployee.getAuthentication().setPassword(passwordEncorder.encode(emppass));
       service.saveEmployee(tableEmployee);
       return"redirect:/employee/index";
    }

   /** 削除処理　*/
   @GetMapping("/employee/delete/{id}/")
   public String deleteEmployee(@PathVariable("id") Integer id , Model model) {
       //一括削除
       service.deleteEmployee(id);
       return "redirect:employee/index";
   }

}

