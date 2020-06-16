









$("#gem").click(function () {

    const batchID = $('#batchID').val();
    const raavareID = $('#raavareID').val();
    const mængde = $('#aktuelMaengde').val();
    const oprindeligMaengde = $('#oprindeligMaengde').val();

    var obj = { rbId: batchID, raavareId: raavareID, aktuelMaengde: mængde, startMaengde: oprindeligMaengde };
    var myJson = JSON.stringify(obj);

    $.ajax({
        type: "POST",
        url: "/BoilerPlate_war_exploded/rest/Raavarebatch/opretRaavarebatch",
        data: myJson,
        //todo - giver altid sucess
        success: function() {
            alert("Råvarebatch oprettet succesfuldt");
            $("#gem").removeAttr("hover");
        },
        dataType: "json",
        contentType: "application/json; charset=UTF-8"
    });

})

