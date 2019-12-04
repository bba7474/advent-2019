fun main() {
    val potentialPasswordsCount = IntRange(153517, 630395)
        .map { p -> toOverlappingDigits(p) }
        .filter { p -> hasMatchingPair(p) }
        .filter { p -> isIncreasing(p) }
        .count()

    println("There are... $potentialPasswordsCount potential passwords")
}

fun hasMatchingPair(pairsOfDigits: List<Pair<Int, Int>>): Boolean {
    return pairsOfDigits.any { pair -> pair.first == pair.second }
}

fun isIncreasing(pairsOfDigits: List<Pair<Int, Int>>): Boolean {
    return pairsOfDigits.all { pair -> pair.first <= pair.second }
}

fun toOverlappingDigits(input: Int): List<Pair<Int, Int>> {
    return getDigits(input).zipWithNext()
}

fun getDigits(input: Int): List<Int> {
    val digits = mutableListOf<Int>()

    var workingNumber = input
    while (workingNumber > 0) {
        digits.add(workingNumber % 10)
        workingNumber /= 10
    }

    digits.reverse()

    return digits
}