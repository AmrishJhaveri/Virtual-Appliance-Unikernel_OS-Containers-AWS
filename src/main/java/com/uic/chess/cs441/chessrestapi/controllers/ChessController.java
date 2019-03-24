package com.uic.chess.cs441.chessrestapi.controllers;

import com.uic.chess.cs441.chessrestapi.interfaces.IChessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chess")
public class ChessController {

    private final IChessEngine engine;

    @Autowired
    public ChessController(IChessEngine engine) {
        this.engine = engine;
    }

    @GetMapping("/newGame")
    public List newGame(/*@PathVariable String bookTitle*/) {
        return engine.newGame();
    }

}
