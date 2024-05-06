package com.prikolz.mcprotocol.utils;

import com.prikolz.mcprotocol.Server;

import java.time.chrono.IsoEra;

public class Logger {
    public boolean loggerON = false;
    public final Server server;

    public Logger(Server server) {
        this.server = server;
    }

    public void logInfo(Object ... objects) {
        log("[i]", objects, "");
    }

    public void logWarn(Object ... objects) {
        log((char)27 + "[33m[!]", objects, (char)27 + "[0m");
    }

    public void logErr(Object ... objects) {
        log((char)27 + "[31m[ X ]", objects, (char)27 + "[0m");
    }

    private void log(String prefix, Object[] objects, String suffix) {
        if(!loggerON) return;
        System.out.print(prefix + "[mcprotocol]" + "[" + server.port + "] ");
        for(Object obj : objects) {
            System.out.print(obj);
        }
        System.out.print(suffix + "\n");
    }

}
