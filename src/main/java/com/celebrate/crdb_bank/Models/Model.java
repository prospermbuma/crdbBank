package com.celebrate.crdb_bank.Models;

import com.celebrate.crdb_bank.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    // Single tone design pattern implementation
    // Constructor
    private Model() {
        this.viewFactory = new ViewFactory();
    }

    // Getter Method - Get Model Instance
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
