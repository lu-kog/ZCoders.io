package com.yourcompany.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Random;

public class GuessTheNumberServlet extends HttpServlet {
    private int secretNumber;
    private int attempts;
    private final int maxAttempts = 10;

    public void init() throws ServletException {
        // Initialize the game when the servlet is started
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        attempts = 0;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests, typically used to display the game UI
        String guess = request.getParameter("guess");
        String message = "";


        if (guess != null) {
            // Handle the user's guess
            int userGuess = Integer.parseInt(guess);
            attempts++;

            if (userGuess == secretNumber) {
                message = "Congratulations! You guessed the number in " + attempts + " attempts.";
                init(); // Restart the game
            } else if (attempts >= maxAttempts) {
                message = "Sorry, you've run out of attempts. The correct number was " + secretNumber + ".";
                init(); // Restart the game
            } else if (userGuess < secretNumber) {
                message = "Try a higher number.";
            } else {
                message = "Try a lower number.";
            }
        }

        // Pass data to JSP for rendering
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/guess-the-number.jsp");
        dispatcher.forward(request, response);
    }
}
