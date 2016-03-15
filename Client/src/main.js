
var context1 = document.getElementById("memory").getContext("2d");
var context2 = document.getElementById("graph2").getContext("2d");
var context3 = document.getElementById("graph3").getContext("2d");

var options = {
    responsive: true,
    animation: false,
    showTooltips: false
};

var chartData = [
    {
        value:0,
        color: "#FF0000",
        highlight: "#FF0000",
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
new Chart(context2).Doughnut(chartData, options);
new Chart(context3).Doughnut(chartData, options);



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
    chartData[0]['value'] = json['memory']['used'];
    chartData[1]['value'] = json['memory']['free'];
    memoryChart.Doughnut(chartData,options);
}

function print() {
    console.log(chartData);
}