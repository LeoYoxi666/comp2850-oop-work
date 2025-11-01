import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class WordleTest : StringSpec({
    // Write your tests here
    "first Test isValid works correctly" {
        isValid("apple") shouldBe true
        isValid("pear") shouldBe false
        isValid("app!e") shouldBe false
        isValid("APPlE") shouldBe true
    }
    "evaluateGuess full match" {
        evaluateGuess("apple", "apple") shouldBe listOf(2, 2, 2, 2, 2)
    }
    "evaluateGuess all wrong" {
        evaluateGuess("zzzzz", "apple") shouldBe listOf(0, 0, 0, 0, 0)
    }

    "evaluateGuess partial & repeated letters" {
        val result1 = evaluateGuess("plpea", "apple")
        result1 shouldBe listOf(1, 1, 2, 1, 1)

        val result2 = evaluateGuess("ppppp", "apple")

        var nonZeroCount = 0
        for (value in result2) {
            if (value > 0) nonZeroCount++
        }

        (nonZeroCount <= 2) shouldBe true
    }
    "pickRandomWord removes from list" {
        val words = mutableListOf("apple", "grape", "mango")
        val chosen = pickRandomWord(words)

        val original = listOf("apple", "grape", "mango")
        var foundInOriginal = false
        for (w in original) {
            if (w == chosen) foundInOriginal = true
        }
        foundInOriginal shouldBe true

        var stillInList = false
        for (w in words) {
            if (w == chosen) stillInList = true
        }
        stillInList shouldBe false
    }

    "pickRandomWord on empty list" {
        val words = mutableListOf<String>()
        var exceptionThrown = false
        try {
            pickRandomWord(words)
        } catch (e: Exception) {
            exceptionThrown = true
        }
        exceptionThrown shouldBe true
    }
})
