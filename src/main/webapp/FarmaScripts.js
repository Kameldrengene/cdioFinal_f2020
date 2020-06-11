

function confirmOpdaterRV(RVID, RVNummer){
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ RVNummer +"?")){
            switchP('FarmaScreen/NyRaavare/index.html')
            localStorage.setItem("opdaterRVID", RVID);
            $(document).ready(function () {
                $.getJSON("/BoilerPlate_war_exploded/rest/RV/getRV/"+ localStorage.getItem("opdaterRVID"), function (data) {
                    document.getElementById("RVHeader").textContent = "Update Råvare";
                    document.getElementById("confirmbtn").value = "Update";
                    document.getElementById("RVNummer").value = data.RVNummer;
                    document.getElementById("RVNavn").value = data.RVNavn;
                    document.getElementById("leverandoer").value = data.leverandoer;
                })
            });
        }
        else {
            alert("no worries!");
        }
    });
}

function confirmOpdaterRC(RVID, RVNummer){ //TODO Make for RC
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ RVNummer +"?")){
            switchP('FarmaScreen/NyRaavare/index.html')
            localStorage.setItem("opdaterRVID", RVID);
            $(document).ready(function () {
                $.getJSON("/BoilerPlate_war_exploded/rest/RV/getRV/"+ localStorage.getItem("opdaterRVID"), function (data) {
                    document.getElementById("RVHeader").textContent = "Opdater Råvare";
                    document.getElementById("confirmbtn").value = "Opdater";
                    document.getElementById("RVNummer").value = data.RVNummer;
                    document.getElementById("RVNavn").value = data.RVNavn;
                    document.getElementById("leverandoer").value = data.leverandoer;
                })
            });
        }
        else {
            alert("no worries!");
        }
    });
}

function postRVData() {
    const INummer = $("#RVNummer").val();
    const INavn = $("#RVNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {RVNummer: INummer, RVNavn: INavn, leverandoer: ILeve};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/RV/opretRV",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            switchP("FarmaScreen/index.html")
            alert("Succes!")
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsonData));
        }
    });


}

function postRVOpdatering() {

    const Iid = localStorage.getItem("opdaterRVID");
    const INummer = $("#RVNummer").val();
    const INavn = $("#RVNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {raavareID: Iid, RVNummer: INummer, RVNavn: INavn, leverandoer: ILeve};

    console.log(jsonData);
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/RV/opdaterRV",
        type: 'PUT',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            switchP("FarmaScreen/VisRaavarer/index.html")
            alert("Succes!")
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsonData));
        }
    });

}
//postRaavareData() : postRaavareUpdate()