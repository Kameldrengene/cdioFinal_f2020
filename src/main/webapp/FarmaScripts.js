

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

function ingenDublicate() {
    var checkraavar = [];
    for (let i = 1; i <=counter ; i++) {
        const RaaNavn = document.getElementById('ledeligeNavn'+i+'').value;
        checkraavar.push(RaaNavn);
    }

    for (let i = 0; i < checkraavar.length ; i++) {
        for (let j = i+1; j < checkraavar.length; j++) {
            if(checkraavar[j]!== null){
                if(checkraavar[i]===checkraavar[j]){
                    return false;
                }
            }
        }
    }
    return true;
}


function confirmOpretRecept() {
    $(document).ready(function () {
        if (confirm('Er du sikker?')) {
            console.log("test1");
            if(document.getElementById('recepID').value != '' && document.getElementById('recepnavn').value != ''){
                console.log("test2");
                if(ingenDublicate()){
                    opretRecept();
                }else {
                    alert("må ikke vælge samme råvare flere gang!")
                }
            } else {
                alert('Ikke alt udfyldt');
            }
        }
    });
}

function opretRecept() {

    for (let i = 1; i <= counter ; i++) {
        const RID = document.getElementById("recepID").value;
        const RNavn = $("#recepnavn").val();
        const RaaNavn = document.getElementById('ledeligeNavn'+i+'').value;
        const RaaID = alleMap.get(RaaNavn);
        console.log("test3 "+ RaaID);
        const Rnetto = $("#netto"+i+"").val();
        console.log("test4 "+ Rnetto);
        const Rtol = $("#tol"+i+"").val();
        console.log("test4 "+ Rtol);
        const jsonData = {receptId: RID, receptNavn: RNavn, raavareId: RaaID, nonNetto: Rnetto, tolerance: Rtol};
        console.log(JSON.stringify(jsonData));
        $.ajax({
            url: "/BoilerPlate_war_exploded/rest/Recept/opretRecept",
            type: 'POST',
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify(jsonData),
            success: function (data) {
                if(i===counter){
                    switchP("FarmaScreen/index.html")
                    counter=0;
                }
            },
            error: function (jqXHR, text, error) {
                alert(jqXHR + text.status);
            }
        });
    }
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
            person_data += "<td><input id='updateRecept' class='update' type='button' onclick='confirmReceptupdate("+RaaID+")' value='Rediger'/> </td>";
            person_data +=  '</tr>';
        });
        $('#nyrecept_table').html(person_data);
    });
}


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
                alert("ingen problem");
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
            alert("ingen problem");
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

function tiladdReceptHtml() {
    $(document).ready(function () {
        // console.log("test1");
        switchP("FarmaScreen/NyRecept/addRecept.html");
        // console.log("test2")
        document.getElementById("addReceptmedID").textContent = "Tilføje ny Råvare i Recept med ID: " + localStorage.getItem("vistID");
        //console.log("test3");
        document.getElementById("recID").value = localStorage.getItem("vistID");
        // console.log("test4");
        document.getElementById("recnavn").value = localStorage.getItem("vistNavn");
        // console.log("test5");

    });

}

function listofRaavare() {
    $(document).ready(function () {
        var listraavarenavn = [];
        var listraavare = [];
        let map = new Map();
        var RID = localStorage.getItem("vistID");
        $.getJSON("/BoilerPlate_war_exploded/rest/Recept/getRecepts/"+ RID,function (data) {
            $.each(data,function (key,value) {
                console.log(value);
                var raavarID = value.raavareId;
                var raavarNavn = value.raavareNavn;
                listraavare.push(raavarID);
                listraavarenavn.push(raavarNavn);
                map.set(raavarNavn,raavarID);
            });
        });
        localStorage.setItem("map",map);
        localStorage.setItem("idList" ,listraavare);
        localStorage.setItem("navnList",listraavarenavn);
    });

}
let alleMap = new Map();  //indeholder alle rååvare med navn som key.
function ledeligeRaavare() {
    $(document).ready(function () {
        var alleRaavare = [];
        var alleRaavareNavn = [];

        $.getJSON("/BoilerPlate_war_exploded/rest/Raavare/getRaavarer",function (data) {
            $.each(data,function (key,value) {
                //console.log(value);
                var raavarID = value.raavareID;
                var raavarNavn = value.raavareNavn;

                alleRaavare.push(raavarID);
                alleRaavareNavn.push(raavarNavn);
                alleMap.set(raavarNavn,raavarID);
            });
        });
        localStorage.setItem("ledeligeRaavarID",alleRaavare);
        localStorage.setItem("ledeligeRaavarNavn",alleRaavareNavn);
        localStorage.setItem("ledeligeMap", alleMap);
    });
}

function checkRaavareID() {
    $(document).ready(function () {
        var currentraavarlist = localStorage.getItem("idList").split(",");
        var alleRaavare = localStorage.getItem("ledeligeRaavarID").split(",");
        var currentraavarNavn = localStorage.getItem("navnList").split(",");
        var alleRaavarNavn = localStorage.getItem("ledeligeRaavarNavn").split(",");
        var index = 0;
        for (x = 0; x < currentraavarlist.length; x++){
            for (i = 0; i < alleRaavare.length; i++){
                if(currentraavarlist[x]===alleRaavare[i]){
                    index++;
                    alleRaavare.splice(i,1);
                    alleRaavarNavn.splice(i,1);
                }
            }
        }
        localStorage.setItem("ledeligeRaavarID",alleRaavare);
        localStorage.setItem("ledeligeRaavarNavn",alleRaavarNavn);
        localStorage.setItem("index",index);
    });
}

function getledeligeRaavare() {
    listofRaavare();
    ledeligeRaavare();
    checkRaavareID();
    $(document).ready(function () {
        // var select = document.getElementById('ledeligeID');
        var selectNavn = document.getElementById('ledeligeNavn');
        var ledeliglist = localStorage.getItem("ledeligeRaavarID").split(",");
        var ledeligNavnList = localStorage.getItem("ledeligeRaavarNavn").split(",");
        for (i = 0; i < ledeliglist.length; i++){
            //   var option = document.createElement('option');
            var option2 = document.createElement('option');
            //  option.value = option.text = ledeliglist[i];
            option2.value = option2.text = ledeligNavnList[i];
            //  select.add(option);
            selectNavn.add(option2);
        }
        localStorage.setItem("ledeligeRaavarID","");
        localStorage.setItem("ledeligeRaavarNavn","");
    });
}

function getReceptIDNavn() {
    $(document).ready(function () {
        var RID = document.getElementById("recepID").value;
        var Rnavn = document.getElementById("recepnavn").value;
        var listraavare = [];
        localStorage.setItem("vistID",RID);
        localStorage.setItem("Orecepnavn",Rnavn);
    });
    //listofRaavare();
    ledeligeRaavare();
    //checkRaavareID();

    $(document).ready(function () {
        // var select = document.getElementById('ledeligeID');
        var selectNavn = document.getElementById('ledeligeNavn');
        var ledeliglist = localStorage.getItem("ledeligeRaavarID").split(",");
        var ledeligNavnList = localStorage.getItem("ledeligeRaavarNavn").split(",");
        for (i = 0; i < ledeliglist.length; i++){
            //   var option = document.createElement('option');
            var option2 = document.createElement('option');
            //  option.value = option.text = ledeliglist[i];
            option2.value = option2.text = ledeligNavnList[i];
            //  select.add(option);
            selectNavn.add(option2);
        }
        localStorage.setItem("ledeligeRaavarID","");
        localStorage.setItem("ledeligeRaavarNavn","");
        document.getElementById("recepID").readOnly = true;
        document.getElementById("recepnavn").readOnly = true;
        $("#addRaavare").show();
        $("#videre").hide();
    });


}

function OpretReceptIDNavn() {
    $(document).ready(function () {
        var RID = document.getElementById("recepID").value;
        var Rnavn = document.getElementById("recepnavn").value;
        localStorage.setItem("vistID",RID);
        localStorage.setItem("Orecepnavn",Rnavn);
    });

    listofRaavare();
    ledeligeRaavare();
    checkRaavareID();

    $(document).ready(function () {
        // var select = document.getElementById('ledeligeID');
        var selectNavn = document.getElementById('ledeligeNavn');
        var ledeliglist = localStorage.getItem("ledeligeRaavarID").split(",");
        var ledeligNavnList = localStorage.getItem("ledeligeRaavarNavn").split(",");
        for (i = 0; i < ledeliglist.length; i++){
            //   var option = document.createElement('option');
            var option2 = document.createElement('option');
            //  option.value = option.text = ledeliglist[i];
            option2.value = option2.text = ledeligNavnList[i];
            //  select.add(option);
            selectNavn.add(option2);
        }
        localStorage.setItem("ledeligeRaavarID","");
        localStorage.setItem("ledeligeRaavarNavn","");
        document.getElementById("recepID").readOnly = true;
        document.getElementById("recepnavn").readOnly = true;
        $("#addRaavare").show();
        //   $("#videre").hide();
    });


}
var counter = 0;  //bruges til at give at tilføje id's til elementer.
function addLinje() {
    //  listofRaavare();
    ledeligeRaavare();
    // checkRaavareID();
    counter++;
    console.log(counter);
    document.getElementById("raavareLinjer" + counter+"").innerHTML +=
        '<div id="raavareLinjer'+counter+'">'+
        '<select name="RaavarID"  id="ledeligeNavn'+counter+'">' +
        '<option value="" disabled selected>Vælg Råvare</option>' +
        '</select><br>' +
        '<input type="number" id="netto'+counter+'" placeholder="Mængde 1.0001" value="" step="0.0001" max="20" min="0.05"><br>' +
        '<input type="number" id="tol'+counter+'"  placeholder="Tolerance 0.1" value="" step="0.01" max="10" min="0.1"><br> '+
        '</div>' +
        '<div id="raavareLinjer'+(counter + 1)+'"></div>'
    ;
    selectbtn(counter);

}

function selectbtn(counter) {
    $(document).ready(function () {
        // var select = document.getElementById('ledeligeID');
        var selectNavn = document.getElementById('ledeligeNavn'+counter+'');
        var ledeliglist = localStorage.getItem("ledeligeRaavarID").split(",");
        var ledeligNavnList = localStorage.getItem("ledeligeRaavarNavn").split(",");
        for (i = 0; i < ledeliglist.length; i++) {
            //   var option = document.createElement('option');
            var option2 = document.createElement('option');
            //  option.value = option.text = ledeliglist[i];
            option2.value = option2.text = ledeligNavnList[i];
            //  select.add(option);
            selectNavn.add(option2);
        }
        localStorage.setItem("ledeligeRaavarID", "");
        localStorage.setItem("ledeligeRaavarNavn", "");
        document.getElementById("recepID").readOnly = true;
        document.getElementById("recepnavn").readOnly = true;

        if(counter===1) {
            $("#addRaavare").show();
        }

        //   $("#videre").hide();
    });
}


function confirmaddRecept() {
    $(document).ready(function () {
        if (confirm('Er du sikker?')) {
            console.log("test1");
            if(document.getElementById('recID').value != '' && document.getElementById('recnavn').value != '' && document.getElementById('ledeligeNavn').value != ''){
                console.log("test2");
                addRecept();
            } else {
                alert('Ikke alt udfyldt');
            }
        }
    });
}

function addRecept() {
    //  const raaID = localStorage.getItem("ledeligeMap");
    const RID = document.getElementById("recepID").value;
    const RNavn = document.getElementById("recepnavn").value;
    console.log("test3");
    const RaaNavn = document.getElementById("ledeligeNavn").value;
    const raID = alleMap.get(RaaNavn);
    console.log(raID);
    const Rnetto = $("#netto").val();
    const Rtol = $("#tol").val();
    const jsonData = {receptId: RID, receptNavn: RNavn, raavareId: raID, nonNetto: Rnetto, tolerance: Rtol};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/Recept/opretRecept",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            tilbage();
        },
        error: function (jqXHR, text, error) {
            alert(jqXHR + text.status);
        }
    });
}






$("document").ready(function(){
    updateTable();
    $("#visAfsluttede").click(function () {
        updateTable();
    })
    $("#produktbatches").on("click", "button", function () {
        localStorage.setItem("activePBId", this.id);
        //Get content of table
        const currentRow = $(this).closest("tr");
        const receptID = currentRow.find("td:eq(1)").text();
        const status = currentRow.find("td:eq(2)").text();
        //Save content
        localStorage.setItem("activeStatus", status);
        localStorage.setItem("activeReceptID", receptID);
        switchP("PLeadScreen/Produktbatches/AabenProduktbatch.html");
    })
});
function updateTable(){
    let path;
    if($("#visAfsluttede").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/" + path, function (data) {
        viewTable(data)
    }, function (data) {
        alert("Error getting all/actual produktbatches: ERR.NO.16");
        console.log(data);
    })
};
function viewTable(data){
    //Variable to hold all the tabel rows
    let tabelData = "";
    tabelData += "<tr>";
    tabelData += "<th>Batch ID</th>";
    tabelData += "<th>Recept ID</th>";
    tabelData += "<th>Status</th>";
    tabelData += "</tr>";
    //Loop through
    $.each(data, function (key, value) {
        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.pbId+"</td>";
        tabelData += "<td>"+value.receptId+"</td>";
        tabelData += "<td>"+value.status+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.pbId + " >Åben</button>  </td>"
        tabelData += "</tr>";
    });
    $("#produktbatches").html(tabelData);
}
