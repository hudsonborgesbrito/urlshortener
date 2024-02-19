package com.studyjavaee.service;

import com.studyjavaee.entity.ShortenerURLMapping;
import com.studyjavaee.exception.DuplicatedCodeException;
import com.studyjavaee.repository.ShortURLRepositoryInMemory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class URLShortenerService {
    private static final Logger LOGGER = Logger.getLogger(URLShortenerService.class.getName());
    public static final int SHORT_URL_LENGTH = 4;
    public static final int MAX_RETRY = 3;

    private URLShortenerService(){}

    public static ShortenerURLMapping createShortURL(String longURL){
        return createShortURLWithRetry(longURL, SHORT_URL_LENGTH, MAX_RETRY);
    }

    private static ShortenerURLMapping createShortURLWithRetry(String longURL, int shortUrlLength, int attempts) {
        if (attempts <= 0){
            throw new InternalError("Could not generate after 3 attempts");
        }
        String shortURLCode = ShortCodeGeneratorService.generateShortCode(shortUrlLength);
        LOGGER.log(Level.FINE, ()-> String.format("Code generated: %s for url: %s", shortURLCode, longURL));
        try {
            ShortenerURLMapping shortenerURLMapping = new ShortenerURLMapping(shortURLCode, longURL);
            ShortURLRepositoryInMemory.getInstance().insert(shortenerURLMapping);
            return shortenerURLMapping;
        } catch (DuplicatedCodeException e) {
            LOGGER.log(Level.FINE, ()-> String.format("Generated code already exists, retrying for another %s times", (attempts - 1)));
            return createShortURLWithRetry(longURL, shortUrlLength+2, attempts -1);
        }
    }

    public static String findLongURL(String shortURLCode) {
        ShortenerURLMapping shortenerURLMapping = ShortURLRepositoryInMemory.getInstance().findByCode(shortURLCode);
        if(shortenerURLMapping != null
                && shortenerURLMapping.getOriginalUrl() != null
                && !shortenerURLMapping.getOriginalUrl().isEmpty()){
            return shortenerURLMapping.getOriginalUrl();
        }else{
            throw new IllegalArgumentException("Code Invalid");
        }
    }
}
