package com.prikolz;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static boolean runMachine = true;
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server(25565);
        while ( runMachine ) {
            Thread.sleep(1);
        }
    }
}