package training.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private final AmqpTemplate amqpTemplate;

    public List<EmployeeDto> findAll() {
        // CQRS: command and query responsibility segregation
        return employeeRepository
                .findAllBy(EmployeeDto.class);
    }

    public List<SummaryEmployeeDto> findAllSummary() {
        return employeeRepository
                .findAllBy(SummaryEmployeeDto.class);
    }

    public EmployeeDto join(EmployeeDto employee) {
//        if (employeeRepository.findByName(employee.name()).isPresent()) {
//            throw new IllegalArgumentException("Employee name already exists: %s".formatted(employee.name()));
//        }
        Employee entity = employeeMapper.toEntity(employee);
        Employee saved = employeeRepository.save(entity);

        amqpTemplate.convertAndSend("employees.queue", new EmployeeHasCreatedEvent(saved.getId(), saved.getName()));

        return employeeMapper.toDto(saved);
    }

    public EmployeeDto findById(Long id) {
        return employeeMapper.toDto(employeeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found: %d".formatted(id))));
    }

    @Transactional
    public EmployeeDto correct(EmployeeDto employee) {
        Employee entity = employeeRepository
                .findById(employee.id())
                .orElseThrow(() -> new NotFoundException("Employee not found: %d".formatted(employee.id())));
        entity.setName(employee.name());
        return employeeMapper.toDto(entity);
    }

    public void leave(long id) {
        employeeRepository.deleteById(id);
    }

}
