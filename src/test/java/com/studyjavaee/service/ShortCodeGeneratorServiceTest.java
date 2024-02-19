package com.studyjavaee.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortCodeGeneratorServiceTest {

    @Test
    void generateShortCode_SizeEqualsFour() {
        assertEquals(4,
                ShortCodeGeneratorService.generateShortCode(4).length(),
                "Expected code generated to have lenght equals 4.");
    }
    @Test
    void generateShortCode_SizeEqualsSix() {
        assertEquals(6,
                ShortCodeGeneratorService.generateShortCode(6).length(),
                "Expected code generated to have lenght equals 6.");
    }
    @Test
    void generateShortCode_SizeEqualsZero_throwsIllegalArgumentException() {
        try{
            ShortCodeGeneratorService.generateShortCode(0);
        }catch(Exception e){
            assertInstanceOf(IllegalArgumentException.class, e, "Expected IllegalArgumentException");
        }
    }
    @Test
    void generateShortCode_SizeNegative_throwsIllegalArgumentException() {
        try{
            ShortCodeGeneratorService.generateShortCode(-1);
        }catch(Exception e){
            assertInstanceOf(IllegalArgumentException.class, e, "Expected IllegalArgumentException");
        }
    }
}