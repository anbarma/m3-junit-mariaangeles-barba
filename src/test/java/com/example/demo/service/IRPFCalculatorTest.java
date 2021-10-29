package com.example.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IRPFCalculatorTest {
    IRPFCalculator calculator = new IRPFCalculator();

    @DisplayName("Comprobar si calcula el IRPF")
    @Test
    void calculateIRPF() {
        double result = calculator.calculateIRPF(5000);
        assertEquals(750, result);
    }
    @DisplayName("Comprobar si el IRPF es 0")
    @Test
    void calculateIRPFZeroTest(){
        double result = calculator.calculateIRPF(0);
        assertEquals(0, result);
    }
    @DisplayName("Comprobar si el IVA es negativo")
    @Test
    void calculateIRPFNegativeTest(){
        double result = calculator.calculateIRPF(-1000);
        assertEquals(-150, result);
    }

}