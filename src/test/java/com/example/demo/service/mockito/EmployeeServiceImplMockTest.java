package com.example.demo.service.mockito;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplMockTest {
    EmployeeRepository repository; //dependencia
    EmployeeService service; //System Under Test

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepository.class);
        service = new EmployeeServiceImpl(repository);
    }

    @Test
    void count() {
        //given
        when(repository.count()).thenReturn(3);
        //when
        Integer result = service.count();
        //then
        assertNotNull(result);
        assertTrue(result > 0);
        assertEquals(3, result);
    }

    @Test
    void findAll() {
        //given
        List<Employee> lista = new ArrayList<Employee>();
          lista.add(new Employee(1L, "Emp 1", 30));
          lista.add(new Employee(2L, "Emp 2", 40));
          lista.add(new Employee(3L, "Emp 3", 50));
        when(repository.findAll()).thenReturn(lista);
        //when
        service.findAll();
        //then
        assertNotNull(lista);
        assertEquals(3, lista.size());
    }

    @Test
    void findOne() {
        //given
        Employee empleado1 = new Employee(1L, "Pepe Sánchez", 45);
        when(repository.findOne(1L)).thenReturn(empleado1);
        //when
        Employee result = service.findOne(1L);
        //then
        assertNotNull(result);
        assertEquals(1,result.getId());
        assertNotNull(empleado1.getName());
    }

    @Test
    void findOneOptional() {
    }

    @Test
    void save() {

    }

    @Test
    void deleteOKTest() {
        //given
        when(repository.delete(1L)).thenReturn(true);
        //when
        boolean result = service.delete(1L);
        //then
        assertTrue(result);
    }

    @Test
    void deleteAll() {
        //given
        when(repository.count()).thenReturn(0);
        //when
        service.deleteAll();
        //then
        assertEquals(0, service.count());
    }
}