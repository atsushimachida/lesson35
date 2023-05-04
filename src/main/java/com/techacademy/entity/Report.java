package com.techacademy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Report")

public class Report {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 日報の日付　*/
    @Column
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate reportdate;

    /** タイトル　*/
    @Column
    private String title;

    /** 日報の内容　*/
    @Column(nullable = false)
    @Type(type="text")
    private String content;


    /** 登録日時　*/
    @Column
    private LocalDateTime createdAt;

    /** 更新日時　*/
    @Column
    private LocalDateTime updatedAt;

    /** 従業員id */
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}

