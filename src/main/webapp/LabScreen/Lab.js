$.ajaxSetup({async: false});

/** PB = ProduktBatch */

function showPBList() { /** Loader data ind i Selecten "pbvalg" */
    var inner = "";
    $(document).ready(
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getAktuelle", function (data) {
            let counter = 0;
            $.each(data, function (key, value) {
                inner += "<option id='valg_" + counter++ + "' value='" + value.pbId + "'>" + value.pbId + ": " + value.status + "</option>"
            })
            document.getElementById("pbvalg").innerHTML += inner;
        }, function (data) {
            alert("Error getting actual pb for laborant: ERR.NO.17");
            console.log(data);
        })
    )
}


function getProductBatch(id){ /** Henter data om en PB: PBID, ReceptID, og en råvareliste og dertilhørende råvarenavneobjekt bliver gemt i local storage */
    localStorage.setItem("procesPBID", id);
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchLine/" + id, function (data) { /** henter PB for at finde ReceptID */
        var RID = data.receptId;
        localStorage.setItem("receptId", RID);
        sendAjax("/BoilerPlate_war_exploded/rest/Recept/getRecepts/" + RID, function (Rdata) { /** henter Recept for at finde Råvarene der skal i PBen */
            var RNavn = Rdata[0].receptNavn;
            if(confirm("Arbejd med Produktbatch " + id + " (" + RNavn + ")")){
                var raavareList = [];
                var raavareNavnObjekt = {};
                $.each(Rdata, function (key, value) {
                    raavareList.push(value.raavareId) /** Gemmer id for hver Råvare */
                    sendAjax("/BoilerPlate_war_exploded/rest/Raavare/getRaavare/" + value.raavareId, function (data) { /** Henter Råvarenavn og gemmer det */
                        raavareNavnObjekt[value.raavareId] = data.raavareNavn
                        localStorage.setItem("raavareNavnList", JSON.stringify(raavareNavnObjekt));
                    }, function (data) {
                        alert("Error getting Raavarer")
                        console.log(data)
                    }, "GET", "None", false)
                })
                localStorage.setItem("raavareList", raavareList);
                switchP("LabScreen/ProcesserProduktbatch/ProcesserProduktbatch.html");
                document.getElementById("header").innerText = "Produktbatch: " + id + " (" + RNavn + ")"

                initPB(data); /** starter arbejdet med PBen */
            }
        }, function (data) {
            alert("Error getting recept: ERR.NO.18");
            console.log(data)
        }, "GET", "None", false)
    }, function (data) {
        alert("Error getting recept: ERR.NO.19");
        console.log(data)
    }, "GET", "None", false)
}

function initPB(data) { /** Påbegynder arbejdet med PB. Forskelligt for hvis den allerede er under produktion eller ikke påbegyndt */
    if (data.status === "Ikke påbegyndt") {
        data.status = "Under Produktion";
        sendAjax("rest/produktbatch/opdaterProduktbatch", function (data) { /** sætter PB til at være under produktion */
            localStorage.setItem("raavareCounter", 0); /** Starter counteren på 0 */
            taraView()
        }, function (data) {
            alert("Fejl ved indlæsningen af Produktbatchet. Prøver at genstarte.\nHvis problemet fortsætter, bedes system administratoren kontaktes");
            console.log(data);
            data.status = "Ikke påbegyndt";
            switchP("LabScreen/ProcesserProduktbatch.html")
        }, "POST", JSON.stringify(data));

    } else { /** allerede under produktion */
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchComponents/" + data.pbId, function (data) { /** henter listen af PBes allerede i DB */
            localStorage.setItem("raavareCounter", data.length - 1); /** sætter counteren til at være antallet af PBer i db -1, da der er en ekstra "tom" PB, som skal bruges til den næste råvare */
            taraView()
        }, function (data) {
            alert("Error getting batch components: ERR.NO.33")
            console.log(data)
        })
    }
}

function taraView(){
    const PBID = localStorage.getItem("procesPBID"); /** henter data fra local storage */
    const raavareList = localStorage.getItem("raavareList").split(",");
    const raavareNavnList = JSON.parse(localStorage.getItem("raavareNavnList"));
    const counter = parseInt(localStorage.getItem("raavareCounter")); /** counter beskriver hvilken råvare produktbatchen er nået til, ift index i raavareList */
    const raavareNavn = raavareNavnList[parseInt(raavareList[counter])]

    /** indsætter html */
    $("#content").innerHTML ="<h2>Indtast Tara for Råvare " + raavareNavn + " (ID: " + raavareList[counter] + ")</h2>" +
        "<br>" +
        "<input id='tara' style='font-size: 14pt; width: 10%; text-align: center;' type='number' min='0.001' max='10' step='0.0001' placeholder='Tara [Kg]'>" +
        "<br>" +
        "<input type='submit' class='hvr-pop screenbtn' value='Næste' onclick='taracheck(" + PBID + ")'>"
}

function taracheck(PBID) { /** Tjekker om korrekt indtastet data */
    (parseFloat($("#tara").val()) <= 10) ? taraSwitch(PBID) : (alert(" + 'Tara skal være under 10 kg' + "))
}

function nettoView() {
    const PBID = localStorage.getItem("procesPBID"); /** henter data fra local storage */
    const raavareList = localStorage.getItem("raavareList").split(",");
    const raavareNavnList = JSON.parse(localStorage.getItem("raavareNavnList"));
    const counter = localStorage.getItem("raavareCounter");
    const raavareNavn = raavareNavnList[raavareList[counter]]
    $("#content").innerHTML = "" +
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
    sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/getRVIDBatch/" + raavareList[counter], function (data) { /** Henter Råvarebatches til råvaren og sætter dem ind i Selecten */
        $.each(data, function (key, value) {
            document.getElementById("batchSelect").innerHTML += "<option value='" + value.rbId + "'>" + value.rbId + ": aktuel mængde: " + value.aktuelMaengde + "Kg</option>"
        })
    }, function (data) {
        alert("Error getting RVIDB List: ERR.NO.25");
        console.log(data)
    })
}

function taraSwitch(PBID) { /** Metode der bliver kørt, når siden skifter fra Tara til Netto */
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchComponent/" + PBID + "/0", function (data) { /** Henter PB fra DB og opdaterer den Tara værdi */
        data.tara = $("#tara").val()
        data.userId = localStorage.getItem("loginID");
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opdaterNewProduktbatch/", function (data) { /** opdaterer den i backenden */
            nettoView()
        }, function (data) {
            alert("Error updating new batch: ERR.NO.23")
            console.log(data)
        }, "POST", JSON.stringify(data), false)
    }, function (data) {
        alert("Error getting batchline: ERR.NO.24")
        console.log(data)
    }, "GET", "None", false)
}

function nettoSwitch(PBID) { /** Metode der bliver kørt, når siden skifter fra Netoo til Tara og hvis produktbatchen er færdig */
    const RID = localStorage.getItem("receptId");
    const counter = parseInt(localStorage.getItem("raavareCounter"));
    const raavareList = localStorage.getItem("raavareList").split(",");
    sendAjax("/BoilerPlate_war_exploded/rest/Recept/getRecept/" + RID + "/" + raavareList[counter], function (data) { /** Henter Recepten for at tjekke om tolerancen er overholdt */
        const tolvaegt = data.nonNetto * data.tolerance / 100;
        if (parseFloat($("#netto").val()) <= data.nonNetto + tolvaegt && parseFloat($("#netto").val()) >= data.nonNetto - tolvaegt) { /** tjekker om tolerancen er overholdt */
            if ($("#batchSelect").val() === "none") { /** Tjekker om en råvarebatch er blevet valgt */
                alert("Vælg en Råvarebatch")
                nettoView()
            } else {
                sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/getBatch/" + $("#batchSelect").val(), function (data) { /** Henter batchen for at opdatere den aktuelle mængde i den */
                    if (data.aktuelMaengde < parseFloat($("#netto").val())) { /** tjekker om der er nok materiale i råvarebatchen */
                        alert("Vælg venligst en Batch med nok matriale tilbage.")
                        nettoView()
                    } else {
                        data.aktuelMaengde -= parseFloat($("#netto").val());
                        sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/opdaterRaavarebatch", function (data) { /** opdaterer råvarebatchen med dens nye aktuelle mængde */
                            sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchComponent/" + PBID + "/0", function (data) { /** Henter den "nye" PB */
                                data.netto = parseFloat($("#netto").val());
                                data.rbID = parseInt($("#batchSelect").val());
                                console.log(data);
                                sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opdaterNewProduktbatch", function (data) { /** Opdaterer PBen i databasen */
                                    var counter = parseInt(localStorage.getItem("raavareCounter"));
                                    const raavareList = localStorage.getItem("raavareList").split(",");
                                    if (counter < raavareList.length - 1) { /** Tjekker om flere PB skal oprettes */
                                        localStorage.setItem("raavareCounter", (counter + 1) + "");
                                        const newPBLine = {
                                            pbId: PBID,
                                            receptId: localStorage.getItem("receptId"),
                                            status: "Under Produktion"
                                        }
                                        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch", function (data) { /** opretter ny PB */
                                            taraView(); /** går tilbage til taraView */
                                        }, function (data) {
                                            alert("Error creating new pbLine: ERR.NO.30")
                                            console.log(data)
                                        }, "POST", JSON.stringify(newPBLine))
                                    } else {
                                        completepb(PBID); /** bliver kaldt for at afslutte PB, når den sidste er blevet helt opdateret */
                                    }
                                }, function (data) {
                                    alert("Error updating new batch: ERR.NO.26")
                                    console.log(data)
                                }, "POST", JSON.stringify(data), false)
                            }, function (data) {
                                alert("Error getting batchline: ERR.NO.27")
                                console.log(data)
                            }, "GET", "None", false)
                        }, function (data) {
                            alert("Error updating RaavareBatch: ERR.NO.31");
                            console.log(data)
                        }, "POST", JSON.stringify(data), false)
                    }
                }, function (data) {
                    alert("Error getting RaavareBatch: ERR.NO.32");
                    console.log(data)
                }, "GET", "None", false)
            }
        } else {
            alert("Vægt ikke acceptabel, prøv igen")
            nettoView()
        }
    }, function (data) {
        alert("Error getting Recept: ERR.NO.34");
        console.log(data)
    }, "GET", "None", false)


}

function completepb(PBID){ /** Kører for at afslutte PB */
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchLine/" + PBID, function (data) { /** opdaterer alle PB med den rigtige ID til at være Afsluttet */
        data.status = "Afsluttet";
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opdaterProduktbatch/", function (data) {
            alert("Success");
            switchP("LabScreen/Lab.html"); /** bliver returneret til startskærmen for Laboranter */
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

