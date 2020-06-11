
$("document").ready(function(){
    updateTable();
    $("#visTomme").click(function () {
        updateTable();
    })

    $("#RVB_table").on("click", "button", function () {
        localStorage.setItem("activeRVBID", this.id);
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
        path = "getAktuelRVBList"

    viewlist(
        ["Batch Nummer", "Råvare ID", "Aktuel mængde", "Oprindelig mængde"],
        "/BoilerPlate_war_exploded/rest/RVB/" + path,
        "RVB_table",
        function (value) {
            let msg = "";
            msg += "<td> <button class = hvr-buzz onclick='confirmRaavarebatchUpdate("+ value.RVBID + ", " + value.RVBNummer +")'>Rediger</button>  </td>";
            return msg
        }
    )

};

