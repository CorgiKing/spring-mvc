package com.corgiking.service;

import java.util.ArrayList;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

public class CustomPropertiesConfig extends PropertyPlaceholderConfigurer {
  private static final String CONFIG_FILE_NAME = "demo_config_file";

  private CustomPropertiesConfig() {}

  public static CustomPropertiesConfig getInstances() {

    CustomPropertiesConfig config = new CustomPropertiesConfig();
    ArrayList<Resource> resources = new ArrayList<>();

    DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource1 = resourceLoader.getResource("classpath:env.properties");
    resources.add(resource1);
    Resource resource2 = resourceLoader.getResource("classpath:base.properties");
    resources.add(resource2);

    String config_file = System.getProperty(CONFIG_FILE_NAME);
    if (config_file != null) {
      try {
        Resource resource3 = new PathResource(config_file);
        resources.add(resource3);
      } catch (Exception e) {

      }
    }
    Resource[] rs = new Resource[resources.size()];
    resources.toArray(rs);
    config.setLocations(rs);
    return config;
  }
}
