# DesignPatterns_Costco

Overview

This project implements a comprehensive suite of design patterns to address key operational challenges within a simulated Costco environment. The project demonstrates practical applications of creational, structural, and behavioral design patterns across six distinct areas: Bakery, Food Court, Customer Support, Stock, Employee Management System, and Payment System. Each team member focused on specific modules, ensuring modular, scalable, and maintainable solutions.
Design Patterns Implemented

The project incorporates 18 design patterns in total:
1. Creational Patterns (3)

    Singleton: Ensures a single instance for centralized management in modules like stock inventory and payment processing.
    Builder: Simplifies complex object creation, such as multi-step food preparation workflows.
    Prototype: Supports cloning of reusable objects, such as predefined product templates in the Bakery.

2. Structural Patterns (6)

    Composite: Used for hierarchical menu structures in the Food Court.
    Adapter: Integrates legacy systems for employee data with modern payroll modules.
    Facade: Provides a unified interface for accessing daily specials from multiple departments.
    Decorator: Enables dynamic addition of features to bakery items, such as custom toppings.
    Proxy: Controls access to limited network resources in customer service areas.
    Flyweight: Reduces memory usage by reusing shared data for frequently accessed stock items.

3. Behavioral Patterns (9)

    Observer: Updates customers on order statuses in real time.
    Command: Encapsulates customer requests, like order modifications, as command objects.
    Strategy: Provides interchangeable algorithms for calculating employee bonuses.
    State: Manages order lifecycle stages (e.g., preparing, cooking, serving).
    Mediator: Coordinates communication between different departments for resource sharing.
    Visitor: Performs diagnostics on interconnected systems (e.g., inventory scanners).
    Template: Standardizes workflows, such as soda dispensing in the Food Court.
    Iterator: Traverses employee and stock collections without exposing internal structures.
    Interpreter: Parses and processes text-based order inputs from customers.

Key Features by Module
Bakery (Travis)

    Implements Builder for custom cake orders.
    Uses Prototype for predefined product templates.
    Incorporates Decorator for dynamic customization.

Food Court (Travis)

    Utilizes Composite for menu hierarchy.
    Applies Template for standardized soda workflows.

Customer Support (Sebastian)

    Leverages Proxy for network access control.
    Integrates Command for handling customer requests.

Stock (Sebastian)

    Employs Singleton for centralized inventory management.
    Implements Flyweight for optimized stock item representation.

Employee Management System (Rebecca)

    Adopts Adapter for legacy data integration.
    Uses Strategy for flexible bonus calculations.

Payment System (Rebecca)

    Implements State to track payment stages.
    Uses Observer for real-time transaction updates.
