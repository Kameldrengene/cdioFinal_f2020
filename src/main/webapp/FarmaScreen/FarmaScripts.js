

function confirmRaavareUpdate(id){
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ id +"?")){
            switchP('FarmaScreen/VisRaavare/OpdaterRaavare/index.html');
            localStorage.setItem("raavareUpdateID", id);
            $(document).ready(function () {
                $.getJSON("/BoilerPlate_war_exploded/rest/Raavare/getRaavare/"+ localStorage.getItem("raavareUpdateID"), function (data) {
                    document.getElementById("raavareID").innerText = "ID: " + data.raavareID;
                    document.getElementById("raavareNavn").value = data.raavareNavn;
                    document.getElementById("leverandoer").value = data.leverandoer;
                })
            });
        }
    });
}

function raavareData(modType) {
    var APILink = "/BoilerPlate_war_exploded/rest/Raavare/";
    var httpType = "";
    var IID;
    switch (modType) {
        case "Create":
            APILink+="opretRaavare";
            httpType="POST";
            IID = document.getElementById("raavareID").value;
            break;
        case "Update":
            APILink+="updaterRaavare";
            httpType+="PUT";
            IID = localStorage.getItem("raavareUpdateID");
            break;
    }
    const INavn = $("#raavareNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {raavareID: IID, raavareNavn: INavn, leverandoer: ILeve};
    $.ajax({
        url: APILink,
        type: httpType,
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            switchP("FarmaScreen/PLeadScreen.html");
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsonData));
        }
    });
}

function postRaavareData() {
    raavareData("Create");


}

function postRaavareUpdate() {


    raavareData("Update");

}





function visRecept() {
    $(document).ready(function () {
        $.getJSON("/BoilerPlate_war_exploded/rest/Recept/getRecepts",function (data) {
            var person_data = '<tr>\n' +
                '                <th>ID</th>\n' +
                '                <th>Navn</th>\n' +
                '            </tr>';
            $.each(data,function (key,value) {
                //console.log(value);
                person_data += '<tr>';
                person_data += '<td>'+value.receptId+'</td>';
                person_data += '<td>'+value.receptNavn+'</td>';                                     //visalle(12,'Ipren'
                person_data += "<td><input id='updateuser' class='update' type='button' onclick='visAlle("+value.receptId+", \"" + value.receptNavn +"\")' value='Vis Recepter'/> </td>";
                person_data +=  '</tr>';
            });
            $('#recept_table').html(person_data);
        });
    });
}

function visAlle(id,navn) {
    $(document).ready(function () {
        localStorage.setItem("vistID", id);
        localStorage.setItem("vistNavn",navn);
        switchP('FarmaScreen/VisRecept/updaterecepter.html');
        visBestemtRecepter(id);
    });
}


function visBestemtRecepter(id) {

    $.getJSON("/BoilerPlate_war_exploded/rest/Recept/getRecepts/"+id,function (data) {
        console.log("test5");
        document.getElementById("receptheader").textContent = "Recept med ID: "+id;
        var person_data = '<tr>\n' +
            '                <th>ID</th>\n' +
            '                <th>Navn</th>\n' +
            '                <th>RåvareID</th>\n' +
            '                <th>RåvareNavn</th>\n' +
            '                <th>nonNetto</th>\n' +
            '                <th>Tolerance</th>\n' +
            '            </tr>';
        var intid = 0;
        $.each(data,function (key,value) {
            //console.log(value);
            var RID = value.receptId;
            var Rname = value.receptNavn;
            var RaaID = value.raavareId;
            var RaaNavn = value.raavarNavn;
            var Rnonnetto = value.nonNetto;
            var Rtolerance = value.tolerance;
            person_data += '<tr>';
            person_data += '<td>'+RID+'</td>';
            person_data += '<td>'+Rname+'</td>';
            person_data += '<td>'+RaaID+'</td>';
            person_data += '<td>'+RaaNavn+'</td>';
            person_data += '<td id="nonnetto" >'+Rnonnetto+'</td>';
            person_data += '<td id="tolerance" >'+Rtolerance+'</td>';
           // person_data += "<td><input id='updateRecept' class='update' type='button' onclick='confirmReceptupdate("+RaaID+")' value='Rediger'/> </td>";
            person_data +=  '</tr>';
        });
        $('#nyrecept_table').html(person_data);
    });
}



function tilbage() {
    $(document).ready(function () {
        var receptID = localStorage.getItem("vistID");
        switchP("FarmaScreen/VisRecept/updaterecepter.html");
        visBestemtRecepter(receptID);
    });
}

function toOpretrecept() {
    switchP('FarmaScreen/NyRecept/index.html');
    $("#loading").hide();

}


