package com.moiez.repository;

import com.moiez.model.Resource;

import java.util.List;

public interface ResourceRepository {
    boolean createResource(String resourceName);
    boolean removeResource(String resourceName);
    Resource viewResource(String resourceName);

    List<String> listResources();
}
