package com.example.demo.service;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartPhoneServiceImplTest {

    @BeforeEach
    void setUp() {

    }

    @DisplayName("Comprobar el contador")
    @Test
    void countTest() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
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
       SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        List<SmartPhone> smartphones =  service.findAll();

        assertNotNull(smartphones);
        assertEquals(3, smartphones.size());
    }

    @DisplayName("Comprobar si encuentra el ID=1")
    @Test
    void findOneId1Test() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        SmartPhone phone1  = service.findOne(1L);

        assertNotNull(phone1);
        assertEquals(1L, phone1.getId());
        assertNotNull(phone1.getName());
    }

    @DisplayName("Comprobar que no encuentra smartphone con ID muy grande")
    @Test
    void findOnePhone999Test() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        SmartPhone phone1  = service.findOne(999L);

        assertNull(phone1);
    }

    @DisplayName("Comprobar captura de excepción al buscar un smartphone nulo")
    @Test
    void findOneExceptionTest() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        // verifica si se ha lanzado una excepción
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findOne(null)
        );
    }

    @DisplayName("Comprobar si lo guarda")
    @Test
    void saveTest() {
    }

    @DisplayName("Comprobar excepción cuando el smartphone a guardar es nulo")
    @Test
    void saveNullTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(null)
        );
    }

    @DisplayName("Comprobar que se asigna un id correctamente")
    @Test
    void saveIdNullTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        SmartPhone phone = new SmartPhone(null, "Otro smartphone",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3, service.count());
        SmartPhone result = service.save(phone);
        assertEquals(4, service.count());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }

    @DisplayName("Comprobar que se asigna un id cuando el id que pasamos es 0")
    @Test
    void saveIdZeroTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        SmartPhone phone = new SmartPhone(0L, "Another one!",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3, service.count());
        SmartPhone result = service.save(phone);
        assertEquals(4, service.count());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }

    @DisplayName("Comprobar id negativo, no se debería añadir un smartphone")
    @Test
    void saveNegativeIdTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        SmartPhone phone = new SmartPhone(-4L, "Smartphone pirata",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3, service.count());
        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(phone)
        );
        assertEquals(3, service.count());
    }

    @DisplayName("Comprobar borrado con ID=1")
    @Test
    void deleteOkTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(1L);
        assertTrue(result);
    }

    @DisplayName("Comprobar borrado cuando el Smartphone es nulo")
    @Test
    void deleteNullTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(null);
        assertFalse(result);
    }

    @DisplayName("Comprobar borrado cuando el la ID no existe")
    @Test
    void deleteNotContainsTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(666L);
        assertFalse(result);
    }

    @DisplayName("Comprobar si borra todos")
    @Test
    void deleteAllTest() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        assertTrue(service.count() > 0);
        service.deleteAll();
        assertEquals(0, service.count());

    }

    @DisplayName("Comprobar si encuentra por Wifi")
    @Test
    void findByWifiTest() {
    }

    @DisplayName("Comprobar que se actualiza un smartphone existente")
    @Test
    void saveUpdateTest(){

        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone = new SmartPhone(1L, "One plus 9 editado",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        // comprobar num smartphones
        assertEquals(3, service.count());
        SmartPhone result = service.save(phone);
        assertEquals(3, service.count()); // no se crea un smartphone, se actualiza uno que ya existe

        // comprobar id
        assertEquals(1L, result.getId());

        // comprobar name
        SmartPhone phone1 = service.findOne(1L);
        assertEquals("One plus 9 editado", phone1.getName());

    }
}