

function confirmRaavareUpdate(id, nummer){
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ nummer +"?")){
            switchP('FarmaScreen/NyRaavare/index.html')
            localStorage.setItem("raavareUpdateID", id);
            $(document).ready(function () {
                $.getJSON("/BoilerPlate_war_exploded/rest/Raavare/getRaavare/"+ localStorage.getItem("raavareUpdateID"), function (data) {
                    document.getElementById("raavareHeader").textContent = "Update Råvare";
                    document.getElementById("confirmbtn").value = "Update";
                    document.getElementById("raavareNummer").value = data.raavareNummer;
                    document.getElementById("raavareNavn").value = data.raavareNavn;
                    document.getElementById("leverandoer").value = data.leverandoer;
                })
            });
        }
        else {
            alert("no worries!");
        }
    });
}

function postRaavareData() {
    const INummer = $("#raavareNummer").val();
    const INavn = $("#raavareNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {raavareNummer: INummer, raavareNavn: INavn, leverandoer: ILeve};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavare/opretRaavare",
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

function postRaavareUpdate() {

    const Iid = localStorage.getItem("raavareUpdateID");
    const INummer = $("#raavareNummer").val();
    const INavn = $("#raavareNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {raavareID: Iid, raavareNummer: INummer, raavareNavn: INavn, leverandoer: ILeve};

    console.log(jsonData);
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavare/updaterRaavare",
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