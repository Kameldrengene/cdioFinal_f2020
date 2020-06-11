

function confirmRaavareUpdate(id){
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ id +"?")){
            switchP('FarmaScreen/VisRaavare/UpdateRaavare/index.html')
            localStorage.setItem("raavareUpdateID", id);
        }
        else {
            alert("no worries!");
        }
    });
}

function postRaavareData() {
    const UPnavn = $("#raavareNavn").val();
    const UPleve = $("#Leverandoer").val();

}

function postRaavareUpdate() {

    const UPid = localStorage.getItem("");
    const UPnavn = $("#raavareNavn").val();
    const UPleve = $("#Leverandoer").val();

}
//postRaavareData() : postRaavareUpdate()