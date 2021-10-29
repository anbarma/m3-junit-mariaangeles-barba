package com.example.demo.service;

import com.example.demo.domain.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryCalculatorServiceTest {

    @Test
    void calculateSalary() {
        IRPFCalculator irpfCalculator = new IRPFCalculator();
        IVACalculator ivaCalculator = new IVACalculator();
        //double resultIRPF = irpfCalculator.calculateIRPF(8000);
        //double resultIVA = ivaCalculator.calculateIVA(8000);
        Employee empleado1 = new Employee(1L, "Pepe", 35);
        SalaryCalculatorService calculasalario = new SalaryCalculatorService(irpfCalculator, ivaCalculator);
        double sueldo = calculasalario.calculateSalary(empleado1);
        assertEquals(46615,25, sueldo);

    }
}