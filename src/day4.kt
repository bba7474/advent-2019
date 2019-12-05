fun main() {
    val potentialPasswordsCount = IntRange(153517, 630395)
        .asSequence()
        .map { p -> toOverlappingDigits(p) }
        .filter { p -> hasMatchingPair(p) }
        .filter { p -> isIncreasing(p) }
        .filter { p -> matchingPairsNotPartOfLargerMatchingGroup(p) }
        .count()

    println("There are... $potentialPasswordsCount potential passwords")
}

fun hasMatchingPair(pairsOfDigits: List<Pair<Int, Int>>): Boolean {
    return pairsOfDigits.any { pair -> pair.first == pair.second }
}

fun isIncreasing(pairsOfDigits: List<Pair<Int, Int>>): Boolean {
    return pairsOfDigits.all { pair -> pair.first <= pair.second }
}

fun matchingPairsNotPartOfLargerMatchingGroup(pairsOfDigits: List<Pair<Int, Int>>): Boolean {
    for ((index, value) in pairsOfDigits.withIndex()) {
        if (value.first == value.second) {
            val previousMatch = if (index == 0) false else pairIsMatchingPairOfInt(pairsOfDigits.get(index - 1), value.first)
            val nextMatch = if (index == pairsOfDigits.size - 1) false else pairIsMatchingPairOfInt(pairsOfDigits.get(index + 1), value.first)

            if (!previousMatch && !nextMatch) {
                return true
            }
        }
    }

    return false
}

fun pairIsMatchingPairOfInt(pair: Pair<Int, Int>, intToMatch: Int): Boolean {
    return pair.first == intToMatch && pair.first == pair.second
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