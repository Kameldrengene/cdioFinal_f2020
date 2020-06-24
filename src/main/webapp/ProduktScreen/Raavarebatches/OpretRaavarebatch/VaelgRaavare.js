//Created by Mikkel

$("document").ready(async function () {

    await loadRaavarer();

    //Save choosen ID and change page
    $("#raavareTable").on("click", "button", function () {
        localStorage.setItem("activeRaavare", this.id);
        switchP("ProduktScreen/Raavarebatches/OpretRaavarebatch/AngivData.html")
    })

});

async function loadRaavarer() {

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Raavare/getRaavarer",
        data => viewTable(data),
        err => error(err)
    );

}

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    //Header
    tabelData += "<tr>";
    tabelData += "<th>Råvare ID</th>";
    tabelData += "<th>Råvarenavn</th>";
    tabelData += "</tr>";

    //Loop through data and add dynamically
    $.each(data, function (key, value) {

        //Uses raavareID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.raavareID+"</td>";
        tabelData += "<td>"+value.raavareNavn+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.raavareID + " >Vælg</button>  </td>";
        tabelData += "</tr>";

    });

    //Fill table
    $("#raavareTable").html(tabelData);

}
