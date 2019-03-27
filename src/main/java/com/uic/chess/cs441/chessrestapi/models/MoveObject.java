package com.uic.chess.cs441.chessrestapi.models;

import com.uic.chess.cs441.chessrestapi.enums.GameStatus;

import static com.uic.chess.cs441.chessrestapi.enums.GameStatus.ONGOING;

public class MoveObject {

    private String start;
    private String end;
    private String session;
    private String message;
    private GameStatus status;

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MoveObject() {

    }

    public MoveObject(String start, String end, String session, String message,GameStatus status) {
        this.start = start;
        this.end = end;
        this.session = session;
        this.message = message;
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public static MoveObject getEndGameObject(String sessionId){
       return new MoveObject("","",sessionId,"The game exited successfully.",GameStatus.QUIT);
    }

    public static MoveObject getFinishedGameObject(String sessionId){
        return new MoveObject("", "", sessionId, "End of Game", GameStatus.FINISHED);
    }

    public static MoveObject getInvalidMoveObject(String session){
        return new MoveObject("", "", session, "Invalid Move", GameStatus.ONGOING);
    }
}
