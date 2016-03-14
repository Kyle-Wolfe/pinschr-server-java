
var context1 = document.getElementById("memory").getContext("2d");
var context2 = document.getElementById("graph2").getContext("2d");
var context3= document.getElementById("graph3").getContext("2d");

var options = {
    responsive: true
};

var data = [
    {
        value:300,
        color: "#FF0000",
        highlight: "#FF0000",
        label: "Test"
    },
    {
        value:50,
        color: "#FFFFFF",
        highlight: "#FFFFFF",
        label: "Blank Space"
    }
]

var myNewChart = new Chart(context1).Doughnut(data, options);
var myNewChart = new Chart(context2).Doughnut(data, options);
var myNewChart = new Chart(context3).Doughnut(data, options);