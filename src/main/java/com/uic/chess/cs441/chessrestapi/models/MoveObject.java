package com.uic.chess.cs441.chessrestapi.models;

public class MoveObject {

    private String start;
    private String end;
    private String session;
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MoveObject() {

    }

    public MoveObject(String start, String end, String session, String message) {
        this.start = start;
        this.end = end;
        this.session = session;
        this.message = message;
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


}
