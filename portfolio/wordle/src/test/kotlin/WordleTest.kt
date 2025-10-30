import io.kotest.assertions.withClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class WordleTest : StringSpec({
    // Write your tests here
    "isValid works correctly" {
    isValid("apple") shouldBe true
    isValid("pear") shouldBe false
    isValid("app!e") shouldBe false
    isValid("APPlE") shouldBe true
}
    "evaluateGuess full match" {
        evaluateGuess("apple", "apple") shouldBe listOf(2, 2, 2, 2, 2)
    }
})
