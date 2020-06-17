$("document").ready(function () {

    const activeRaavareID = localStorage.getItem("activeRaavare");

    $("#raavareID").html("Råvare ID: " + activeRaavareID);

    $("#opret").click(function () {

        let confirmation = confirm("Opret råvarebatch for råvare ID: " + activeRaavareID);
        if (confirmation == true)
            opretRaavarebatch(this.id);
    })

});

function opretRaavarebatch(){

    const activeRaavareID = localStorage.getItem("activeRaavare");
    const activeBatchID = $('#batchID').val();
    const activeStartMaengde = $('#startMaengde').val();

    var obj = { rbId: activeBatchID, raavareId: activeRaavareID, aktuelMaengde: activeStartMaengde, startMaengde: activeStartMaengde };
    var myJson = JSON.stringify(obj);

    sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/opretRaavarebatch", function (data) {
        alert("Råvarebatch oprettet succesfuldt");
        $("#gem").removeAttr("hover");
        switchP("PLeadScreen/PLeadScreen.html")
    }, function (data) {
        alert("Error making RaavareBatch: ERR.NO.08");
        console.log(data);
    }, "POST", myJson);

};
