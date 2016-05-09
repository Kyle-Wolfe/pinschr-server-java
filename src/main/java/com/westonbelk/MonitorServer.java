package com.westonbelk;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;

public class MonitorServer extends WebSocketServer {

    private SystemMonitor system;

    public static void main(String[] args) throws InterruptedException, IOException {
        // Initialize Logging
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        System.setProperty(SimpleLogger.SHOW_DATE_TIME_KEY, "true");
        System.setProperty(SimpleLogger.DATE_TIME_FORMAT_KEY, "[MM/dd/yy HH:mm]");
        Logger LOG = LoggerFactory.getLogger(MonitorServer.class);
        WebSocketImpl.DEBUG = false;

        // Start the server
        int port = 3000;
        MonitorServer server = new MonitorServer(port);
        server.start();
        LOG.info("Server started on port " + server.getPort());

        // Async thread to update and send information to clients
        AsyncUpdate updateRunner = new AsyncUpdate(server.getSystem(), server);
        new Thread(updateRunner).start();

        // Commandline interface for controlling the server
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        boolean isRunning = true;
        while(isRunning) {
            String in = sysin.readLine();
            switch (in) {
                case "stop":
                    isRunning = false;
                    break;
                case "print":
                    LOG.info("Printing current Json");
                    System.out.println(server.getSystem().toString());
                    break;
                case "update":
                    server.getSystem().update();
                    LOG.info("System information manually updated.");
            }
        }

        // Shutdown
        updateRunner.stop();
        server.stop();
        LOG.info("Server told to shutdown.");
        System.exit(0);
    }

    public MonitorServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        system = new SystemMonitor();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected.");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " has disconnected.");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
    }


    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if(conn != null) {

        }
    }

    public SystemMonitor getSystem() {
        return this.system;
    }

}