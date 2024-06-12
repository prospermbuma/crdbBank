package com.celebrate.crdb_bank.Models;

import com.celebrate.crdb_bank.Views.ViewFactory;

/*===========================================
# Singleton design pattern implementation.

The Singleton design pattern is a creational pattern that ensures a class has only one instance,
and provides a global access point to it.

Purpose:
Controls object creation and guarantees a single instance.
Provides a global access point to that single instance.
Useful for scenarios like:
Logger classes
Configuration managers
Driver objects for resources (e.g., database connection pool)

Benefits:
Ensures a single instance throughout the application.
Provides a centralized access point for interacting with the object.

Drawbacks:
Can make testing more difficult (consider dependency injection for better testability).
Overuse can lead to tight coupling and reduced flexibility.

Alternatives:
Factory Pattern: Offers more flexibility in creating different object types.
Dependency Injection: Promotes loose coupling and easier testing.

When to Use:
Consider using Singleton when you need a single, globally accessible object for resource management, logging, or configuration.
However, evaluate if alternatives like dependency injection might be more suitable for your specific use case.
============================================*/
public class Model {
    // Static Instance: A private static member variable of the class is used to hold the single instance.
    private static Model model;
    private final ViewFactory viewFactory;

    // Constructor
    // Private Constructor: The constructor is declared private to prevent external object creation
    private Model() {
        this.viewFactory = new ViewFactory();
    }

    // Getter Method - Get Model Instance
    // Static Factory Method: A public static method provides the global access point to get the instance
    /*
    *  1. Checks if the instance already exists (using lazy initialization if needed).
    *  2. If not, creates the instance using the private constructor.
    *  3. Returns the instance.
    * */
    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    // Getter Method - Get View Factory
    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
