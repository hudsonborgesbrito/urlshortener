package com.studyjavaee.repository;

import com.studyjavaee.entity.ShortenerURLMapping;
import com.studyjavaee.exception.DuplicatedCodeException;

import java.util.concurrent.ConcurrentHashMap;

public class ShortURLRepositoryInMemory implements ShortURLRepository {
    private static ShortURLRepository shortURLRepository;
    private static final ConcurrentHashMap<String, ShortenerURLMapping> dataTable = new ConcurrentHashMap<>();
    private ShortURLRepositoryInMemory(){}

    public static ShortURLRepository getInstance(){
        if(shortURLRepository == null){
            shortURLRepository = new ShortURLRepositoryInMemory();
        }
        return shortURLRepository;
    }

    @Override
    public ShortenerURLMapping findByCode(String code) {
        ShortenerURLMapping shortenerURLMapping = dataTable.get(code);
        return shortenerURLMapping == null ? null : shortenerURLMapping.copy();
    }

    @Override
    public void insert(ShortenerURLMapping shortenerURLMapping) throws DuplicatedCodeException {
        Object existentShortURLValue = dataTable.putIfAbsent(shortenerURLMapping.getCode(), shortenerURLMapping);
        if(existentShortURLValue != null){
            throw new DuplicatedCodeException("Code: "+ shortenerURLMapping.getCode()+" already exists in the database and is associated with the URL: "+ shortenerURLMapping.getOriginalUrl());
        }
    }
}
