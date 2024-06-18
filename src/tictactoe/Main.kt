package tictactoe

fun main() {
    println("---------")
    println("|       |")
    println("|       |")
    println("|       |")
    println("---------")

    //val str = readln()

    val gameBoard = mutableListOf<MutableList<Char>>()
    /*
    gameBoard.add(mutableListOf(str[0], str[1], str[2]))
    gameBoard.add(mutableListOf(str[3], str[4], str[5]))
    gameBoard.add(mutableListOf(str[6], str[7], str[8]))
    */
    gameBoard.add(mutableListOf(' ', ' ', ' '))
    gameBoard.add(mutableListOf(' ', ' ', ' '))
    gameBoard.add(mutableListOf(' ', ' ', ' '))

    var first = true
    var letter: Char

    var result: String
    do {
        var arr: List<String>
        var validated: Boolean
        var xInt: Int = 0
        var yInt: Int = 0
        do {
            arr = readln().split(" ")
            val x = arr[0]
            val y = arr[1]

            if (!x.toCharArray()[0].isDigit() || !y.toCharArray()[0].isDigit()) {
                validated = false
                println("You should enter numbers!")
            } else if (x.toInt() < 1 || x.toInt() > 3 || y.toInt() < 1 || y.toInt() > 3) {
                validated = false
                println("Coordinates should be from 1 to 3!")
            } else if (gameBoard[x.toInt() - 1][y.toInt() - 1] != ' ') {
                validated = false
                println("This cell is occupied! Choose another one!")
            } else {
                validated = true
                xInt = x.toInt()
                yInt = y.toInt()
            }
        } while (!validated)

        letter = if (first) 'X' else 'O'
        gameBoard[xInt - 1][yInt - 1] = letter
        first = !first
        printBoard(gameBoard)
        result = analyzeGame(boardToString(gameBoard))
    } while (!result.contains("wins") && !result.contains("Draw"))


    println(result)
}

fun boardToString(list: MutableList<MutableList<Char>>): String {
    val sb = StringBuilder()
    for (row in list) {
        for (c in row) {
            sb.append(c)
        }
    }

    return sb.toString()
}

fun printBoard(board: MutableList<MutableList<Char>>) {
    println("---------")
    println("| ${board[0][0]} ${board[0][1]} ${board[0][2]} |")
    println("| ${board[1][0]} ${board[1][1]} ${board[1][2]} |")
    println("| ${board[2][0]} ${board[2][1]} ${board[2][2]} |")
    println("---------")
}

fun analyzeGame(str: String): String {
    val winList = mutableListOf<String>(
        "012",
        "345",
        "678",
        "036",
        "147",
        "258",
        "048",
        "246"
    )

    var xWon = false
    var yWon = false

    for (index in winList) {
        if (str[index[0].digitToInt()] == 'X' && str[index[1].digitToInt()] == 'X' && str[index[2].digitToInt()] == 'X') {
            xWon = true
        }
        if (str[index[0].digitToInt()] == 'O' && str[index[1].digitToInt()] == 'O' && str[index[2].digitToInt()] == 'O') {
            yWon = true
        }
    }

    if (xWon && yWon) {
        return "Impossible"
    } else if (xWon) {
        return "X wins"
    } else if (yWon) {
        return "O wins"
    }

    var emptyExists = false

    for (value in str) {
        if (value == ' ') {
            emptyExists = true
        }
    }

    if (!emptyExists) {
        return "Draw"
    }

    val xCount = str.count { a -> a == 'X' }
    val yCount = str.count { a -> a == 'O' }

    if (xCount - yCount >= 2 || yCount - xCount >= 2) {
        return "Impossible"
    }

    return "Game not finished"

}