Ajax:js sent request to server in certain time to query data.

Long pull:  wait for server response then continue.

Websocket: build a connection, server push information to client.

//Server side
Websocket on Netty:
   WSServer.java:
        create two thread pools add to ServerBootStrap, also add Channel and ChildrenHandler then bind port.
   WSServerIntializer.java:
        Extends from ChannelInitializer.
        add pipeline with handlers to channel.



//front - end
Websocket based on JS:
    var socket = new WebSocket("ws://[ip][port]");
  life time:
    onopen(), onmessage(), onerror(), onclose()
  method;
    socket.send(), soket.close()
