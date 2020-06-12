
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
            msg += "<td> <button class = hvr-buzz id =" + value.rbId + ">Rediger</button>  </td>";
            return msg
        }
    )
};

