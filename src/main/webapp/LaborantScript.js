$.ajaxSetup({async: false});

function showPBList() {
    var inner = "";
    $(document).ready(
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getAktuelle", function (data) {
            let counter = 0;
            $.each(data, function (key, value) {
                inner += "<option id='valg_" + counter++ + "' value='" + value.pbId + "'>" + value.pbId + ": " + value.status + "</option>"
            })
            document.getElementById("pbvalg").innerHTML = document.getElementById("pbvalg").innerHTML + inner;
        }, function (data) {
            alert("Error getting actual pb for laborant: ERR.NO.17");
            console.log(data);
        })
    )
}


function getProductBatch(id){
    localStorage.setItem("procesPBID", id);
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchLine/" + id, function (data) {
        var RID = data.receptId;
        localStorage.setItem("receptId", RID);
        sendAjax("/BoilerPlate_war_exploded/rest/Recept/getReceptList/" + RID, function (Rdata) {
            var RNavn = Rdata[0].receptNavn;
            if(confirm("Arbejd med Produktbatch " + id + " (" + RNavn + ")")){
                var raavareList = [];
                var raavareNavnList = [];
                $.each(Rdata, function (key, value) {
                    raavareList.push(value.raavareId)
                    sendAjax("/BoilerPlate_war_exploded/rest/Raavare/getRaavare/" + value.raavareId, function (data) {
                        raavareNavnList.push(data.raavareNavn)
                        localStorage.setItem("raavareNavnList", raavareNavnList);
                    }, function (data) {
                        alert("Error getting Raavarer")
                        console.log(data)
                    })
                })
                localStorage.setItem("raavareList", raavareList);
                switchP("LabScreen/ProcesserProduktbatch/index.html");
                document.getElementById("header").innerText = "Produktbatch: " + id + " (" + RNavn + ")"

                initPB(data, Rdata);
            }
        }, function (data) {
            alert("Error getting recept: ERR.NO.18");
            console.log(data)
        })
    }, function (data) {
        alert("Error getting recept: ERR.NO.19");
        console.log(data)
    })
}

function initPB(data, Rdata) {
    if (data.status === "Ikke påbegyndt") {
        data.status = "Under Produktion";
        sendAjax("rest/produktbatch/opdaterProduktbatch", function (data) {
            localStorage.setItem("raavareCounter", 0);
            taraView()
        }, function (data) {
            alert("Error updating produktbatch: ERR.NO.22");
            console.log(data);
        }, "POST", JSON.stringify(data));

    } else {
        //TODO find correct place
    }
}

function taraView(){
    const PBID = localStorage.getItem("procesPBID");
    const RID = localStorage.getItem("receptId");
    const raavareList = localStorage.getItem("raavareList").split(",");
    const raavareNavnList = localStorage.getItem("raavareNavnList").split(",");
    const counter = localStorage.getItem("raavareCounter");
    const raavareNavn = raavareNavnList[counter]

    document.getElementById("content").innerHTML ="<h2>Indtast Tara for Råvare " + raavareNavn + " (ID: " + raavareList[counter] + ")</h2>" +
        "<br>" +
        "<input id='tara' style='font-size: 14pt' type='number' min='0.05' max='10' step='0.01' placeholder='Tara [Kg]'>" +
        "<br>" +
        "<input type='submit' class='hvr-pop screenbtn' value='Næste' onclick='taraSwitch(PBID)'>"
}

function nettoView() {
    const PBID = localStorage.getItem("procesPBID");
    const RID = localStorage.getItem("receptId");
    const raavareList = localStorage.getItem("raavareList").split(",");
    const raavareNavnList = localStorage.getItem("raavareNavnList").split(",");
    const counter = localStorage.getItem("raavareCounter");
    const raavareNavn = raavareNavnList[counter]
    document.getElementById("content").innerHTML = "<p> Success </p>"
}

function taraSwitch(PBID) {
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchLine/" + PBID, function (data) {
        data.tara = $("#tara").val()
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opdaterNewProduktbatch", function (data) {
            nettoView()
        }, function (data) {
            alert("Error updating new batch: ERR.NO.23")
            console.log(data)
        }, "POST", JSON.stringify(data))
    }, function (data) {
        alert("Error getting line: ERR.NO.24")
        console.log(data)
    })
}



