$.ajaxSetup({async: false});

function showPBList() {
    console.log("test1")
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
        var RID = data.receptId;
        if (RID == undefined) {
            RID = data[0].receptId;
        }
        sendAjax("/BoilerPlate_war_exploded/rest/Recept/getRecept/" + RID, function (data) {
            var RNavn = data.receptNavn;
            if (RNavn == undefined) {
                RNavn = data[0].receptNavn;
            }
            if(confirm("Arbejd med Produktbatch " + id + " (" + RNavn + ")")){
                switchP("LabScreen/ProcesserProduktbatch/index.html");
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






