package com.moiez.service;

import com.moiez.model.Resource;
import com.moiez.repository.ResourceRepositoryInMemImpl;
import com.moiez.repository.ResourceRepository;

import java.util.List;
import java.util.Scanner;

public class ResourceService {

    Scanner scanner = new Scanner(System.in);

    ResourceRepository resourceRepository = new ResourceRepositoryInMemImpl();

    public boolean createResource(String resourceName) {
        return resourceRepository.createResource(resourceName);
    }

    public boolean deleteResource(String resourceName) {
        return resourceRepository.removeResource(resourceName);
    }

    public Resource getResource(String resourceName) {
        return resourceRepository.viewResource(resourceName);
    }

    public void readResource(Resource resource) {
        System.out.println(resource.getContent());
    }

    public void writeResource(Resource resource) {
        System.out.println("Over-write existing content of the resource ? (y/n): ");
        boolean overWrite = scanner.next().equalsIgnoreCase("y");
        System.out.println("Enter content: ");
        String newContent = scanner.nextLine();
        resource.setContent(newContent, overWrite);
    }

    public List<String> listResources() {
        return resourceRepository.listResources();
    }
}
