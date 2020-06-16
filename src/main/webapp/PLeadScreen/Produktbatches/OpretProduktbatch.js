$("document").ready(function () {
    showPBList();

    let status;

    $("#receptValg").change(function () {
        status = this.value;
    })

    $("#gem").click(function () {

        const batchID = $('#batchID').val();
        const receptID = $('#receptID').val();

        var obj = { pbId: batchID, status: status, receptId: receptID};
        var myJson = JSON.stringify(obj);

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












