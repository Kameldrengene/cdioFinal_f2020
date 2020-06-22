$.ajaxSetup({async: false});

var alleMap = new Map();  //indeholder alle rååvare med navn som key.


function ledeligeRaavare() {   // gemmer alle råvare i en Map.
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
    });
}

counter = 0;  //bruges til at give at tilføje id's til elementer.
function addLinje() {    //tilføjer ekstra råvare
    if(($("#recepID").val() === null) || ($("#recepnavn").val() === '')){
        alert('udfyld alle felter');
    }else {
        ledeligeRaavare();
        counter++;
        console.log(counter);
        let coutnum = counter;   //indeholder præcise ledeligeNavn + nr=countnum
        console.log(coutnum);
        $("#raavareLinjer").append(
            '<tr id="raavareLines' + counter + '">\n' +
            '        <td id="RåvareID">' +
            '<select name="RaavarID"  id="ledeligeNavn' + counter + '">' +
            '<option value="" disabled selected>Vælg Råvare</option>' +
            '</select>' +
            '</td>\n' +
            '        <td >' +
            '<input type="number" id="netto' + counter + '" placeholder="Mængde 1.001" value="" step="0.001" max="20" min="0.05">' +
            '</td>\n' +
            '        <td >' +
            '<input type="number" id="tol' + counter + '"  placeholder="Tolerance 0.1" value="" step="0.1" max="10" min="0.1"> ' +
            '</td>\n' +
            '<td>\n' +
            '            <button class="hvr-buzz" onclick="fjernRaavar('+coutnum+');" id="Fjern">fjern</button>\n' +
            '        </td>' +
            '    </tr>');
        console.log("test1");
        coutnum = 0;
        console.log(coutnum);
        selectbtn(counter);
    }
}


function fjernRaavar(counter1) {
    $(document).ready(function () {
        $("#raavareLines"+counter1+"").remove();
        console.log(counter);
    });

}

function selectbtn(counter) {    //tilføjer råvare i scroll down list.
    $(document).ready(function () {
        let selectNavn = document.getElementById('ledeligeNavn'+counter+'');
        let ledeliglist = localStorage.getItem("ledeligeRaavarID").split(",");
        let ledeligNavnList = localStorage.getItem("ledeligeRaavarNavn").split(",");
        for (i = 0; i < ledeliglist.length; i++) {
            var option2 = document.createElement('option');
            option2.value = option2.text = ledeligNavnList[i];
            selectNavn.add(option2);
        }
        localStorage.setItem("ledeligeRaavarID", "");
        localStorage.setItem("ledeligeRaavarNavn", "");

        if(counter===1) {
            $("#addRaavare").show();
            $("#confirmbtn").show();
        }
    });
}

function retur() {
    $(document).ready(function () {
        delete counter;
        switchP('FarmaScreen/Farma.html');
    });
}

function confirmOpretRecept() {
    $(document).ready(async function () {
        if (confirm('Er du sikker?')) {
            $("#loading").show();
            console.log("test1");
            if (document.getElementById('recepID').value != '' && document.getElementById('recepnavn').value != '') {
                console.log("test2");
                if(!ingenRaavare()){
                    alert("Tilføje en Råvare");
                } else if(rigtigRaavar()){
                    alert("vælge en Råvare");
                }
                else if (ingenDublicate()) {
                    await opretReceptList();
                } else {
                    alert("må ikke vælge samme råvare flere gang!")
                }
            } else {
                alert('Ikke alt udfyldt');
            }
        }
    });
}


function ingenDublicate() {  //funktion checker for samtlige raavar navn.
    let checkraavar = [];
    for (let i = 1; i <= counter ; i++) {
        if($('#ledeligeNavn'+i+'').length) {
            console.log(i);
            const RaaNavn = document.getElementById('ledeligeNavn' + i + '').value;
            checkraavar.push(RaaNavn);
        }
    }

    console.log(checkraavar);

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

function ingenRaavare() {  //funktion checker for samtlige raavar navn.
    let checkraavarNr = [];
    for (let i = 1; i <= counter ; i++) {
        if($('#ledeligeNavn'+i+'').length) {
            console.log(i);
            const RaaNavn = document.getElementById('ledeligeNavn' + i + '').value;
            checkraavarNr.push(RaaNavn);
        }
    }

    return checkraavarNr.length !== 0;

}

function rigtigRaavar(){
    for (let i = 1; i <= counter ; i++) {
        if($("#ledeligeNavn"+i+"").find(':selected').prop('disabled')){
            return true;
        }
    }
    return false;
}



async function opretReceptList() {
    let ReceptList = [];
    for (let i = 1; i <= counter; i++) {
        if ($('#ledeligeNavn' + i + '').length) {
            const RID = document.getElementById("recepID").value;
            const RNavn = $("#recepnavn").val();
            const RaaNavn = document.getElementById('ledeligeNavn' + i + '').value;
            const RaaID = alleMap.get(RaaNavn);
            //console.log("test3 " + RaaID);
            const Rnetto = $("#netto" + i + "").val();
            // console.log("test4 " + Rnetto);
            const Rtol = $("#tol" + i + "").val();
            //console.log("test4 " + Rtol);
            const ReceptListobj = {
                receptId: RID,
                receptNavn: RNavn,
                raavareId: RaaID,
                nonNetto: Rnetto,
                tolerance: Rtol
            };
            ReceptList.push(ReceptListobj);
            console.log(ReceptList[i - 1]);
        }
    }
    var receptData = JSON.stringify(ReceptList);
    console.log(JSON.stringify(ReceptList));
    console.log("before ajax");
    await sendAjax("/BoilerPlate_war_exploded/rest/Recept/OpretRecept", function (data) {
        console.log("before" + counter);
        alert("Recept oprettet succesfuldt");
        switchP('FarmaScreen/Farma.html');
        console.log("after" + counter);
    }, function (data) {
        if (data.status != 500) {
            alert(data.responseText);
            console.log(counter);
        } else {
            alert('Enternal Error: Prøve igen!');
        }
    },"POST",receptData);
}

