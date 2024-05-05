package com.prikolz.mcprotocol.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerStatusConstructor {

    private String jsonResult;

    private String versionName;
    private int protocol;
    private int maxPlayers;
    private int players;
    private boolean enforcesSecureChat;
    private boolean previewsChat;
    private String jsonDescription;
    private final List<String> playersSamples;
    private String base64Icon;

    public ServerStatusConstructor(String versionName, int protocol, int maxPlayers, int players, boolean enforcesSecureChat, boolean previewsChat) {
        this.versionName = versionName;
        this.protocol = protocol;
        this.maxPlayers = maxPlayers;
        this.players = players;
        this.enforcesSecureChat = enforcesSecureChat;
        this.previewsChat = previewsChat;
        this.playersSamples = new ArrayList<>();
        this.jsonDescription = "";
        this.base64Icon = "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAABD0lEQVR4nO2Zyw7DIAwEoer//zI91hckuxAm6e5cm8dqtFag9NbaaMK86AA0FkAHoLEAOgCNvID37Icxvl/H3nvpoZl74zWR6rtWkW+AvIDpCERWxqH6nF3vyiLfAHkBqRGIVTxd0auRb4C8gNQIRP5tHOQbIC+gPAKR2Tg8CfkGyAtYGoFIZhyq15xAvgHyAnrzwYg2FkAHoJEXsG0dEMnuEjPL56vXBW4AHWCFHavJSwRka3+HP1E8AnSAKrsPV9CvwB2QHwELoAPQWAAdgMYC6AA0FkAHoJEX8Li9wO7DFfkGyAs4PgK/HKnPKr1jpynfAHkBPhukA9BYAB2AxgLoADQWQAegsQA6AI28gA9Czj+qprzpNQAAAABJRU5ErkJggg==";
    }

    public ServerStatusConstructor setBase64Icon(String base64) {
        this.base64Icon = base64;
        return this;
    }

    public String getBase64Icon() { return this.base64Icon; }

    public ServerStatusConstructor setVersionName(String name) {
        this.versionName = name;
        return this;
    }

    public String getVersionName() { return this.versionName; }

    public ServerStatusConstructor setVersionProtocol(int protocol) {
        this.protocol = protocol;
        return this;
    }

    public int getVersionProtocol() { return this.protocol; }

    public ServerStatusConstructor setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public int getMaxPlayers() { return this.maxPlayers; }

    public ServerStatusConstructor setPlayers(int players) {
        this.players = players;
        return this;
    }

    public int getPlayers() { return this.players; }

    public ServerStatusConstructor setEnforcesSecureChat(boolean enforcesSecureChat) {
        this.enforcesSecureChat = enforcesSecureChat;
        return this;
    }

    public boolean getEnforcesSecureChat() { return this.enforcesSecureChat; }

    public ServerStatusConstructor setPreviewsChat(boolean previewsChat) {
        this.previewsChat = enforcesSecureChat;
        return this;
    }

    public boolean getPreviewsChat() { return this.previewsChat; }

    public ServerStatusConstructor setJsonDescription(String json) {
        this.jsonDescription = json;
        return this;
    }

    public String getJsonDescription() { return this.jsonDescription; }

    public ServerStatusConstructor addPlayerSample(String name) {
        return addPlayerSample(this.playersSamples.size(), name, UUID.randomUUID().toString());
    }

    public ServerStatusConstructor addPlayerSample(int index, String name) {
        return addPlayerSample(index, name, UUID.randomUUID().toString());
    }

    public ServerStatusConstructor addPlayerSample(int index, String name, String id) {
        String object = "{\"name\":\"" + name + "\",\"id\":\"" + id + "\"}";
        this.playersSamples.add(index, object);
        return this;
    }

    public ServerStatusConstructor removePlayerSample(int index) {
        this.playersSamples.remove(index);
        return this;
    }

    public int getPlayersSamplesSize() { return this.playersSamples.size(); }
    public ServerStatusConstructor clearPlayersSamples() { this.playersSamples.clear(); return this; }

    private String createJson() {
        StringBuilder result = new StringBuilder("{");
        result.append("\"version\":{")
                .append("\"name\":\"")
                .append(this.versionName)
                .append("\",\"protocol\":")
                .append(this.protocol)
                .append("},");
        result.append("\"players\":{")
                .append("\"max\":")
                .append(this.maxPlayers)
                .append(",\"online\":")
                .append(this.players)
                .append(",\"sample\":[");
        boolean zpt = false;
        for(String block : this.playersSamples) {
            if (zpt) {
                result.append(",");
            }else { zpt = true; }
            result.append(block);
        }
        result.append("]},\"description\":")
                .append(this.jsonDescription)
                .append(",\"favicon\":\"data:image/png;base64,")
                .append(this.base64Icon)
                .append("\",\"enforcesSecureChat\":")
                .append(this.enforcesSecureChat)
                .append(",\"previewsChat\":")
                .append(this.previewsChat);
        result.append("}");
        this.jsonResult = result.toString();
        return this.jsonResult;
    }

    public String getJson(boolean create) {
        if(create) {
            return createJson();
        }
        return this.jsonResult;
    }

}