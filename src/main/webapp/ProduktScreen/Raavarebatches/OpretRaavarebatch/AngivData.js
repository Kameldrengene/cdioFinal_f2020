//Created by Mikkel

$("document").ready(async function () {

    const activeRaavareID = localStorage.getItem("activeRaavare");

    //Show active raave ID
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

    //To avoid ajax conversion error
    if( !wholeNumberTest(activeBatchID)){
        alert("Kun heltal er tilladte som ID");
        return
    }

    //To avoid ajax conversion error
    if( isNaN(activeStartMaengde) || activeStartMaengde.localeCompare("") ==0 ){
        alert("Kun tal er tilladte som startmængde");
        return
    }

    //Create JSON
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
    switchP("ProduktScreen/Raavarebatches/OpretRaavarebatch/VaelgRaavare.html")
}

function wholeNumberTest(n) {

    console.log("n:" + n);
    if (n == "") return false;

    const result = (n - Math.floor(n)) !== 0;

    if (result) return false;
    else return true;
}
