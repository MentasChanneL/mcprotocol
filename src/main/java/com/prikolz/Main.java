package com.prikolz;

import com.prikolz.mcprotocol.client.Server;

import java.io.IOException;

public class Main {

    public static boolean runMachine = true;
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server(25565);
        while ( runMachine ) {
            Thread.sleep(1);
        }
    }
}