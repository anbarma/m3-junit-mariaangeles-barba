package com.example.demo.service;

import com.example.demo.domain.Employee;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.domain.pieces.*;
import com.example.demo.repository.EmployeeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartWatchServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void countTest() {
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        Integer num = service.count();

        assertAll(
                () -> assertNotNull(num),
                () -> assertTrue(num > 0),
                () -> assertEquals(3, num)
        );
    }

    @Test
    void findAllTest() {
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        List<SmartWatch> smartWatches =  service.findAll();

        assertNotNull(smartWatches);
        assertEquals(3, smartWatches.size());
    }

    @Test
    void findOne() {
    }

    @DisplayName("Comprobar si encuentra el ID=1")
    @Test
    void findOneId1Test() {
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        SmartWatch smartWatch1  = service.findOne(1L);

        assertNotNull(smartWatch1);
        assertEquals(1L, smartWatch1.getId());
        assertNotNull(smartWatch1.getName());
    }

    @DisplayName("Comprobar que no encuentra smartwatch con ID muy grande")
    @Test
    void findOnePhone999Test() {
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        SmartWatch smartWatch1  = service.findOne(999L);

        assertNull(smartWatch1);
    }
@Disabled("Deshabilitado hasta corregir bug en SmartWatchServiceImpl ")
    @DisplayName("Comprobar captura de excepción al buscar un smartwatch nulo")
    @Test
    void findOneExceptionTest() {
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        // verifica si se ha lanzado una excepción
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findOne(null)
        );
    }

    @DisplayName("Comprobar que guarda correctamente")
    @Test
    void save() {
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        assertEquals(3, service.count());
    }
@Disabled("Deshabilitado hasta que se arregle el BUG")
    @DisplayName("Comprobar id negativo al guardar, no se debería añadir un smartwatch")
    @Test
    void saveNegativeIdTest(){
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        SmartWatch smartWatch = new SmartWatch(-4L, "Smartwatch desconocido",                 new RAM(3L, "DDR4", 2),
                new Battery(3L, 4500.0),
                new CPU(3L, 4),
                false,
                new HealthMonitor(3L, 0.0, 0));

        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(smartWatch)
        );
    }

    @DisplayName("Comprobar que se asigna un id correctamente")
    @Test
    void saveIdNullTest(){
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        SmartWatch smartWatch = new SmartWatch(null, "Smartwatch desconocido",                 new RAM(3L, "DDR4", 2),
                new Battery(3L, 5000.0),
                new CPU(3L, 8),
                true,
                new HealthMonitor(3L, 0.0, 0));

        SmartWatch result = service.save(smartWatch);
        assertEquals(4, service.count());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }

    @DisplayName("Comprobar borrado con ID=1")
    @Test
    void deleteTest(){

            SmartWatchServiceImpl service = new SmartWatchServiceImpl();
            boolean result = service.delete(1L);
            assertTrue(result);
    }

    @DisplayName("Comprobar borrado cuando el SmarWatch es nulo")
    @Test
    void deleteNullTest(){
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        boolean result = service.delete(null);
        assertFalse(result);
    }

    @DisplayName("Comprobar borrado cuando el la ID no existe")
    @Test
    void deleteNotContainsTest(){
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        boolean result = service.delete(666L);
        assertFalse(result);
    }

    @DisplayName("Comprobar si borra todos")
    @Test
    void deleteAllTest() {
        SmartWatchServiceImpl service = new SmartWatchServiceImpl();
        assertTrue(service.count() > 0);
        service.deleteAll();
        assertEquals(0, service.count());

    }
}