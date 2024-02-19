package com.studyjavaee.entity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ShortenerURLMapping {
    private final String code;
    private final String originalUrl;
    private ZonedDateTime creationDate;

    public ShortenerURLMapping(String code, String url){
        this.code = code;
        this.originalUrl = url;
        this.creationDate = ZonedDateTime.now(ZoneOffset.UTC);
    }

    public String getCode() {
        return code;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortenerURLMapping shortenerURLMapping = (ShortenerURLMapping) o;
        return Objects.equals(code, shortenerURLMapping.code) && Objects.equals(originalUrl, shortenerURLMapping.originalUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, originalUrl);
    }

    @Override
    public String toString() {
        return "ShortURL{" +
                "code='" + code + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                '}';
    }

    public ShortenerURLMapping copy() {
        ShortenerURLMapping shortenerURLMappingClone = new ShortenerURLMapping(this.code, this.originalUrl);
        shortenerURLMappingClone.creationDate = this.creationDate;
        return shortenerURLMappingClone;
    }
}
