package com.example.demo.service;

import com.example.demo.domain.Employee;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    @BeforeEach
    void setUp() {

    }
    @DisplayName("Comprobar el contador")
    @Test
    void countTest() {
        EmployeeRepository employeeRepository;
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        Integer num = service.count();
        assertAll(
                () -> assertNotNull(num),
                () -> assertTrue(num > 0),
                () -> assertEquals(3, num)
        );
    }

    @DisplayName("Comprobar si muestra todos")
    @Test
    void findAllTest() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        List<Employee> employees =  service.findAll();

        assertNotNull(employees);
        assertEquals(3, employees.size());
    }

    @DisplayName("Comprobar si encuentra el ID=1")
    @Test
    void findOneId1Test() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        Employee empID1  = service.findOne(1L);

        assertNotNull(empID1);
        assertEquals(1L, empID1.getId());
        assertNotNull(empID1.getName());
    }

    @DisplayName("Comprobar que no encuentra smartphone con ID muy grande")
    @Test
    void findOnePhone999Test() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        Employee empID99  = service.findOne(999L);

        assertNull(empID99);
    }

    @DisplayName("Comprobar captura de excepción al buscar un smartphone nulo")
    @Test
    void findOneExceptionTest() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        // verifica si se ha lanzado una excepción
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findOne(null)
        );
    }

    @Test
    void findOneOptional() {
    }

    @Test
    void save() {
    }
    @Disabled("Hasta corregir bug NUllPointerException")
    @DisplayName("Comprobar excepción cuando el empleado a guardar es nulo")
    @Test
    void saveNullTest(){
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(null)
        );
    }
    @Disabled("Hasta corregir bug ID negativo")
    @DisplayName("Comprobar id negativo, no se debería añadir un empleado")
    @Test
    void saveNegativeIdTest(){
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        Employee empleado = new Employee(-4L, "Empleado desconocido",40);

        assertEquals(3, service.count());
        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(empleado)
        );
        assertEquals(3, service.count());
    }

    @DisplayName("Comprobar borrado con ID=1")
    @Test
    void deleteOkTest(){
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        boolean result = service.delete(1L);
        assertTrue(result);
    }

    @DisplayName("Comprobar borrado cuando el Smartphone es nulo")
    @Test
    void deleteNullTest(){
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        boolean result = service.delete(null);
        assertFalse(result);
    }

    @DisplayName("Comprobar borrado cuando el la ID no existe")
    @Test
    void deleteNotContainsTest(){
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        boolean result = service.delete(666L);
        assertFalse(result);
    }

    @DisplayName("Comprobar si borra todos")
    @Test
    void deleteAllTest() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        assertTrue(service.count() > 0);
        service.deleteAll();
        assertEquals(0, service.count());

    }
}