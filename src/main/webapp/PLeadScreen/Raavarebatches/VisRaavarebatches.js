
$("document").ready(async function(){

    await updateTable()

    $("#visTomme").click(async function () {
        await updateTable();
    });

});

async function updateTable(){

    //Hide table and display loader while updating
    $("#raavarebatches").hide();
    $("#loading").show();

    let path;

    if($("#visTomme").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    await viewlist(
        ["Batch ID", "Råvare ID", "Oprindelig mængde", "Aktuel mængde", "Råvarenavn", "Leverandør"],
        "/BoilerPlate_war_exploded/rest/Raavarebatch/" + path,
        "raavarebatches",
        function () {}
    )

    //Remove loader and reveal table
    $("#loading").hide();
    $("#raavarebatches").show();

};

