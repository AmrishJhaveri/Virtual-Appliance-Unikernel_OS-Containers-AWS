package com.uic.chess.cs441.chessrestapi.session;

import pl.art.lach.mateusz.javaopenchess.core.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SessionMapSingleton {

    private static SessionMapSingleton singleton;
    private Map<String, Game> sessionMap;

    private SessionMapSingleton() {
        sessionMap = new HashMap<>();
    }

    public Optional<Game> getGameBySession(String sessionId) {
        return Optional.ofNullable(sessionMap.getOrDefault(sessionId, null));
    }

    public String addGameToSession(Game game) {
        String uuid = generateUuid();
        while (sessionMap.containsKey(uuid.toString())) {
            uuid = generateUuid();
        }
        sessionMap.put(uuid, game);
        return uuid;
    }

    public boolean removeGameFromSession(String sessionId) {
        return sessionMap.remove(sessionId) != null;
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

//    @Bean
//    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//    public SessionMapSingleton sessionMapSingleton(){
//        return new SessionMapSingleton();
//    }

    public static SessionMapSingleton getInstance() {
        if (singleton == null) {
            singleton = new SessionMapSingleton();
        }
        return singleton;
    }
}
