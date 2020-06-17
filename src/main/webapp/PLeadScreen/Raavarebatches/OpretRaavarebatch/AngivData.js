$("document").ready(async function () {

    const activeRaavareID = localStorage.getItem("activeRaavare");

    $("#raavareID").html("Råvare ID: " + activeRaavareID);

    $("#opret").click(async function () {

        let confirmation = confirm("Opret råvarebatch for råvare ID: " + activeRaavareID);
        if (confirmation == true){

            try{
                await opretRaavarebatch(this.id);
                alert("Råvarebatch oprettet succesfuldt");
                switchP("PLeadScreen/Raavarebatches/OpretRaavarebatch/VaelgRaavare.html")
            } catch(err){
                console.log(err);
                alert(err.responseText);
            }
        }
    });

});

async function opretRaavarebatch(){

    const activeRaavareID = localStorage.getItem("activeRaavare");
    const activeBatchID = $('#batchID').val();
    const activeStartMaengde = $('#startMaengde').val();

    const obj = { rbId: activeBatchID, raavareId: activeRaavareID, aktuelMaengde: activeStartMaengde, startMaengde: activeStartMaengde };
    const myJson = JSON.stringify(obj);

    await $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavarebatch/opretRaavarebatch",
        type: "POST",
        async: true,
        contentType: "application/json",
        dataType: "json",
        data: myJson,
    });

};
