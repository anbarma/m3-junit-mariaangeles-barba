package com.example.demo.service;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
//@Suite
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JUnit Test")
@SelectPackages("com.example.demo")
//@IncludeClassNamePatterns(".*Test")
public class SuiteTest {
}
