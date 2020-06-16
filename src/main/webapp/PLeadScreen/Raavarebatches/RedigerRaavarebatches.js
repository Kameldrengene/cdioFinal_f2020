$("document").ready(function () {

    const batchID = localStorage.getItem("activeRBId");

    $("#batchID").html(batchID);

    sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/getBatch/" + batchID, function(data) {
        $("#raavareID").html(data.rbId);
        $("#actualAmount").html(data.aktuelMaengde);
        $("#oriAmount").html(data.startMaengde);
    }, function (data) {
        alert("Error getting RaavareBatch by ID: ERR.NO.09");
        console.log(data);
    })

    $("#gem").click(function () {
        save();
    })

    //Disable enter
    $("table").keypress(function(e){
        return e.which != 13;
    });


});

function save(){

    const batchID = $(".brugertable").find("td").eq(0).text();
    const raavareID = $(".brugertable").find("td").eq(1).text();
    const mængde = $(".brugertable").find("td").eq(2).text();
    const oprindeligMaengde = $(".brugertable").find("td").eq(3).text();

    var obj = { rbId: batchID, raavareId: raavareID, aktuelMaengde: mængde, startMaengde: oprindeligMaengde };
    var myJson = JSON.stringify(obj);
    sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/getBatch/opdaterRaavarebatch", function(data) {
        alert("Råvarebatch successfuldt opdateret");
        $("#gem").removeAttr("hover");
    }, function (data) {
        alert("Error getting RaavareBatch by ID: ERR.NO.10");
        console.log(data);
    }, "POST", myJson)
}