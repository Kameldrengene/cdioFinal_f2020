$.ajaxSetup({async: false});

function showPBList() {
    var inner = "";
    $(document).ready(
        sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getAktuelle", function (data) {
            let counter = 0;
            console.log(data);
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
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatch/" + id, function (data) {
        console.log(data);
        var RID = data.receptId;
        if (RID == undefined) {
            RID = data[0].receptId;
        }
        localStorage.setItem("receptId", RID);
        sendAjax("/BoilerPlate_war_exploded/rest/Recept/getRecept/" + RID, function (data2) {
            var RNavn = data2.receptNavn;
            if (RNavn == undefined) {
                RNavn = data2[0].receptNavn;
            }
            if(confirm("Arbejd med Produktbatch " + id + " (" + RNavn + ")")){
                var raavareList = [];
                console.log(data2);
                $.each(data2, function (key, value) {
                    raavareList.push(value.raavareId)
                })
                localStorage.setItem("raavareList", raavareList);
                switchP("LabScreen/ProcesserProduktbatch/index.html");
                document.getElementById("header").innerText = "Produktbatch: " + id + " (" + RNavn + ")"
                taraView()
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


function taraView(){
    const PBID = localStorage.getItem("procesPBID");
    const RID = localStorage.getItem("receptId");
    const raavareList = localStorage.getItem("raavareList").split(",");
    let raavareNavn = "";
    let counter = 0;
    let todo = []
    for (let i = 0; i < raavareList.length; i++) {
        sendAjax("BoilerPlate_war_exploded/rest/produktbatch/getBatchLine/" + PBID + "/" + raavareList[counter], function (data) {
            if (data.pbId === "0") {
                todo.push(raavareList[counter])
            }
            counter++
        }, function (data) {
            alert("Error Getting Bathlines: ERR.NO.20")
            console.log(data)
        })
    }
    localStorage.setItem("todo", todo);

    sendAjax("BoilerPlate_war_exploded/rest/Raavare/getRaavare/" + todo[0], function (data) {
        raavareNavn = data.raavareNavn
    }, function (data) {
        alert("Error Getting raavare: ERR.NO.21")
        console.log(data)
    })

    document.getElementById("content").innerHTML ="<h2>Indtast Tara for Råvare " + raavareNavn + " (ID: " + raavareList[counter] + ")</h2>"
}





