package com.prikolz;

import com.prikolz.mcprotocol.Server;
import com.prikolz.mcprotocol.server.runnables.DefalutServerRunnable;
import com.prikolz.mcprotocol.utils.Logger;

import java.io.IOException;

public class Main {

    public static boolean runMachine = true;
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server(25565, true, new DefalutServerRunnable());
        while ( runMachine ) {
            Thread.sleep(1);
        }
    }
}