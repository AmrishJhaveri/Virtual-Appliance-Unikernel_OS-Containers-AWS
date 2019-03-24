package com.uic.chess.cs441.chessrestapi.services;

import com.uic.chess.cs441.chessrestapi.interfaces.IChessEngine;
import org.springframework.stereotype.Service;
import pl.art.lach.mateusz.javaopenchess.core.Game;

import java.util.Arrays;
import java.util.List;

@Service
public class ChessEngineImpl implements IChessEngine {

    private Game game;

    @Override
    public List newGame() {
        System.out.println("In ChessEngineImpl");
        game=new Game("amrish","white");
        return Arrays.asList("Hello", "World");
    }
}
