package main;

import controller.Controller;
import controller.Parser;
import view.View;

import java.io.IOException;

/**
 * Main class to run a program
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        View view = new View();
        Controller controller = new Controller(parser, view);
        controller.process();
    }
}
