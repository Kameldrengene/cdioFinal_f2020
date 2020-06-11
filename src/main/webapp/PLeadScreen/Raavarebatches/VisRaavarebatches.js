
$("document").ready(function(){
    updateTable();
    $("#visTomme").click(function () {
        updateTable();
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
            const id = value.rBID;
            let msg = "";
            msg += "<td> <button class = hvr-buzz>Halo</button>  </td>";
            return msg
        }
    )

};

