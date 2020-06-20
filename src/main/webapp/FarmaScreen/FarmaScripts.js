

function confirmRaavareUpdate(id){ /** function til at opdatere råvere*/
    $(document).ready(function () {
        if(confirm("Er du sikker på at du vil opdatere råvare "+ id +"?")){
            switchP('FarmaScreen/VisRaavare/OpdaterRaavare/ProcesserProduktbatch.html');
            localStorage.setItem("raavareUpdateID", id);
            $(document).ready(function () {
                $.getJSON("/BoilerPlate_war_exploded/rest/Raavare/getRaavare/"+ localStorage.getItem("raavareUpdateID"), function (data) {
                    $("#raavareID").innerText = "ID: " + data.raavareID;
                    $("#raavareNavn").val(data.raavareNavn);
                    $("#leverandoer").val(data.leverandoer);
                })
            });
        }
    });
}

function raavareData(modType) {
    var APILink = "/BoilerPlate_war_exploded/rest/Raavare/";
    var httpType = "";
    var IID;
    switch (modType) {
        case "Create":
            APILink+="opretRaavare";
            httpType="POST";
            IID = $("#raavareID").val();
            break;
        case "Update":
            APILink+="updaterRaavare";
            httpType+="PUT";
            IID = localStorage.getItem("raavareUpdateID");
            break;
    }
    const INavn = $("#raavareNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {raavareID: IID, raavareNavn: INavn, leverandoer: ILeve};
    sendAjax(APILink,function (data) {
            switchP("FarmaScreen/ProduktScreen.html");
        }, function (jqXHR, text, error) {
        alert(JSON.stringify(jsonData));
    }, "PUT", JSON.stringify(jsonData))
}

function toOpretrecept() {
    switchP('FarmaScreen/NyRecept/index.html');
    $("#addRaavare").hide();
    $("#loading").hide();
}


