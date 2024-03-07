<!DOCTYPE html>
<html>

<head>
    <title>Guess the Number</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
    <script src="static/js/game.js"></script>
</head>

<body>

    <h1>Guess the Number</h1>
    <form action="GuessTheNumberServlet" method="GET">
        <label for="guess">Enter your guess:</label>
        <input type="number" name="guess" required>
        <input type="submit" value="Guess">
    </form>
    <p>${message}</p>

        <footer>
            <span><marquee>The player is prompted to guess a number between 1 and 100. The JavaScript code generates a random number between
        			1 and 100, and then compares the player's guess with the random number. The player is given feedback on whether
        			their guess is too low or too high, and the number of attempts is displayed. If the player guesses the correct
        			number, a congratulatory message is displayed and the input and button are disabled.</marquee></span>
        </footer>
        <br/>
        <br/>
        
        <center>
            <b>************************************<div class="blinking-text"> Fun Coding Challenge</div>************************************</b>
        </center>
        <ul>
            <li>Add a feature to limit the number of attempts (e.g., 10 attempts allowed).</li>
            <li>Add a timer to see how long it takes the user to guess the number.</li>
            <li>Implement difficulty levels (easy, medium, hard) with different number ranges.</li>
            <li>Keep a record of high scores or best times.</li>
        </ul>
        <pre>Note: You can use AI Assistant Extension within the ZohoCode IDE and Complete the Challenge. Happy Coding... </pre>
</body>
</html>