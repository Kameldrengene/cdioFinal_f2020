function visRecept() {
    $(document).ready(async function () {
        await sendAjax("/BoilerPlate_war_exploded/rest/Recept/getRecepts",function (data) {
            var person_data = '<tr>\n' +
                '                <th>ID</th>\n' +
                '                <th>Navn</th>\n' +
                '            </tr>';
            $.each(data,function (key,value) {
                //console.log(value);
                person_data += '<tr>';
                person_data += '<td>'+value.receptId+'</td>';
                person_data += '<td>'+value.receptNavn+'</td>';                                     //visalle(12,'Ipren'
                person_data += "<td><input id='updateuser' class='hvr-pop' type='button' onclick='visAlle("+value.receptId+", \"" + value.receptNavn +"\")' value='Åben'/> </td>";
                person_data +=  '</tr>';
            });
            $('#recept_table').html(person_data);
        }, function (data) {
            alert("Error: kan ikke hente list")
        });
    });
}

function visAlle(id,navn) {
    $(document).ready(async function () {
        localStorage.setItem("vistID", id);
        localStorage.setItem("vistNavn",navn);
        switchP('FarmaScreen/Recepter/VisRecepter/AabenRecept/Aabenrecepter.html');
        await visBestemtRecepter(id);
    });
}

async function visBestemtRecepter(id) {
    await sendAjax("/BoilerPlate_war_exploded/rest/Recept/getRecepts/"+id,function (data) {
            document.getElementById("receptheader").textContent = "Recept med ID: "+id;
            var person_data = '<tr>\n' +
                '                <th>ReceptID</th>\n' +
                '                <th>Navn</th>\n' +
                '                <th>RåvareID</th>\n' +
                '                <th>RåvareNavn</th>\n' +
                '                <th>nonNetto kg</th>\n' +
                '                <th>Tolerance %</th>\n' +
                '            </tr>';
            var intid = 0;
            $.each(data,function (key,value) {
                //console.log(value);
                var RID = value.receptId;
                var Rname = value.receptNavn;
                var RaaID = value.raavareId;
                var RaaNavn = value.raavarNavn;
                var Rnonnetto = value.nonNetto.toFixed(4);
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
        },
        function (data) {
            alert("Error: kunne ikke hente list");
        });
}

function tilbage() {
    $(document).ready(async function () {
        var receptID = localStorage.getItem("vistID");
        switchP("FarmaScreen/Recepter/VisRecepter/AabenRecept/Aabenrecepter.html");
        await visBestemtRecepter(receptID);
    });
}

function toOpretrecept() {
    switchP('FarmaScreen/Recepter/NyRecept/NyRecept.html');
    $("#loading").hide();
}
