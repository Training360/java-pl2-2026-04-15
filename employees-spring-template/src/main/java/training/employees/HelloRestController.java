package training.employees;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloRestController {

    private final HelloService helloService;

    @GetMapping("/api/hello")
    public String hello() {
        log.info("Hello");
        return helloService.hello();
    }
}
