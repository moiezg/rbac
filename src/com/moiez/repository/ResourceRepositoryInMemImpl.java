package com.moiez.repository;

import com.moiez.model.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceRepositoryInMemImpl implements ResourceRepository {

    Map<String, Resource> resources = new HashMap<>();

    @Override
    public boolean createResource(String resourceName) {

        if (resources.containsKey(resourceName))
            return false;

        Resource resource = new Resource(resourceName);
        resources.put(resourceName, resource);
        return true;
    }

    @Override
    public boolean removeResource(String resourceName) {
        if (!resources.containsKey(resourceName))
            return false;

        resources.remove(resourceName);
        return true;
    }

    @Override
    public Resource viewResource(String resourceName) {
        return resources.get(resourceName);
    }

    @Override
    public List<String> listResources() {
        return new ArrayList<>(resources.keySet());
    }
}
