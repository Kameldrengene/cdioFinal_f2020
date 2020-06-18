$("document").ready(async function () {

    const activeRaavareID = localStorage.getItem("activeRaavare");

    $("#raavareID").html("Råvare ID: " + activeRaavareID);

    $("#opret").click(async function () {

        let confirmation = confirm("Opret råvarebatch for råvare ID: " + activeRaavareID);
        if (confirmation == true)
            await opretRaavarebatch(this.id);
    });

});

async function opretRaavarebatch(){

    const activeRaavareID = localStorage.getItem("activeRaavare");
    const activeBatchID = $('#batchID').val();
    const activeStartMaengde = $('#startMaengde').val();

    const obj = { rbId: activeBatchID, raavareId: activeRaavareID, aktuelMaengde: activeStartMaengde, startMaengde: activeStartMaengde };
    const myJson = JSON.stringify(obj);

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Raavarebatch/opretRaavarebatch",
        () => success(),
        err => error(err),
        "POST",
        myJson
    );
};

function success(){
    alert("Råvarebatch oprettet succesfuldt");
    switchP("PLeadScreen/Raavarebatches/OpretRaavarebatch/VaelgRaavare.html")
}

function error(err){
    const status = err.status;
    if(status != 500) alert(err.responseText);
    else alert("ERROR: Fejl i forbindelse med håndtering af input. Prøv igen")
}
