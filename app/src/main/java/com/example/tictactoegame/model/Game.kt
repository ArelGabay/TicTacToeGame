package com.example.tictactoegame.model

data class Game(
    val board: Array<Array<String>> = Array(3) { Array(3) { "" } },
    var currentPlayer: String = "X",
    var winner: String? = null
    var isGameOver: Boolean = false
) {
    fun makeMove(row: Int, col: Int): Boolean { // return true if the move is valid
        if (board[row][col].isEmpty() && !isGameOver) {
            board[row][col] = currentPlayer
            checkWinner()
            if (!isGameOver) {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
            return true
        }
        return false
    }

    private fun checkWinner() { // check if the current player has won the game
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                winner = currentPlayer
                isGameOver = true
                return
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                winner = currentPlayer
                isGameOver = true
                return
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer ||
            board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer
        ) {
            winner = currentPlayer
            isGameOver = true
            return
        }

        if (board.all { row -> row.all { cell -> cell.isNotEmpty() } }) {
            isGameOver = true
        }
    }

    fun resetGame() { // reset the game
        for (row in board) row.fill("")
        currentPlayer = "X"
        winner = null
        isGameOver = false
    }

}