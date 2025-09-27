package day15

import day14.breakfast
import java.io.File

fun main() {
    val input = File("data/day15/sample.txt").readText()
    val resultBreakfast = breakfast(input)
    //println(resultBreakfast)
    //val resultLunch = lunch(input)
    //println(resultLunch)
}

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
}

fun canMove(robot: Pair<Int, Int>, direction: Direction, borders: List<Pair<Int, Int>>, boxes: List<Pair<Int, Int>>): Boolean {
    return when(direction) {
        Direction.NORTH -> true
        Direction.EAST -> true
        Direction.SOUTH -> true
        Direction.WEST -> {
            val bordersWest = borders.filter { it.second == robot.second && it.first < robot.first }
            val borderWest = bordersWest.last()
            println(borderWest)
            val boxesWest = boxes.filter { it.second == robot.second && it.first < robot.first && it.first > borderWest.first}.reversed()
            println(boxesWest)
            val diff = robot.first - borderWest.first - 1
            println(diff)
            return diff > boxesWest.size
        }
    }
}

fun breakfast(input: String) {
    val (warehouseInput, commands) = input.split("\n\n")
    println(warehouseInput)
    println(commands)
    val warehouseMap = warehouseInput.split("\n").flatMapIndexed { y, s ->
        s.mapIndexed { x, c ->
            Pair(x, y) to c
        }
    }.toMap()
    println(warehouseMap)
    val borders = warehouseMap.filter { it.value == '#' }.map { it.key }
    println(borders)
    var robot = warehouseMap.filter { it.value == '@' }.map { it.key }.first()
    println(robot)
    val boxes = warehouseMap.filter { it.value == 'O' }.map { it.key }.toMutableList()
    println(boxes)
    val movable = canMove(robot, Direction.WEST, borders, boxes)
    println(movable)
}


