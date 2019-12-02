fun main() {
    for (noun in 0..99) {
        for (verb in 0..99) {
            if (runGravityAssist(noun, verb)[0] == 19690720) {
                println("Noun: $noun, Verb: $verb")
                println("Answer... ${ 100 * noun + verb }")
                break
            }
        }
    }
}

fun runGravityAssist(noun: Int, verb: Int): Array<Int> {
    val memory = getMemory(noun, verb)
    for (address in 0 until memory.size step  4) {
        val instruction = memory[address]
        if (instruction == 99) {
            break
        }
        val parameterOne = memory[memory[address + 1]]
        val parameterTwo = memory[memory[address + 2]]
        val outputIndex = memory[address + 3]

        if (instruction == 1) {
            memory[outputIndex] = parameterOne + parameterTwo
        } else if (instruction == 2) {
            memory[outputIndex] = parameterOne * parameterTwo
        } else {
            println("Unknown OpCode $instruction")
            break
        }
    }
    return memory
}

fun getMemory(noun: Int, verb: Int): Array<Int> {
    return arrayOf(1,noun, verb,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,6,19,1,19,6,23,2,23,6,27,2,6,27,31,2,13,31,35,1,9,35,39,2,10,39,43,1,6,43,47,1,13,47,51,2,6,51,55,2,55,6,59,1,59,5,63,2,9,63,67,1,5,67,71,2,10,71,75,1,6,75,79,1,79,5,83,2,83,10,87,1,9,87,91,1,5,91,95,1,95,6,99,2,10,99,103,1,5,103,107,1,107,6,111,1,5,111,115,2,115,6,119,1,119,6,123,1,123,10,127,1,127,13,131,1,131,2,135,1,135,5,0,99,2,14,0,0)
}