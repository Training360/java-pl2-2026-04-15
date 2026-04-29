package empapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class EmployeeServiceBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private EmployeeDaoBean employeeDaoBean;

    @Inject
    private LogEntryDaoBean logEntryDaoBean;

    @Resource
    private TimerService timerService;

    @PostConstruct
    public void init() {
        System.out.println("Employee Service bean has created");
    }

    public List<Employee> findEmployees() {
        logger.info("List employees");
        logger.debug("List employees without parameters");

        return employeeDaoBean.findEmployees();
    }

    @Transactional
    public void saveEmployee(String name) {
        logEntryDaoBean.saveLogEntry("Save employee with name: " + name);

        employeeDaoBean.saveEmployee(name);

        timerService.createTimer(5000, name);

    }

    @Timeout
    public void logCreateEmployee(Timer timer) {
        String name = (String) timer.getInfo();
        System.out.println("Employee has created: " + name);
    }

}
