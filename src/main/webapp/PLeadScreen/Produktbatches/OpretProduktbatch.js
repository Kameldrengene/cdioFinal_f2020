function showPBList() {
    console.log("test1")
    var inner = "";
    $(document).ready(
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getAktuelle", function (data) {
            let counter = 0;
            console.log(data);
            $.each(data, function (key, value) {
                inner += "<option id='valg_" + counter++ + "' value='" + value.pbId + "'>" + value.pbId + ": " + value.status + "</option>"
            })
            document.getElementById("pbvalg").innerHTML = document.getElementById("pbvalg").innerHTML + inner;
        }, function (data) {
            alert("Error getting actual pb for laborant: ERR.NO.17");
            console.log(data);
        })
    )
}











$("#gem").click(function () {

    const batchID = $('#batchID').val();
    const status = $('#status').val();
    const receptID = $('#receptID').val();
    const brugerID = $('#brugerID').val();
    const raavarebatchID = $('#raavarebatchID').val();
    const tara = $('#tara').val();
    const netto = $('#netto').val();

    var obj = { pbId: batchID, status: status, receptId: receptID, userId: brugerID, rbID: raavarebatchID, tara: tara, netto: netto };
    var myJson = JSON.stringify(obj);

    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch", function(data) {
        alert("Råvarebatch oprettet succesfuldt");
        $("#gem").removeAttr("hover");
    }, function (data) {
        alert("Error Creating produktbatch: ERR.NO.12");
        console.log(data);
    }, "POST", myJson);

})


