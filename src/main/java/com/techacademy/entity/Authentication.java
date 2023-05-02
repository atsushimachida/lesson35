package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import lombok.Data;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Authentication")
public class Authentication {

    public static enum Role{
        管理者,一般
    }

    @Id
    @Column(length = 20, nullable = false)
    private String code;

    /** password。255桁。null不許可 */
    @Column(length = 255, nullable = false)
    private String password;

    /** 権限 */
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /** 従業員id */
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}