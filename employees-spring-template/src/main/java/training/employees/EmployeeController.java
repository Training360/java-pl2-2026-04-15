package training.employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {

    private EmployeeService employeesService;

    @GetMapping("/")
    public ModelAndView findAll() {
        var model = new HashMap<String, Object>();
        model.put("employees", employeesService.findAll());
        model.put("command", new EmployeeDto(null, ""));

        return new ModelAndView("employees", model);
    }

    @GetMapping("/employees")
    public ModelAndView findById(@RequestParam long id) {
        var employee = employeesService.findById(id);
        return new ModelAndView("employee", "employee", employee);
    }


    @GetMapping("/join-employee")
    public ModelAndView join() {
        var model = Map.of(
                "command", new EmployeeDto(null, "")
        );
        return new ModelAndView("join-employee", model);
    }

    @PostMapping("/join-employee")
    public ModelAndView joinPost(@ModelAttribute EmployeeDto command) {
        employeesService.join(command);
        return new ModelAndView("redirect:/");
    }

}