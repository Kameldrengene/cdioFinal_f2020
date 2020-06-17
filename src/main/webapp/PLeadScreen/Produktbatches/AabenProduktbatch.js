var batchID;
var receptID;
var status;
var dato;
$("document").ready(function () {

    batchID = localStorage.getItem("activePBId");
    receptID = localStorage.getItem("activeReceptID");
    status = localStorage.getItem("activeStatus");
    dato = localStorage.getItem("activeDato");
    let param = "";
    param += batchID+"-"+receptID;

    $("#batchID").html("Batch ID: " + batchID);
    $("#receptID").html("Recept ID: " + receptID);
    $("#status").html("Status: " + status);
    $("#dato").html("Oprettelses dato: " + dato);

    loadBatch(param);

    $("#produktbatch").on("click", "button", function () {
        localStorage.setItem("activeRBId", this.id);
        switchP("PLeadScreen/Produktbatches/RedigerProduktbatch.html");
    })

});

async function loadBatch(param) {
    sendAjax(
        "/BoilerPlate_war_exploded/rest/Print/getData/" + param,
        function (data) {
        viewTable(data);
    }, function (data) {
        alert("Error loading produktbatches: ERR.NO.11");
        console.log(data);
    })

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
        tabelData += "<tr>";
        tabelData += "<th align='left'>Råvare nr: </th><th align='left'>"+value.raavareID+"</th>";
        tabelData += "</tr>";
        tabelData += "<tr>";
        tabelData += "<th align='left'>Råvare navn: </th><th align='left'>"+value.raavareNavn+"</th>";
        tabelData += "</tr>";
        tabelData += "<tr>";
        tabelData += "<th>Mængde</th><th>Tolerance</th><th width='75px'>Tara</th><th width='75px'>Netto(kg)</th><th width='75px'>Batch</th><th width='75px'>Opr</th>"
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
    });

    tabelData += "<tr>";
    tabelData += "<th align='left'>Sum Tara: </th>"
    tabelData += "</tr>";
    tabelData += "<tr>";
    tabelData += "<th align='left'>Sum Netto: </th>"
    tabelData += "</tr>";
    tabelData += "<tr>";
    tabelData += "<th align='left'>Produktion Slut: </th>"
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

function printDiv(divName) {
    $("#produktbatch").removeClass("brugertable");
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
    $("#produktbatch").addClass("brugertable");
};
