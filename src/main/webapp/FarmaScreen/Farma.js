

function confirmRaavareUpdate(id){
    $(document).ready(async function () {
        if(confirm("Are you sure, you want to update this råvare "+ id +"?")){
            switchP('FarmaScreen/Raavarer/VisRaavarer/OpdaterRaavare/OpdaterRaavare.html');
            localStorage.setItem("raavareUpdateID", id);
            // $(document).ready(function () {
            await $.getJSON("/BoilerPlate_war_exploded/rest/Raavare/getRaavare/"+ localStorage.getItem("raavareUpdateID"), function (data) {
                document.getElementById("raavareID").innerText = "ID: " + data.raavareID;
                document.getElementById("raavareNavn").value = data.raavareNavn;
                document.getElementById("leverandoer").value = data.leverandoer;
                // })
            });
        }
    });
}

async function raavareData(modType) {
    var APILink = "/BoilerPlate_war_exploded/rest/Raavare/";
    var httpType = "";
    var IID;
    var checkmodtype = modType;
    switch (checkmodtype) {
        case "Create":
            APILink += "opretRaavare";
            httpType += "POST";
            IID = document.getElementById("raavareID").value;
            break;
        case "Update":
            APILink += "updaterRaavare";
            httpType += "PUT";
            IID = localStorage.getItem("raavareUpdateID");
            break;
    }
    const INavn = $("#raavareNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {raavareID: IID, raavareNavn: INavn, leverandoer: ILeve};
    await sendAjax(APILink, function (data) {
        if(checkmodtype === "Create"){
            alert("Opret success!");
            switchP("FarmaScreen/Farma.html");
        }else if(checkmodtype === "Update"){
            alert("Opdateret success!\nFor RåvareID: " + localStorage.getItem("raavareUpdateID"));
            switchP("FarmaScreen/VisRaavare/Farma.html");
        }

    }, function (data) {
        if (data.status != 500) {
            alert(data.responseText);
        } else {
            alert('Enternal Error: Prøve igen!');
        }
    }, httpType, JSON.stringify(jsonData))
}


function postRaavareData() {
    raavareData("Create");
}

function postRaavareUpdate() {


    raavareData("Update");

}

function toOpretrecept() {
    switchP('FarmaScreen/Recepter/NyRecept/NyRecept.html');
    $("#addRaavare").hide();
    $("#loading").hide();
}


