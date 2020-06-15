$("document").ready(function(){

    updateTable();

    $("#visAfsluttede").click(function () {
        updateTable();
    })

    $("#produktbatches").on("click", "button", function () {
        localStorage.setItem("activePBId", this.id);
        localStorage.setItem("activeRBId", this.name);
        switchP("PLeadScreen/Produktbatches/RedigerProduktbatches.html");
    })

});

function updateTable(){

    let path;

    if($("#visAfsluttede").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    viewlist(
        ["Batch ID", "Staus", "Recept ID", "Bruger ID", "Råvarebatch ID", "Tara", "Netto"],
        "/BoilerPlate_war_exploded/rest/produktbatch/" + path,
        "produktbatches",
        function (value) {
            let msg = "";
            msg += "<td> <button class = hvr-buzz name=" + value.rbID + " id =" + value.pbId + " >Rediger</button>  </td>";
            return msg
        }
    )
};
