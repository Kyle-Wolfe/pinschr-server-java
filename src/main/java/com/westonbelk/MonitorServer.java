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
import java.util.Date;

public class MonitorServer extends WebSocketServer {

    private SystemMonitor system;

    public static void main(String[] args) throws InterruptedException, IOException {
        WebSocketImpl.DEBUG = false;
        int port = 3000;
        MonitorServer server = new MonitorServer(port);
        server.start();
        System.out.println("Server started on port " + server.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String in = sysin.readLine();
            if(in.equals("stop")) {
                server.stop();
                break;
            }
            else if(in.equals("print")) {
                System.out.println("["+new Date().toString()+"] Printing current Json");
                server.getSystem().printPretty();
            }
            else if(in.equals("update")) {
                server.getSystem().update();
                System.out.println("["+new Date().toString()+"] Updated");
            }
        }
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
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + ": " + message);
        system.update();
        conn.send(system.toString());
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