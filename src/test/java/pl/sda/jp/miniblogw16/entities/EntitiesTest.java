package pl.sda.jp.miniblogw16.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.jp.miniblogw16.user.UserRepository;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntitiesTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    @Rollback(false)
    public void shouldSaveEmployee() {

        if (!departmentRepository.findById("ACCOUNTING").isPresent()) {
            departmentRepository.save(new Department("ACCOUNTING", "Accounting"));
        }
        if (!departmentRepository.findById("IT").isPresent()) {
            departmentRepository.save(new Department("IT", "IT"));
        }

        Employee boss = employeeRepository.findById(1L).get();

        Employee employee = new Employee();
        employee.setName("Janusz v11");
        employee.setAddress(new Address("Sezamkowa"));
        employee.setLocation(new Location(3, "24b"));
        employee.setHobby(new Hobby("Internet"));
        employee.addTasks("CLEANING", "SHOPPING");
        employee.setDepartments(Arrays.asList(new Department("ACCOUNTING"), new Department("IT")));
        employee.setBoss(boss);
        employee.setPhoneNumbers(Arrays.asList(new PhoneNumber("997"), new PhoneNumber("998")));

        employeeRepository.save(employee);

    }

}
