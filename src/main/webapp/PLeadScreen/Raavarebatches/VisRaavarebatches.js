
$("document").ready(async function(){

    await updateTable().then(function () {

        $("#visTomme").click(function () {
            updateTable();
        });

        $("#raavarebatches").on("click", "button", function () {
            localStorage.setItem("activeRBId", this.id);
            switchP("PLeadScreen/Raavarebatches/RedigerRaavarebatches.html");
        });
    });

});

async function updateTable(){

    //Hide table while updating
    $("#raavarebatches").hide();

    //Display loader
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

