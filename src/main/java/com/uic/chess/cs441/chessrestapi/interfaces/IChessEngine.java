package com.uic.chess.cs441.chessrestapi.interfaces;

import com.uic.chess.cs441.chessrestapi.models.MoveObject;
import pl.art.lach.mateusz.javaopenchess.core.Square;

import java.util.List;

public interface IChessEngine {

    //    Response newGame(boolean firstMove) throws Exception;
//    Response move(Square start, Square end, Session session) throws Exception;
//    Response quit(Session session) throws Exception;
    String newGame();

    MoveObject move(String start, String end, String session);
}
