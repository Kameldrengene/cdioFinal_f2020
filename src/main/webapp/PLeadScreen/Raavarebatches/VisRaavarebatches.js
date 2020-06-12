
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

function confirmRaavarebatchUpdate(id, nummer) {
    if (confirm("Er du sikker på at redigere Råvarebatch nummer " + nummer + "?")) {
        switchP("PLeadScreen/RaavareBatches/RedigerRaavarebatches.html");
    } else {
        alert("Returnerer")
    }
}

function updateTable(){

    let path;

    if($("#visTomme").is(":checked"))
        path = "getRVBList";
    else
        path = "getaktuelRVBList"

    viewlist(
        ["Batch Nummer", "Råvare ID", "Aktuel mængde", "Oprindelig mængde"],
        "/BoilerPlate_war_exploded/rest/RVB/" + path,
        "raavarebatches",
        function (valuea) {
            let msg = "";
            msg += "<td> <button class = hvr-buzz onclick='confirmRaavarebatchUpdate("+ valuea.rbId + ", " + valuea.rbNummer +")'>Rediger</button>  </td>";
            return msg
        }
    )

};

