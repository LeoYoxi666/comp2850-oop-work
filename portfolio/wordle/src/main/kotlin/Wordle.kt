import java.io.File
import kotlin.math.min
fun isValid(word: String): Boolean {
    return word.length == 5 && word.all { it.isLetter() }
}

fun readWordList(filename: String): MutableList<String> =
    File(filename).readLines()
        .map { it.trim().lowercase() }
        .filter { isValid(it) }
        .toMutableList()

fun pickRandomWord(words: MutableList<String>): String {
    require(words.isNotEmpty()) { "word list is empty" }
    return words.removeAt(words.indices.random())
}

fun obtainGuess(attempt: Int, dictionary: Set<String>? = null): String {
    var guess: String
    while (true) {
        print("Attempt $attempt: ")
        guess = readln().trim().lowercase()

        if (isValid(guess)) {
            if (dictionary == null || dictionary.contains(guess)) {
                return guess
            } else {
                println("Word not found in dictionary.")
            }
        } else {
            println("Please enter a 5-letter word.")
        }
    }
}
fun evaluateGuess(guess: String, target: String): List<Int> {
    if (guess.length != 5 || target.length != 5) {
        throw IllegalArgumentException("Both guess and target must be 5 letters.")
    }

    val res = mutableListOf(0, 0, 0, 0, 0)
    val freq = IntArray(26)

    for (i in 0..4) {
        val g = guess[i]
        val t = target[i]
        if (g == t) {
            res[i] = 2
        } else {
            freq[t - 'a']++
        }
    }

    for (i in 0..4) {
        val g = guess[i]
        val idx = g - 'a'
        if (res[i] == 0 && idx in 0..25 && freq[idx] > 0) {
            res[i] = 1
            freq[idx]--
        }
    }

    return res
}


fun displayGuess(guess: String, matches: List<Int>) {
    require(matches.size == 5)
    for (i in 0 until 5) {
        when (matches[i]) {
            2 -> print(guess[i])
            1 -> print(guess[i].lowercase())
            else -> print('?')
        }
    }
    println()
}

