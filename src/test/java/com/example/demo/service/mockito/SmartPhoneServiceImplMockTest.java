package com.example.demo.service.mockito;

import com.example.demo.domain.SmartPhone;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class SmartPhoneServiceImplMockTest {
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

    }

    @Test
    void findOneTest() {
        //given

        //when

        //then

    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void findByWifi() {
    }
}