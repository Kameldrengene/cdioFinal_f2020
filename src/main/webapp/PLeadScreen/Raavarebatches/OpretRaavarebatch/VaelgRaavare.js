$("document").ready(function () {

    loadRaavarer();

    $("#raavareTable").on("click", "button", function () {
        localStorage.setItem("activeRaavare", this.id);
        switchP("PLeadScreen/Raavarebatches/OpretRaavarebatch/AngivData.html")
    })


})

async function loadRaavarer(path) {

    await $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavare/getRaavarer",
        type: "GET",
        async: true,
        contentType: "application/json",
        dataType: "json",
        success: data => viewTable(data)
    });

}

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    tabelData += "<tr>";
    tabelData += "<th>Råvare ID</th>";
    tabelData += "<th>Råvarenavn</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.raavareID+"</td>";
        tabelData += "<td>"+value.raavareNavn+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.raavareID + " >Vælg</button>  </td>"
        tabelData += "</tr>";

    });

    $("#raavareTable").html(tabelData);

}
