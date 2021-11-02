package com.example.demo.repository;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryImplTest {

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Comprobar el contador")
    @Test
    void count() {
        EmployeeRepository repository = new EmployeeRepositoryImpl();

        Integer num = repository.count();
        assertAll(
                () -> assertNotNull(num),
                () -> assertTrue(num > 0),
                () -> assertEquals(3, num)
        );
    }

    @DisplayName("Comprobar si muestra todos")
    @Test
    void findAll() {
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        List<Employee> employees =  repository.findAll();

        assertNotNull(employees);
        assertEquals(3, employees.size());
    }

    @DisplayName("Comprobar si encuentra el ID=1")
    @Test
    void findOne() {
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        Employee empID1  = repository.findOne(1L);

        assertNotNull(empID1);
        assertEquals(1L, empID1.getId());
        assertNotNull(empID1.getName());
    }

    @DisplayName("Comprobar que no encuentra smartphone con ID muy grande")
    @Test
    void findOnePhone999Test() {
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        Employee empID99  = repository.findOne(999L);

        assertNull(empID99);
    }

    @DisplayName("Comprobar captura de excepción al buscar un smartphone nulo")
    @Test
    void findOneExceptionTest() {
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        // verifica si se ha lanzado una excepción
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.findOne(null)
        );
    }

    @Test
    void save() {
    }
    @Disabled("Hasta corregir bug NUllPointerException")
    @DisplayName("Comprobar excepción cuando el empleado a guardar es nulo")
    @Test
    void saveNullTest(){
        EmployeeRepository repository = new EmployeeRepositoryImpl();

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.save(null)
        );
    }

    @Disabled("Hasta corregir bug ID negativo")
    @DisplayName("Comprobar id negativo, no se debería añadir un empleado")
    @Test
    void saveNegativeIdTest(){
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        Employee empleado = new Employee(-4L, "Empleado desconocido",40);

        assertEquals(3, repository.count());
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.save(empleado)
        );
        assertEquals(3, repository.count());
    }

    @DisplayName("Comprobar borrado con ID=1")
    @Test
    void deleteOkTest(){
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        boolean result = repository.delete(1L);
        assertTrue(result);
    }

    @DisplayName("Comprobar borrado cuando el Smartphone es nulo")
    @Test
    void deleteNullTest(){
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        boolean result = repository.delete(null);
        assertFalse(result);
    }

    @DisplayName("Comprobar borrado cuando el la ID no existe")
    @Test
    void deleteNotContainsTest(){
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        boolean result = repository.delete(666L);
        assertFalse(result);
    }

    @DisplayName("Comprobar si borra todos")
    @Test
    void deleteAllTest() {
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        assertTrue(repository.count() > 0);
        repository.deleteAll();
        assertEquals(0, repository.count());

    }

    @DisplayName("Máximo")
    @Test
    void savegetMaxIdTest() {
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        Employee empleado = new Employee();
        repository.delete(1L);
        repository.deleteAll();
        assertEquals(1L, repository.save(empleado).getId());
    }

    @DisplayName("Máximo")
    @Test
    void savegetMaxIdnotNullTest() {
        Map<Long, Employee> employees = new HashMap<>();
        Employee emp1 = new Employee(1L, "Emp 1", 30);
        Employee emp2 = new Employee(2L, "Emp 2", 40);
        Employee emp3 = new Employee(3L, "Emp 3", 50);
        employees.put(1L, emp1);
        employees.put(2L, emp2);
        employees.put(3L, emp3);

        EmployeeRepository repository = new EmployeeRepositoryImpl();

        Employee empleado1 = new Employee(0L,"Pepe Benabente",35);
        assertEquals(4L, repository.save(empleado1).getId());
    }
   }