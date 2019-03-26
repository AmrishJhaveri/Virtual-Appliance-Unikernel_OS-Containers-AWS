package com.uic.chess.cs441.chessrestapi.controllers;

import com.uic.chess.cs441.chessrestapi.interfaces.IChessEngine;
import com.uic.chess.cs441.chessrestapi.models.MoveObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chess")
public class ChessController {

    private final IChessEngine engine;

    @Autowired
    public ChessController(IChessEngine engine) {
        this.engine = engine;
    }

    @PostMapping("/newGame")
    public ResponseEntity<String> newGame(/*@PathVariable String bookTitle*/) {
        return new ResponseEntity<>(engine.newGame(), HttpStatus.OK);
    }

    @PostMapping("/move")
    public ResponseEntity<MoveObject> move(@RequestBody MoveObject moveObj) {
        return new ResponseEntity<>(
                engine.move(moveObj.getStart(), moveObj.getEnd(), moveObj.getSession()),
                HttpStatus.OK);
    }

}
