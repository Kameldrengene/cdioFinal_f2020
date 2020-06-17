$("document").ready(async function () {

    await loadRecepter();

    $("#receptTable").on("click", "button", function () {
        console.log("Opret: " + this.id);
        opretProduktbatch(this.id);
    })

});

async function loadRecepter() {

    //Hide table and display loader while updating
    $("#receptTable").hide();
    $("#loading").show();

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Recept/getRecepts",
        function (data) {
            viewTable(data)
        },
        function (data) {
            alert("Error getting all/actual recept: ERR.NO.XX");
            console.log(data);
        }
    );

    //Remove loader and reveal table
    $("#loading").hide();
    $("#receptTable").show();

}

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    tabelData += "<tr>";
    tabelData += "<th>Recept ID</th>";
    tabelData += "<th>Receptnavn</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.receptId+"</td>";
        tabelData += "<td>"+value.receptNavn+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.receptId + " >Opret</button>  </td>";
        tabelData += "</tr>";

    });

    $("#receptTable").html(tabelData);

}

async function opretProduktbatch(activeReceptID){

    const activeBatchID =  "18";

    var obj = { pbId: activeBatchID, status: "Ikke påbegyndt", receptId: activeReceptID, dato: "" };
    var myJson = JSON.stringify(obj);
    console.log(myJson)

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch",
        function(data) {
        alert("Produktbatch oprettet succesfuldt");
        $("#gem").removeAttr("hover");
    },
        function (data) {
        alert("Error Creating produktbatch: ERR.NO.12");
        console.log(data);
    },
        "POST",
        myJson);

}











// $("document").ready(function () {
//     showPBList();
//
//     let activeReceptID;
//
//     $("#receptValg").change(function () {
//         activeReceptID = this.value.split(":")[0];
//     })
//
//     $("#opret").click(function () {
//
//         const activeBatchID = $('#batchID').val();
//
//         var obj = { pbId: activeBatchID, status: "Ikke påbegyndt", receptId: activeReceptID, dato: "" };
//         var myJson = JSON.stringify(obj);
//         console.log(myJson);
//
//         sendAjax(
//             "/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch",
//             function(data) {
//             alert("Produktbatch oprettet succesfuldt");
//             $("#gem").removeAttr("hover");
//         },
//             function (data) {
//             alert("Error Creating produktbatch: ERR.NO.12");
//             console.log(data);
//         },
//             "POST",
//             myJson);
//     })
//
// })
//
//
// async function showPBList() {
//
//     await sendAjax(
//         "/BoilerPlate_war_exploded/rest/Recept/getRecepts",
//         data => AddToDropdown(data),
//         function (data) {
//             alert("Error getting actual pb for laborant: ERR.NO.17");
//     })
// }
//
// function AddToDropdown(data){
//
//     let counter = 0;
//
//     var recepts = "";
//     $.each(data, function (key, value) {
//         recepts += "<option id='valg_" + counter++ + "'>" + value.receptId + ": " + value.receptNavn + "</option>"
//     })
//
//     $("#receptValg").append(recepts);
//
// }
//
//
//
//
//
//
//
//
//
//
//
//
