$("document").ready(function () {

    const batchID = localStorage.getItem("activeRBId");

    $("#batchID").html(batchID);

    $.getJSON("/BoilerPlate_war_exploded/rest/Raavarebatch/getBatch/" + batchID, function(data) {
        $("#raavareID").html(data.raavareId);
        $("#actualAmount").html(
            "<form onsubmit='return false;'> " +
            "<input type='text' id='newAmount' value=" + data.aktuelMaengde + ">" +
            "</form>"
        );
        $("#oriAmount").html(data.startMaengde);

    })

    $("#gem").click(function () {
        const newAmount = $("#newAmount").val();
        console.log(newAmount);

    })

    $('input').keyup(function(e){
        if(e.keyCode == 13) {
            const newAmount = $("#newAmount").val();
            console.log(newAmount);
        }
    });





})