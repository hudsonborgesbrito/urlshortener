package com.studyjavaee.service;

import java.util.Random;

class ShortCodeGeneratorService {
    public static final int ZERO_ASCII_CODE = 48;
    public static final int Z_LOWERCASE_ASCII_CODE = 122;
    static Random random = new Random();
    private ShortCodeGeneratorService(){}
    static String generateShortCode(int size) throws IllegalArgumentException{
        if(size < 1){
            throw new IllegalArgumentException("Size must be higher than zero");
        }
        return random.ints(ZERO_ASCII_CODE, Z_LOWERCASE_ASCII_CODE + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
