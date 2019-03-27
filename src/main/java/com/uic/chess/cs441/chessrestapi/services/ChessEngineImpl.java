package com.uic.chess.cs441.chessrestapi.services;

import com.uic.chess.cs441.chessrestapi.enums.GameStatus;
import com.uic.chess.cs441.chessrestapi.interfaces.IChessEngine;
import com.uic.chess.cs441.chessrestapi.models.MoveObject;
import com.uic.chess.cs441.chessrestapi.session.SessionMapSingleton;
import com.uic.chess.cs441.chessrestapi.utils.ChessUtility;
import org.springframework.stereotype.Service;
import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.core.moves.Move;

import java.util.Optional;

@Service
public class ChessEngineImpl implements IChessEngine {

    // TODO New Game instance for every call from the client.
//    private Game game;

    @Override
    public String newGame() {
        System.out.println("In ChessEngineImpl");
        Game game = new Game("amrish", "white");
        game.newGame();
        return SessionMapSingleton.getInstance().addGameToSession(game);
    }

    @Override
    public MoveObject move(String start, String end, String session) {
        // Convert start and end to squares on the chessboard.

        Optional<Move> move = SessionMapSingleton.getInstance()
                .getGameBySession(session) //TODO if there is no game against the session
                .map(game -> {
                            int[] startIndexes = ChessUtility.getIndexesFromString(start);
                            int[] endIndexes = ChessUtility.getIndexesFromString(end);
                            return game.moveActionInvoked(startIndexes, endIndexes);
                        }
                );

        return SessionMapSingleton.getInstance()
                .getGameBySession(session)
                .filter(Game::isIsEndOfGame)
                .map(game ->
                        MoveObject.getFinishedGameObject(session))
                .orElse(move.map(move1 ->
                        new MoveObject(ChessUtility.getStringFromIndex(move1.getFrom()),
                                ChessUtility.getStringFromIndex(move1.getTo()),
                                session, "", GameStatus.ONGOING))
                        .orElse(MoveObject.getInvalidMoveObject(session)));
    }

    @Override
    public MoveObject quit(String session) {
        return SessionMapSingleton.getInstance().removeGameFromSession(session) ?
                MoveObject.getEndGameObject(session) : MoveObject.getInvalidMoveObject(session);
    }
}
