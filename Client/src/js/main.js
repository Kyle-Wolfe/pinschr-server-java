var json;
var ws = new WebSocket("ws://localhost:8080");
ws.onopen = function() {
    ws.send("init");
};
ws.onmessage = function (evt) {
    json = JSON.parse(evt.data);
};
setInterval(
    function() {
        ws.send("");
        update();
    }
    ,500
);
/* */
/* Connected to server */
/* */

function update() {
    document.getElementById('jsonDiv').innerHTML = JSON.stringify(json, null, 2);
}