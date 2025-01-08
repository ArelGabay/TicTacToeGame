package com.example.tictactoegame.viewmodel

import com.example.tictactoegame.model.Game

class GameViewModel {
    private val game = Game()

    private var onBoardUpdated: ((Array<Array<String>>) -> Unit)? = null // listener to be called when the board is updated
    private var onGameOver: ((String?) -> Unit)? = null // listener to be called when the game is over
    private var xScore = 0
    private var oScore = 0


    fun setOnBoardUpdatedListener(listener: (Array<Array<String>>) -> Unit) { // listener to be called when the board is updated
        onBoardUpdated = listener
    }

    fun setOnGameOverListener(listener: (String?) -> Unit) { // listener to be called when the game is over
        onGameOver = listener
    }

    fun makeMove(row: Int, col: Int) {
        if (game.makeMove(row,col)) {
            onBoardUpdated?.invoke(game.board) // notify the listener that the board has been updated
            if (game.isGameOver) {
                if (game.winner == "X") {
                    xScore++
                } else if (game.winner == "O") {
                    oScore++
                }
                onGameOver?.invoke(game.winner) // notify the listener that the game is over
            }
        }
    }

    fun resetGame() {
        game.resetGame()
        onBoardUpdated?.invoke(game.board)
        onGameOver?.invoke(null)
    }

    fun getCurrentPlayer(): String {
        return game.currentPlayer
    }

    fun getXScore(): Int {
        return xScore
    }

    fun getOScore(): Int {
        return oScore
    }
}