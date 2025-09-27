package day4

import java.io.File

fun main() {
    val input = File("data/day4/input.txt").readLines()
    //val resultBreakfast = breakfast(input)
    //println(resultBreakfast)
    val resultLunch = lunch(input)
    println(resultLunch)
}

fun checkX(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[position] == "X"
}

fun checkNW(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x - 1, y - 1)] == "M" &&
            maze[Pair(x - 2, y - 2)] == "A" &&
            maze[Pair(x - 3, y - 3)] == "S"
}

fun checkN(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x, y - 1)] == "M" &&
            maze[Pair(x, y - 2)] == "A" &&
            maze[Pair(x, y - 3)] == "S"
}

fun checkNE(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x + 1, y - 1)] == "M" &&
            maze[Pair(x + 2, y - 2)] == "A" &&
            maze[Pair(x + 3, y - 3)] == "S"
}

fun checkW(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x + 1, y)] == "M" &&
            maze[Pair(x + 2, y)] == "A" &&
            maze[Pair(x + 3, y)] == "S"
}

fun checkE(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x - 1, y)] == "M" &&
            maze[Pair(x - 2, y)] == "A" &&
            maze[Pair(x - 3, y)] == "S"
}

fun checkSW(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x - 1, y + 1)] == "M" &&
            maze[Pair(x - 2, y + 2)] == "A" &&
            maze[Pair(x - 3, y + 3)] == "S"
}

fun checkS(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x, y + 1)] == "M" &&
            maze[Pair(x, y + 2)] == "A" &&
            maze[Pair(x, y + 3)] == "S"
}

fun checkSE(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x + 1, y + 1)] == "M" &&
            maze[Pair(x + 2, y + 2)] == "A" &&
            maze[Pair(x + 3, y + 3)] == "S"
}

fun checkA(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[position] == "A"
}

fun checkX1(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x - 1, y - 1)] == "M" &&
            maze[Pair(x - 1, y + 1)] == "M" &&
            maze[Pair(x + 1, y + 1)] == "S" &&
            maze[Pair(x + 1, y - 1)] == "S"
}

fun checkX2(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x - 1, y - 1)] == "S" &&
            maze[Pair(x - 1, y + 1)] == "M" &&
            maze[Pair(x + 1, y + 1)] == "M" &&
            maze[Pair(x + 1, y - 1)] == "S"
}

fun checkX3(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x - 1, y - 1)] == "S" &&
            maze[Pair(x - 1, y + 1)] == "S" &&
            maze[Pair(x + 1, y + 1)] == "M" &&
            maze[Pair(x + 1, y - 1)] == "M"
}

fun checkX4(maze: Map<Pair<Int, Int>, String>, position: Pair<Int, Int>) : Boolean {
    val x = position.first
    val y = position.second
    return maze[Pair(x - 1, y - 1)] == "M" &&
            maze[Pair(x - 1, y + 1)] == "S" &&
            maze[Pair(x + 1, y + 1)] == "S" &&
            maze[Pair(x + 1, y - 1)] == "M"
}

fun breakfast(input: List<String>) : Int {
    val height = input.size
    val width = input.first().length
    val maze: MutableMap<Pair<Int, Int>, String> = mutableMapOf()
    repeat(height) { y ->
        repeat(width) { x ->
            maze[Pair(x, y)] = input[y][x].toString()
        }
    }
    val m = maze.toMap()
    val result = maze.keys.sumOf {
        println("checking : $it")
        var count = 0
        if(checkX(m, it)) {
            if(checkNE(m, it)) count++
            if(checkE(m, it)) count++
            if(checkNW(m, it)) count++
            if(checkSE(m, it)) count++
            if(checkS(m, it)) count++
            if(checkSW(m, it)) count++
            if(checkE(m, it)) count++
            if(checkW(m, it)) count++
        }
        count
    }
    println(result)

    println("---")
    repeat(height) { y ->
        println()
        repeat(width) { x ->
            val it = Pair(x, y)
            val ok = checkX(m, it) &&
                    (checkNE(m, it) || checkNW(m, it) || checkN(m, it) ||
                            checkSE(m, it) || checkS(m, it) || checkSW(m, it) ||
                            checkE(m, it) || checkW(m, it))
            if(ok) print("X") else print('.')
        }
    }
    println("\n---")

    return -1
}

fun lunch(input: List<String>) : Int {

    val height = input.size
    val width = input.first().length
    val maze: MutableMap<Pair<Int, Int>, String> = mutableMapOf()
    repeat(height) { y ->
        repeat(width) { x ->
            maze[Pair(x, y)] = input[y][x].toString()
        }
    }
    val m = maze.toMap()
    val result = maze.keys.sumOf {
        println("checking : $it")
        var count = 0
        if(checkA(m, it)) {
            if(checkX1(m, it)) count++
            if(checkX2(m, it)) count++
            if(checkX3(m, it)) count++
            if(checkX4(m, it)) count++
        }
        count
    }
    println(result)

    return -1
}