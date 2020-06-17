$("document").ready(function () {
    showPBList();

    let activeReceptID;

    $("#receptValg").change(function () {
        activeReceptID = this.value.split(":")[0];
    })

    $("#opret").click(function () {

        const activeBatchID = $('#batchID').val();

        var obj = { pbId: activeBatchID, status: "Ikke påbegyndt", receptId: activeReceptID, dato: "" };
        var myJson = JSON.stringify(obj);
        console.log(myJson);

        sendAjax(
            "/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch",
            function(data) {
            alert("Produktbatch oprettet succesfuldt");
            $("#gem").removeAttr("hover");
        },
            function (data) {
            alert("Error Creating produktbatch: ERR.NO.12");
            console.log(data);
        },
            "POST",
            myJson);
    })

})


async function showPBList() {

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Recept/getRecepts",
        data => AddToDropdown(data),
        function (data) {
            alert("Error getting actual pb for laborant: ERR.NO.17");
    })
}

function AddToDropdown(data){

    let counter = 0;

    var recepts = "";
    $.each(data, function (key, value) {
        recepts += "<option id='valg_" + counter++ + "'>" + value.receptId + ": " + value.receptNavn + "</option>"
    })

    $("#receptValg").append(recepts);

}












