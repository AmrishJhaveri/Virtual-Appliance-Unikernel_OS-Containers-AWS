package com.uic.chess.cs441.chessrestapi.interfaces;

import java.util.List;

public interface IChessEngine {

    //    Response newGame(boolean firstMove) throws Exception;
//    Response move(Square start, Square end, Session session) throws Exception;
//    Response quit(Session session) throws Exception;
    List newGame();
}
