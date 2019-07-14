package pl.sda.jp.miniblogw16.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class EmployeeTask extends BaseEntity {

    @Column
    private String taskName;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
