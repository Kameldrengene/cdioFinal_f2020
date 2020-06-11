$("document").ready(function () {

    const batchID = localStorage.getItem("activeRBId");

    $("#batchID").html(batchID);

    $.getJSON("/BoilerPlate_war_exploded/rest/RVB/getRVB/" + batchID,function (data) {
        $("#raavareID").html(data.raavareId);
        $("#actualAmount").html(
            "<form> " +
            "<input type='text' id='newAmount' value=" + data.aktuelMaengde + ">" +
            "</form>"

        );
        $("#oriAmount").html(data.startMaengde);

    })



})