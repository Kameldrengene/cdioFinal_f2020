
$("document").ready(function(){

    updateTable();
    console.log("her");

    $("#visTomme").click(function () {
        updateTable();
    })

});

function switchPage(id, nummer) {

    localStorage.setItem("activeRBId", id);
    switchP('PLeadScreen/Raavarebatches/RedigerRaavarebatches.html');

}

function updateTable(){

    let path;

    if($("#visTomme").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    viewlist(
        ["Batch Nummer", "Råvare ID", "Aktuel mængde", "Oprindelig mængde"],
        "/BoilerPlate_war_exploded/rest/Raavarebatch/" + path,
        "raavarebatches",
        function (value) {
            let msg = "";
            let parameters = value.rbId + ", " + value.rbNummer;
            msg += "<td> <button class = hvr-buzz onclick='switchPage("+ parameters +")'>Rediger</button>  </td>";
            return msg;
        }
    )

};

