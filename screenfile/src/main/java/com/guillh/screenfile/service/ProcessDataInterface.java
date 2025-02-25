package com.guillh.screenfile.service;

public interface ProcessDataInterface {
    <T> T getData(String json, Class<T> classType);
}
