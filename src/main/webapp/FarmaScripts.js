

function confirmRaavareUpdate(id){
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ id +"?")){
            switchP('FarmaScreen/VisRaavare/OpdaterRaavare/PLeadScreen.html')
            localStorage.setItem("raavareUpdateID", id);
            $(document).ready(function () {
                $.getJSON("/BoilerPlate_war_exploded/rest/Raavare/getRaavare/"+ localStorage.getItem("raavareUpdateID"), function (data) {
                    document.getElementById("raavareID").innerText = "ID: " + data.raavareID;
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
    const IID = document.getElementById("raavareID").value;
    const INavn = $("#raavareNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {raavareID: IID, raavareNavn: INavn, leverandoer: ILeve};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavare/opretRaavare",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            switchP("FarmaScreen/PLeadScreen.html")
            alert("Succes!")
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsonData));
        }
    });


}

function postRaavareUpdate() {

    const IID = localStorage.getItem("raavareUpdateID") ;
    const INavn = $("#raavareNavn").val();
    const ILeve = $("#leverandoer").val();
    const jsonData = {raavareID: IID, raavareNavn: INavn, leverandoer: ILeve};

    console.log(jsonData);
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Raavare/updaterRaavare",
        type: 'PUT',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            switchP("FarmaScreen/VisRaavare/PLeadScreen.html")
            alert("Succes!")
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsonData));
        }
    });

}
function confirmOpretRecept() {
    $(document).ready(function () {
        if (confirm('Er du sikker?')) {
            if(document.getElementById('receptID').value != '' && document.getElementById('receptnavn').value != '' && document.getElementById('raavareID').value != ''){
                opretRecept();
            } else {
                alert('Ikke alt udfyldt')
            }
        }
    });
}

function opretRecept() {

    const RID = document.getElementById("receptID").value;
    const RNavn = $("#receptnavn").val();
    const RaaID = document.getElementById("raavareID").value;
    const Rnetto = $("#nonnetto").val();
    const Rtol = $("#tolerance").val();
    const jsonData = {receptId: RID, receptNavn: RNavn, raavareId: RaaID, nonNetto: Rnetto, tolerance: Rtol};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Recept/opretRecept",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            switchP("FarmaScreen/index.html")
        },
        error: function (jqXHR, text, error) {
            alert(jqXHR + text.status);
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
                person_data += "<td><input id='updateuser' class='update' type='button' onclick='visAlle("+value.receptId+")' value='Vis Recepter'/> </td>";
                person_data +=  '</tr>';
            });
            $('#recept_table').html(person_data);
        });
    });
}

function visAlle(id) {
    $(document).ready(function () {
        localStorage.setItem("vistID", id);
            switchP('FarmaScreen/VisRecept/updaterecepter.html');
            visBestemtRecepter(id);
    });
}


function visBestemtRecepter(id) {

        $.getJSON("/BoilerPlate_war_exploded/rest/Recept/getRecepts/"+id,function (data) {
            document.getElementById("receptheader").textContent = "Recept med ID: "+id;
            var person_data = '<tr>\n' +
                '                <th>ID</th>\n' +
                '                <th>Navn</th>\n' +
                '                <th>RåvareID</th>\n' +
                '                <th>nonNetto</th>\n' +
                '                <th>Tolerance</th>\n' +
                '            </tr>';
            var intid = 0;
            $.each(data,function (key,value) {
                //console.log(value);
                var RID = value.receptId;
                var Rname = value.receptNavn;
                var RaaID = value.raavareId;
                var Rnonnetto = value.nonNetto;
                var Rtolerance = value.tolerance;
                person_data += '<tr>';
                person_data += '<td>'+RID+'</td>';
                person_data += '<td>'+Rname+'</td>';
                person_data += '<td>'+RaaID+'</td>';
                person_data += '<td id="nonnetto" >'+Rnonnetto+'</td>';
                person_data += '<td id="tolerance" >'+Rtolerance+'</td>';
                person_data += "<td><input id='updateRecept' class='update' type='button' onclick='confirmReceptupdate("+RaaID+")' value='Rediger'/> </td>";
                person_data +=  '</tr>';
            });
            $('#nyrecept_table').html(person_data);
        });
}






// function confirmUpdate(id){
//     $(document).ready(function () {
//         if(confirm("ReceptID" + id)){
//             updateRecept(Rid, Rnavn, RaaID, nonNetto, tolerance);
//         }else{
//             alert("no worries!");
//         }
//     });
// }
//
// function updateRecept(Rid, RaaID, nonNetto, tolerance) {
//
//             const jsonData = {
//                 receptId: Rid,
//                 raavareId: RaaID,
//                 nonNetto: nonNetto,
//                 tolerance: tolerance
//             };
//             $.ajax({
//                 url: "/BoilerPlate_war_exploded/rest/Recept/opdaterRecept",
//                 type: 'PUT',
//                 contentType: "application/json",
//                 dataType: 'json',
//                 data: JSON.stringify(jsonData),
//                 success: function (data) {
//                     visBestemtRecepter(Rid);
//                     alert("Succes!");
//                 },
//                 error: function (jqXHR, text, error) {
//                     alert(JSON.stringify(jsonData));
//                 }
//             });
// }

//postRaavareData() : postRaavareUpdate()

function confirmReceptupdate(raavareID) {
    $(document).ready(function () {
        switchP("FarmaScreen/VisRecept/updateRecept.html");
        const ReceptId = localStorage.getItem("vistID");
        const RaavarID = localStorage.setItem("raavarid",raavareID);

        document.getElementById("receptheader").textContent = "Opdater Recept med ReceptID: " + ReceptId + "og RaavareID: " + raavareID;
        $("#ReceptID").html(ReceptId);

        $.getJSON("/BoilerPlate_war_exploded/rest/Recept/getRecept/" + ReceptId + "/" + raavareID, function (data) {
            $("#ReceptNavn").html(data.receptNavn);
            $("#RåvareID").html(data.raavareId);
            $("#nonNetto").html(data.nonNetto);
            $("#Tolerance").html(data.tolerance);
        });
    });
}

function gemAlt(raavareID) {
    $("document").ready(function () {
        switchP("FarmaScreen/VisRecept/updateRecept.html");
        const ReceptId = localStorage.getItem("vistID");
        const RaavarID = localStorage.getItem("raavarid");

        $("#ReceptID").html(ReceptId);

        $.getJSON("/BoilerPlate_war_exploded/rest/Recept/getRecept/" + ReceptId + "/" + RaavarID, function (data) {
            $("#ReceptNavn").html(data.receptNavn);
            $("#RåvareID").html(data.raavareId);
            $("#nonNetto").html(data.nonNetto);
            $("#Tolerance").html(data.tolerance);

        });

        $("#gem").click(function () {
            if(confirm("gem ændringer?")){
                gemopdatering();
            }else{
                alert("ingen problem")
            }
        });

        $("table").keypress(function (e) {
            return e.which != 13;
        });

    });
}

function postReceptUpdate() {
    $(document).ready(function () {
        if (confirm("gem ændringer?")) {
            gemopdatering();
        } else {
            alert("ingen problem")
        }
    });
}

function gemopdatering(){

    const ReceptID = $(".brugertable").find("td").eq(0).text();
    const ReceptNavn = $(".brugertable").find("td").eq(1).text();
    const RaavareID = $(".brugertable").find("td").eq(2).text();
    const nonNetto = $(".brugertable").find("td").eq(3).text();
    const tolerance = $(".brugertable").find("td").eq(4).text();
    const jsonData = {
        receptId: ReceptID,
        receptNavn: ReceptNavn,
        raavareId: RaavareID,
        nonNetto: nonNetto,
        tolerance: tolerance
    };
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Recept/opdaterRecept",
        type: 'PUT',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            tilbage();
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsonData));
        }
    });
}

function tilbage() {
    $(document).ready(function () {
        var receptID = localStorage.getItem("vistID");
        switchP("FarmaScreen/VisRecept/updaterecepter.html");
        visBestemtRecepter(receptID);
    });
}
