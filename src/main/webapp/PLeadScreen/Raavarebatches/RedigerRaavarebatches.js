$("document").ready(function () {

    const batchID = localStorage.getItem("activeRBId");

    $("#batchID").html(batchID);

    $.getJSON("/BoilerPlate_war_exploded/rest/Raavarebatch/getBatch/" + batchID, function(data) {
        $("#raavareID").html(data.raavareId);
        $("#actualAmount").html(data.aktuelMaengde);
        $("#oriAmount").html(data.startMaengde);

    })

    $("#gem").click(function () {
        save();
    })

    $("table").keypress(function(e){
        save();
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

    $.ajax({
        type: "POST",
        url: "/BoilerPlate_war_exploded/rest/Raavarebatch/opdaterRaavarebatch",
        data: myJson,
        dataType: "json",
        contentType: "application/json; charset=UTF-8"
    });
}