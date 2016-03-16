
var context1 = document.getElementById("memory").getContext("2d");
var context2 = document.getElementById("gpu_memory").getContext("2d");
var context3 = document.getElementById("graph3").getContext("2d");

var options = {
    responsive: true,
    animation: false,
    showTooltips: false
};

var memoryChartData = [
    {
        value:0,
        color: "#0000FF",
        highlight: "#0000FF",
        label: "Used"
    },
    {
        value:1,
        color: "#FFFFFF",
        highlight: "#FFFFFF",
        label: "Free"
    }
];

var gpuMemoryChartData = [
    {
        value:0,
        color: "#006400",
        highlight: "#006400",
        label: "Used"
    },
    {
        value:1,
        color: "#FFFFFF",
        highlight: "#FFFFFF",
        label: "Free"
    }
];

var memoryChart = new Chart(context1);
var gpuMemoryChart = new Chart(context2)
//new Chart(context3).Doughnut(memoryChartData, options);



var ws = new WebSocket("ws://localhost:8080/server/echo");
ws.onopen = function() {
    ws.send("init");
};
ws.onmessage = function(evt) {
    update(evt.data);
};


setInterval(
    function() {
        ws.send("");
    }
    ,500
);


function update(data) {
    var json = JSON.parse(data);

    //memoryChartData[0]['value'] = json['memory']['used'];
    //memoryChartData[1]['value'] = json['memory']['free'];
    memoryChart.Doughnut(memoryChartData, options);

    gpuMemoryChartData[0]['value'] = json['gpu']['memory.used'];
    gpuMemoryChartData[1]['value'] = json['gpu']['memory.free'];
    console.log(json);
    gpuMemoryChart.Doughnut(gpuMemoryChartData, options);
}

function print() {
    console.log(chartData);
}