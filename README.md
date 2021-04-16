# diceSimulator
Spring Boot REST Endpoint exercise

> DiceSimulatorController was created to handle GET and POST requests.
> DiceSimulator was created in order to perform the dice simulation after receiving POST request.
> DiceSimulatorResults was created in order to present the whole simulation result. Each simulation result was represented by DiceCombination both were called after controller received GET request.
> DiceSimulatorRepository was created for data handling.

Usage:
[POST] http://localhost:8080/api/rollDice - performs a single simulation. can add query parameters [ numSides(min 4, default 6), numDice(min 1, default 3), numRolls(min 1, default 100) ]
[GET] http://localhost:8080/api/getRolls - retrieve all simulations made during application startup.
