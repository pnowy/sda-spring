package pl.sda.jp.miniblogw16.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Employee extends BaseEntity {

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Embedded
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOBBY_ID")
    private Hobby hobby;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeTask> tasks;

    public void addTasks(String ... tasks) {
        this.tasks = Arrays.stream(tasks)
                .map(n -> {
                    EmployeeTask task = new EmployeeTask();
                    task.setEmployee(this);
                    task.setTaskName(n);
                    return task;
                })
                .collect(Collectors.toList());
    }

    @ManyToMany
    @JoinTable(name = "EMPLOYEE_TO_DEPARTMENT")
    private List<Department> departments;

    @ManyToOne
    @JoinColumn(name = "BOSS_ID")
    private Employee boss;

    @OneToMany(mappedBy = "boss")
    private List<Employee> subordinates = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "PHONE_NUMBER",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID")
    )
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

}
