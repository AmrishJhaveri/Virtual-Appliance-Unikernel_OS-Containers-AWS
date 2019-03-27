package com.uic.chess.cs441.chessrestapi.interfaces;

import com.uic.chess.cs441.chessrestapi.models.MoveObject;
import pl.art.lach.mateusz.javaopenchess.core.Square;

import java.util.List;

public interface IChessEngine {

    String newGame();

    MoveObject move(String start, String end, String session);

    MoveObject quit(String session);
}
