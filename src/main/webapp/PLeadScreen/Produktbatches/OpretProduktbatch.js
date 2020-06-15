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

    $.ajax({
        type: "POST",
        url: "/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch",
        data: myJson,
        success: function() {
            alert("Råvarebatch oprettet succesfuldt");
            $("#gem").removeAttr("hover");
        },
        dataType: "json",
        contentType: "application/json; charset=UTF-8"
    });

})


