
$("document").ready(function(){

    updateTable();

    $("#visTomme").click(function () {
        updateTable();
    })

    $("#raavarebatches").on("click", "button", function () {
        localStorage.setItem("activeRBId", this.id);
        switchP("PLeadScreen/Raavarebatches/RedigerRaavarebatches.html");
    })

});

function confirmRaavarebatchUpdate(id) {
    if (confirm("Er du sikker på at redigere Råvarebatch nummer " + id + "?")) {
        switchP("PLeadScreen/RaavareBatches/RedigerRaavarebatches.html");
    } else {
        alert("Returnerer")
    }
}


function updateTable(){

    let path;

    if($("#visTomme").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    viewlist(
        ["Batch ID", "Råvare ID", "Aktuel mængde", "Oprindelig mængde"],
        "/BoilerPlate_war_exploded/rest/Raavarebatch/" + path,
        "raavarebatches",
        function (value) {
            let msg = "";
            msg += "<td> <button class = hvr-buzz onclick='confirmRaavarebatchUpdate("+ value.rbId + ")'>Rediger</button>  </td>";

            return msg
        }
    )
};

