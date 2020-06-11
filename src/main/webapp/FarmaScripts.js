

function confirmRaavareUpdate(id){
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ id +"?")){
            switchP('FarmaScreen/NyRaavare/index.html')
            localStorage.setItem("raavareUpdateID", id);
            $(document).ready(function () {
                $.getJSON("/BoilerPlate_war_exploded/rest/user/getUser/" + updatedID, function (data) {

                    $("#raavareHeader").innerText = "Update Råvare";
                    $("#raavareNummer").val = data.raavareNummer;
                    $("#raavareNavn").val = data.raavareNavn;
                    $("#leverandoer").val = data.leverandoer;
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
    const ILeve = $("#Leverandoer").val();
    const jsonData = {raavareNummer: INummer, raavareNavn: INavn, leverandoer: ILeve};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavare/createRaavare",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsondata),
        success: function (data) {
            switchP("FarmaScreen/index.html")
            alert("Succes!")
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsondata));
        }
    });


}

function postRaavareUpdate() {

    const Iid = localStorage.getItem("raavareUpdateID");
    const INummer = $("#raavareNummer").val();
    const INavn = $("#raavareNavn").val();
    const ILeve = $("#Leverandoer").val();
    const jsonData = {raavareID: Iid, raavareNummer: INummer, raavareNavn: INavn, leverandoer: ILeve};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavare/updateRaavare",
        type: 'PUT',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsondata),
        success: function (data) {
            switchP("FarmaScreen/index.html")
            alert("Succes!")
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsondata));
        }
    });

}
//postRaavareData() : postRaavareUpdate()