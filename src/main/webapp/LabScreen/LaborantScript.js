$.ajaxSetup({async: false}); //TODO make work with Tolerance

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
        sendAjax("/BoilerPlate_war_exploded/rest/Recept/getRecepts/" + RID, function (Rdata) {
            var RNavn = Rdata[0].receptNavn;
            if(confirm("Arbejd med Produktbatch " + id + " (" + RNavn + ")")){
                var raavareList = [];
                var raavareNavnObjekt = {};
                $.each(Rdata, function (key, value) {
                    raavareList.push(value.raavareId)
                    sendAjax("/BoilerPlate_war_exploded/rest/Raavare/getRaavare/" + value.raavareId, function (data) {
                        raavareNavnObjekt[value.raavareId] = data.raavareNavn
                        localStorage.setItem("raavareNavnList", JSON.stringify(raavareNavnObjekt));
                    }, function (data) {
                        alert("Error getting Raavarer")
                        console.log(data)
                    })
                })
                localStorage.setItem("raavareList", raavareList);
                switchP("LabScreen/ProcesserProduktbatch/index.html");
                document.getElementById("header").innerText = "Produktbatch: " + id + " (" + RNavn + ")"

                initPB(data);
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

function initPB(data) {
    if (data.status === "Ikke påbegyndt") {
        data.status = "Under Produktion";
        sendAjax("rest/produktbatch/opdaterProduktbatch", function (data) {
            localStorage.setItem("raavareCounter", 0);
            taraView()
        }, function (data) {
            alert("Fejl ved indlæsningen af Produktbatchet. Prøver at genstarte.\nHvis problemet fortsætter, bedes system administratoren kontaktes");
            console.log(data);
            data.status = "Ikke påbegyndt";
            initPB(data);
        }, "POST", JSON.stringify(data));

    } else {
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchComponents/" + data.pbId, function (data) {
            localStorage.setItem("raavareCounter", data.length - 1);
            taraView()
        }, function (data) {
            alert("Error getting batch components: ERR.NO.33")
            console.log(data)
        })
    }
}

function taraView(){
    const PBID = localStorage.getItem("procesPBID");
    const raavareList = localStorage.getItem("raavareList").split(",");
    const raavareNavnList = JSON.parse(localStorage.getItem("raavareNavnList"));
    const counter = localStorage.getItem("raavareCounter");
    const raavareNavn = raavareNavnList[raavareList[counter]]

    document.getElementById("content").innerHTML ="<h2>Indtast Tara for Råvare " + raavareNavn + " (ID: " + raavareList[counter] + ")</h2>" +
        "<br>" +
        "<input id='tara' style='font-size: 14pt; width: 10%; text-align: center;' type='number' min='0.001' max='10' step='0.0001' placeholder='Tara [Kg]'>" +
        "<br>" +
        "<input type='submit' class='hvr-pop screenbtn' value='Næste' onclick='taraSwitch(" + PBID + ")'>"
}

function nettoView() {
    const PBID = localStorage.getItem("procesPBID");
    const raavareList = localStorage.getItem("raavareList").split(",");
    const raavareNavnList = JSON.parse(localStorage.getItem("raavareNavnList"));
    const counter = localStorage.getItem("raavareCounter");
    const raavareNavn = raavareNavnList[raavareList[counter]]
    document.getElementById("content").innerHTML = "" +
        "<h2>Indtast RåvareBatch og Nettovægt for Råvare " + raavareNavn + " (ID: " + raavareList[counter] + ")</h2>" +
        "<br>" +
        "<div style='margin: 1% 30%;'>" +
            "<select id='batchSelect' style='font-size: 14pt; width: 65%; float: left;'>" +
                "<option value='none'>Vælg RåvareBatch ID</option>" +
            "</select>" +
            "<input id='netto' style='font-size: 14pt; width: 25%; text-align: center; float: right;' type='number' min='0.001' max='20' step='0.0001' placeholder='Netto [Kg]'>" +
        "</div>" +
        "<br>" +
        "<input type='submit' class='hvr-pop screenbtn' style='margin-left: 40%;' value='Næste' onclick='nettoSwitch(" + PBID + ")'>"
    sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/getRVIDBatch/" + raavareList[counter], function (data) {
        $.each(data, function (key, value) {
            document.getElementById("batchSelect").innerHTML += "<option value='" + value.rbId + "'>" + value.rbId + ": aktuel mængde: " + value.aktuelMaengde + "Kg</option>"
        })
    }, function (data) {
        alert("Error getting RVIDB List: ERR.NO.25");
        console.log(data)
    })
}

function taraSwitch(PBID) {
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchComponent/" + PBID + "/0", function (data) {
        data.tara = $("#tara").val()
        data.userId = localStorage.getItem("loginID");
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opdaterNewProduktbatch/", function (data) {
            nettoView()
        }, function (data) {
            alert("Error updating new batch: ERR.NO.23")
            console.log(data)
        }, "POST", JSON.stringify(data))
    }, function (data) {
        alert("Error getting batchline: ERR.NO.24")
        console.log(data)
    })
}

function nettoSwitch(PBID) {
    sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/getBatch/" + $("#batchSelect").val(), function (data) {
        if (data.aktuelMaengde < parseFloat($("#netto").val())) {
            alert("Vælg venligst en Batch med nok matriale tilbage.")
            nettoView()
        } else {
            data.aktuelMaengde -= parseFloat($("#netto").val());
            sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/opdaterRaavarebatch", function (data) {
                sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchComponent/" + PBID + "/0", function (data) {
                    data.netto = parseFloat($("#netto").val());
                    data.rbID = parseInt($("#batchSelect").val());
                    console.log(data);
                    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opdaterNewProduktbatch", function (data) {
                        var counter = parseInt(localStorage.getItem("raavareCounter"));
                        const raavareList = localStorage.getItem("raavareList").split(",");
                        if (counter < raavareList.length - 1) {
                            console.log(counter < raavareList.length - 1)
                            localStorage.setItem("raavareCounter", (counter + 1) + "");
                            const newPBLine = {pbId: PBID, receptId: localStorage.getItem("receptId"), status: "Under Produktion"}
                            sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch", function (data) {
                                taraView();
                            }, function (data) {
                                alert("Error creating new pbLine: ERR.NO.30")
                                console.log(data)
                            }, "POST", JSON.stringify(newPBLine))
                        } else {
                            completepb(PBID);
                        }
                    }, function (data) {
                        alert("Error updating new batch: ERR.NO.26")
                        console.log(data)
                    }, "POST", JSON.stringify(data))
                }, function (data) {
                    alert("Error getting batchline: ERR.NO.27")
                    console.log(data)
                })
            }, function (data) {
                alert("Error updating RaavareBatch: ERR.NO.31");
                console.log(data)
            }, "POST", JSON.stringify(data))
        }
    }, function (data) {
        alert("Error getting RaavareBatch: ERR.NO.32");
        console.log(data)
    })

}

function completepb(PBID){
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchLine/" + PBID, function (data) {
        data.status = "Afsluttet";
        console.log(data);
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opdaterProduktbatch/", function (data) {
            alert("Success");
            switchP("LabScreen/index.html");
        }, function (data) {
            alert("Error updating produktbatch: ERR.NO.28");
            console.log(data);
            completepb(PBID);
        }, "POST", JSON.stringify(data))
    }, function (data) {
        alert("Error getting batchline: ERR.NO.29");
        console.log(data);
    })
}

