$("document").ready(function () {

    const batchID = localStorage.getItem("activePBId");

    $("#batchID").html(batchID);

    $.getJSON("/BoilerPlate_war_exploded/rest/produktbatch/getBatch/" + batchID, function(data) {
        $("#status").html(data.status);
        $("#receptID").html(data.receptId);
        $("#brugerID").html(data.userId);
        $("#råvarebatchID").html(data.rbID);
        $("#tara").html(data.tara);
        $("#netto").html(data.netto);

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
    const status = $(".brugertable").find("td").eq(1).text();
    const receptID = $(".brugertable").find("td").eq(2).text();
    const brugerID = $(".brugertable").find("td").eq(3).text();
    const råvarebatchID = $(".brugertable").find("td").eq(4).text();
    const tara = $(".brugertable").find("td").eq(5).text();
    const netto = $(".brugertable").find("td").eq(6).text();

    var obj = { pbId: batchID, status: status, receptId: receptID, userId: brugerID, rbID: råvarebatchID, tara: tara, netto: netto };
    var myJson = JSON.stringify(obj);

    $.ajax({
        type: "POST",
        url: "/BoilerPlate_war_exploded/rest/produktbatch/opdaterProduktbatch",
        data: myJson,
        dataType: "json",
        contentType: "application/json; charset=UTF-8"
    });
}