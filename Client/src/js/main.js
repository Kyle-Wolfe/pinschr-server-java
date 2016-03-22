/* * * * * */
/* Charts */
/* * * * */
var memChart = new Chartist.Pie('#memory', {});
var gpuMemChart = new Chartist.Pie('#gpuMem', {});


function update(data) {
    memChart.update({series:[data.memory.used, data.memory.free]});
    gpuMemChart.update({series:[data.gpu.used, data.gpu.free]});
    fanSpeed("gpuFan", 0.1);
}





/* * * * * * * * * * */
/* Helper Functions */
/* * * * * * * * * */
function fanSpeed(element, percent) {
    // Don't spin the fan if the fan is operating at
    // zero percent [OFF] or if the fan is not supported  [-1]
    if(percent == -1 || percent == 0) {
        document.getElementById(element).style.animationIterationCount = 0;
    }

    // Min and max animation speeds
    var maxSpeed = 5;
    var minSpeed = 0.5;
    var speed = (maxSpeed - minSpeed) * (1-percent+0.01);
    document.getElementById(element).style.animationDuration = speed.toString()+"s";
}





/* * * * * * * * */
/* Server Stuff */
/* * * * * * * */
var ws;
var connected;
connect();

function connect() {
    if(!ws || ws.readyState !== WebSocket.OPEN) {
        ws = new WebSocket("ws://localhost:8080");
        ws.onerror = function (e) {
            console.log("Error ignored");
            ws.close();
        };
        ws.onopen = function () {
            setConnectedStatus(true);
            ws.send("init");
        };
        ws.onmessage = function (evt) {
            console.log("Message received");
            update(JSON.parse(evt.data));
        };
        ws.onclose = function () {
            setConnectedStatus(false);
        };
    }
}

function disconnect() {
    ws.send("disconnecting");
    ws.close();
    setConnectedStatus(false);
}

function setConnectedStatus(isConnected) {
    if(isConnected) {
        connected = true;
        document.getElementById('status').style.backgroundColor = "green";
    }
    else {
        connected = false;
        document.getElementById('status').style.backgroundColor = "red";

    }
}

window.onbeforeunload = function() {
    disconnect();
};

setInterval(
    function() {
        if(connected){
            ws.send("");
        }
        else {

        }
    }
    ,1000
);