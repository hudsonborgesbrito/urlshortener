package com.studyjavaee.repository;

import com.studyjavaee.entity.ShortenerURLMapping;
import com.studyjavaee.exception.DuplicatedCodeException;

public interface ShortURLRepository {
    public ShortenerURLMapping findByCode(String code);
    public void insert(ShortenerURLMapping shortenerURLMapping) throws DuplicatedCodeException;
}
