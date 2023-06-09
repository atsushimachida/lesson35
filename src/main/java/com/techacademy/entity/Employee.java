package com.techacademy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.techacademy.service.EmployeeDetail;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Employee")
@Where(clause = "delete_flag = 0")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 名前。20桁。null不許可 */
    @Column(length = 20, nullable = false)
    @NotNull
    @NotEmpty
    private String name;


    /** 削除フラグ　*/
    @Column
    private Integer delete_flag;

    /** 登録日時　*/
    @Column
    private LocalDateTime createdAt;

    /** 更新日時　*/
    @Column
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy="employee",cascade = CascadeType.ALL)
    private Authentication authentication;

    @OneToMany(mappedBy="employee",cascade = CascadeType.ALL)
    private List<Report> reports;



}

