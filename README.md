# Spring MVC Showcase - Spring Boot Edition

This repository contains a Spring Boot version of the original [Spring MVC Showcase](https://github.com/spring-attic/spring-mvc-showcase), automatically migrated using the Spring Migrator tool.

## Migration Demo

![Spring Migrator Demo](./demo.gif)

## About Spring Migrator

Spring Migrator is an intelligent CLI tool that automates the migration of legacy Spring Framework applications to modern Spring Boot applications. Powered by Anthropics Claude 3.7, it uses a LangGraph-based workflow to analyze, plan, and transform Spring projects.

## Comprehensive Feature Set

### Core Architecture
- **LLM Factory**: Creates LLM interfaces with caching for consistent and efficient responses with multiple layers of caching
- **Migration Manager**: Orchestrates workflow using LangGraph for complex migration workflows
- **State Management**: Manages workflow state transitions and data flow between components
- **Task Registry**: Dynamically registers and manages migration tasks

### Project Analysis
- **Source Code Analysis**: Parses and analyzes Java code structure using AST (Abstract Syntax Tree)
- **Dependency Analysis**: Identifies and maps project dependencies
- **Configuration Scanning**: Detects Spring XML configurations, annotations, and property files
- **Resource Discovery**: Locates static resources, templates, and web assets

### Migration Planning
- **Task Prioritization**: Creates a logical sequence of migration steps
- **Dependency Mapping**: Maps Spring dependencies to Spring Boot equivalents
- **Configuration Strategy**: Determines optimal configuration approach (Java vs properties)
- **Risk Assessment**: Identifies potential migration challenges

### Task Execution
- **Controllers**: Migrates MVC controllers with proper annotations and request mappings
- **Services & Repositories**: Transforms service and repository layers
- **Aspect-Oriented Programming**: Converts AOP configurations and components
- **Configuration Conversion**: Transforms XML to Java configuration and application.properties
- **Security Configuration**: Updates Spring Security setup to Boot-compatible approach
- **Main Application**: Creates Spring Boot application class with appropriate annotations
- **MVC Configuration**: Converts WebMVC configuration to modern formats
- **Transactions**: Updates transaction management
- **View Templates**: Adjusts view resolvers and template configurations
- **Resource Locations**: Updates resource location paths to Boot conventions
- **Custom Components**: Handles migration of custom components and formatters
- **Jakarta EE Migration**: Updates to Jakarta EE from Java EE dependencies

### Testing Framework
- **Test Creation**: Generates appropriate tests for migrated components
- **Context Configuration**: Updates test context configuration
- **Test Dependencies**: Manages test framework dependencies (JUnit, TestNG)
- **Test Discovery**: Identifies existing tests and migration requirements
- **Validation Tests**: Creates tests to verify migration correctness
- **Testing Annotations**: Updates testing annotations for Spring Boot

### Validation
- **Syntax Validation**: Verifies Java code syntax correctness
- **Structure Validation**: Ensures proper Spring Boot project structure
- **Configuration Validation**: Validates configuration consistency
- **Runtime Validation**: Checks for runtime compatibility issues
- **Test Validation**: Verifies test functionality post-migration
- **Autoconfiguration Validation**: Ensures Spring Boot autoconfiguration works correctly
- **Issue Remediation**: Automatically fixes common validation issues

### Resources Management
- **Static Resources**: Updates static resource handling
- **XML Resource Conversion**: Transforms XML configurations
- **Properties Management**: Updates and consolidates properties files
- **Java Resources**: Handles Java resource files and classpath resources

### Tools & Utilities
- **File System Operations**: Provides safe file system access and manipulation
- **Shell Command Execution**: Runs necessary shell commands during migration
- **AST Analysis**: Deep code structure analysis using Java AST
- **Code Extraction**: Extracts and transforms specific code elements
- **Class Migration**: Handles complex class migration scenarios
- **Dependency Analysis**: Analyzes and updates dependency graphs

### User Interaction
- **Interactive Feedback**: Solicits user input for critical decisions
- **Progress Reporting**: Provides detailed progress updates
- **Error Handling**: Clear error reporting with suggested fixes
- **Migration Summaries**: Generates comprehensive migration reports

### CLI Interface
- **Command Structure**: Intuitive command structure for migration operations
- **Argument Parsing**: Flexible argument handling for different migration scenarios
- **Output Formatting**: Clear, readable output formatting

The Spring Migrator tool provides a streamlined approach to modernizing legacy Spring applications, making the transition to Spring Boot simpler and more efficient through this comprehensive set of features.
