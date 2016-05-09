package com.westonbelk;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AsyncUpdate implements Runnable {
    private SystemMonitor system;
    private WebSocketServer ws;
    private volatile boolean done;
    private Logger LOG;

    public AsyncUpdate(SystemMonitor system, WebSocketServer ws) {
        this.system = system;
        this.ws = ws;
        this.done = false;
        this.LOG = LoggerFactory.getLogger(AsyncUpdate.class);
    }

    public void stop() {
        LOG.info("Update thread told to shutdown.");
        done = true;
    }

    public void run() {
        LOG.info("Update thread started.");
        try {
            while(!done) {
                system.update();
                for (WebSocket client : ws.connections()) {
                    client.send(system.toString());
                }
                Thread.sleep(1000);
            }
            LOG.info("Update thread has completed.");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
