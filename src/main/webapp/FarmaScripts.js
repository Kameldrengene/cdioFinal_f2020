

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
                person_data += '<td>'+value.receptNavn+'</td>';
                person_data += "<td><input id='updateuser' class='update' type='button'  value='Update'/> </td>";
                person_data += "<td><input id='deleteuser' class='slet' type='button' onclick='visAlle("+value.receptId+")' value='vis'/> </td>";
                person_data +=  '</tr>';
            });
            $('#recept_table').html(person_data);
        });
    });
}

function visAlle(id) {
    localStorage.setItem("vistID", id);
    visBestemtRecepter(id);
}


function visBestemtRecepter(id) {
    $(document).ready(function () {
        switchP('FarmaScreen/VisRecept/recept.html');
        $.getJSON("/BoilerPlate_war_exploded/rest/Recept/getRecept/"+id,function (data) {
            document.getElementById("receptheader").textContent = "Vis Recept med ID: "+id;
            var person_data = '<tr>\n' +
                '                <th>ID</th>\n' +
                '                <th>Navn</th>\n' +
                '                <th>RåvareID</th>\n' +
                '                <th>nonNetto</th>\n' +
                '                <th>Tolerance</th>\n' +
                '            </tr>';
            $.each(data,function (key,value) {
                //console.log(value);
                person_data += '<tr>';
                person_data += '<td>'+value.receptId+'</td>';
                person_data += '<td>'+value.receptNavn+'</td>';
                person_data += '<td>'+value.raavareId+'</td>';
                person_data += '<td contenteditable="true">'+value.nonNetto+'</td>';
                person_data += '<td contenteditable="true">'+value.tolerance+'</td>';
                person_data += "<td><input id='updateuser' class='update' type='button'  value='Update'/> </td>";
                person_data +=  '</tr>';
            });
            $('#nyrecept_table').html(person_data);
        });
    });

}

function updateRecept(Rid, Rnavn, RaaID, nonNetto, tolerance) {

    const jsonData = {receptId: Rid, receptNavn: Rnavn, raavareId:RaaID, nonNetto: nonNetto, tolerance: tolerance};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavare/opretRaavare",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
           visBestemtRecepter(Rid);
            alert("Succes!");
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsonData));
        }
    });
}

//postRaavareData() : postRaavareUpdate()