fun main() {
    val words = readWordList("data/words.txt")
    val dict = words.toSet()

    println("Welcome to Wordle! You have 6 attempts.")

    val target = words.random()
    var success = false

    for (attempt in 0 until 6) { // 0,1,2,3,4,5
        val guess = obtainGuess(attempt + 1, dict)
        val matches = evaluateGuess(guess, target)
        displayGuess(guess, matches)

        if (guess == target) {
            println("Congratulations! You found the word in ${attempt + 1} ${if (attempt == 0) "try" else "tries"}.")
            success = true
            break
        }
    }

    if (!success) {
        println("Sorry! The word was \"$target\".")
    }

    println("Bye!")
}
