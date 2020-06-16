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
    $(document).ready(function () {
        switchP('LabScreen/index.html')
        print("hej")
    })
}