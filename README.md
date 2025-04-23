# Spring MVC Showcase - Spring Boot Edition

This repository contains a Spring Boot version of the original [Spring MVC Showcase](https://github.com/spring-attic/spring-mvc-showcase), automatically migrated using the Spring Migrator tool.

## Migration Demo

![Spring Migrator Demo](./demo.gif)

## About Spring Migrator

Spring Migrator is an intelligent CLI tool that automates the migration of legacy Spring Framework applications to modern Spring Boot applications. Powered by Claude 3.7, it uses a LangGraph-based workflow to analyze, plan, and transform Spring projects.

### How It Works

1. **Project Analysis**: Scans the Spring MVC project structure, identifying controllers, configuration files, and dependencies
2. **Migration Planning**: Creates a step-by-step migration strategy tailored to the specific project
3. **Task Execution**: Transforms Spring MVC code to Spring Boot patterns including:
   - Converting XML configurations to Java-based or application.properties
   - Creating a Spring Boot application class
   - Migrating controllers, services, and repositories
   - Updating resource locations and configurations
4. **Validation**: Verifies the migrated code, fixes issues, and ensures correct operation

### Key Benefits

- **Automated Migration**: Reduces manual effort to modernize Spring applications
- **Intelligent Transformation**: Understands Spring Framework patterns and creates appropriate Spring Boot equivalents
- **Interactive Workflow**: Provides feedback and allows user input throughout the migration process
- **Comprehensive Approach**: Handles everything from dependencies to configuration to component migration

The Spring Migrator tool provides a streamlined approach to modernizing legacy Spring applications, making the transition to Spring Boot simpler and more efficient.
