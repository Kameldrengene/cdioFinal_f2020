var batchID;
var receptID;
var status;
var dato;
var sumTara;
var sumNetto;
var lastDato;

$("document").ready(async function () {

    const cameFrom = localStorage.getItem("cameFrom");
    let returnPage;

    //Set the correct return page
    if(cameFrom.localeCompare("OpretProduktbatch") != 0)
        returnPage = "'ProduktScreen/Produktbatches/VisProduktbatches/VisProduktbatches.html'";
    else
        returnPage = "'ProduktScreen/Produktbatches/OpretProduktbatch/OpretProduktbatch.html'";

    $(".logudEllerTilbage").html('<input id="retur" type="submit" class="hvr-pop" value="Retur" onclick="switchP('+returnPage+'); return false">');

    batchID = localStorage.getItem("activePBId");
    receptID = localStorage.getItem("activeReceptID");
    status = localStorage.getItem("activeStatus");
    dato = localStorage.getItem("activeDato");

    let param = "";
    param += batchID;

    $("#batchID").html("Batch ID: " + batchID);
    $("#receptID").html("Recept ID: " + receptID);
    $("#status").html("Status: " + status);
    $("#dato").html("Oprettelses dato: " + dato);

    await loadBatch(param);

    $(".logudEllerTilbage").click(function () {
        localStorage.setItem("cameFrom", "AabenProduktbatch")
    })

});

async function loadBatch(param) {

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Print/getData/" + param,
        data => viewTable(data),
        err => error(err)
    )
}

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    tabelData += "<tr>";
    tabelData += "<th align='left'>Produktion tilstand: </th><th align='left'>"+status+"</th>";
    tabelData += "</tr>";
    tabelData += "<tr>";
    tabelData += "<th align='left'>Udskrevet: </th><th align='left'>"+dato+"</th>";
    tabelData += "</tr>";
    tabelData += "<tr>";
    tabelData += "<th align='left'>Produkt Batch nr: </th><th align='left'>"+batchID+"</th>";
    tabelData += "</tr>";
    tabelData += "<tr>";
    tabelData += "<th align='left'>Recept nr: </th><th align='left'>"+receptID+"</th>";
    tabelData += "</tr>";

    tabelData += "<tr>";
    tabelData += "<td> &nbsp; </td>";
    tabelData += "</tr>";
    tabelData += "<tr>";
    tabelData += "<td> &nbsp; </td>";
    tabelData += "</tr>";


    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData+= "<tr>";
        tabelData+= "<th style='border-bottom: solid 1px black'></th><th style='border-bottom: solid 1px black'></th><th style='border-bottom: solid 1px black'></th><th style='border-bottom: solid 1px black'></th><th style='border-bottom: solid 1px black'></th><th style='border-bottom: solid 1px black'></th>";
        tabelData+= "</tr>";
        tabelData += "<tr>";
        tabelData += "<th align='left'>Råvare nr: </th><th align='left'>"+value.raavareID+"</th>";
        tabelData += "</tr>";
        tabelData += "<tr>";
        tabelData += "<th align='left'>Råvare navn: </th><th align='left'>"+value.raavareNavn+"</th>";
        tabelData += "</tr>";
        tabelData += "<tr>";
        tabelData += "<th>Mængde[kg]</th><th>Tolerance[%]</th><th width='75px'>Tara[kg]</th><th width='75px'>Netto[kg]</th><th width='75px'>Batch</th><th width='75px'>Opr</th>"
        tabelData += "</tr>";
        tabelData += "<tr>";
        tabelData += "<td>"+value.nonNetto+"</td>"+"<td>"+value.tolerance+"</td>"+"<td>"+sniff(value.tara)+"</td>"+"<td>"+sniff(value.netto)+"</td>"+"<td>"+sniff(value.rbID)+"</td>"+"<td>"+sniff(value.userId)+"</td>" ;
        tabelData += "</tr>";

        tabelData += "<tr>";
        tabelData += "<td> &nbsp; </td>";
        tabelData += "</tr>";


        tabelData += "<tr>";
        tabelData += "<td> &nbsp; </td>";
        tabelData += "</tr>";
        sumNetto = value.sumNetto;
        sumTara = value.sumTara;
        lastDato = value.dato;
    });

    tabelData += "<tr>";
    tabelData += "<th align='left'>Sum Tara[kg]: </th>"+"<th align='left'>"+sniff(sumTara)+"</th>";
    tabelData += "</tr>";
    tabelData += "<tr>";
    tabelData += "<th align='left'>Sum Netto[kg]: </th>"+"<th align='left'>"+sniff(sumNetto)+"</th>";
    tabelData += "</tr>";
    tabelData += "<tr>";
    tabelData += "<th align='left'>Produktion Slut: </th>"+"<th align='left'>"+slutDato(status,lastDato)+"</th>"
    tabelData += "</tr>";

    $("#produktbatch").html(tabelData);

}

function sniff(value) {
    let temp = "";
    if(value==0){
        return temp;
    }
    else
        return value;
}
function slutDato(standing,dato){
    if(standing=="Afsluttet"){
        return dato;
    }
    return "";
}

function printDiv(divName) {
    $("#produktbatch").removeClass("brugertable");
    let printContents = document.getElementById(divName).innerHTML;
    let originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
    $("#produktbatch").addClass("brugertable");
};
