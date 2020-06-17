var alleMap = new Map();  //indeholder alle rååvare med navn som key.
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
    });
}

let counter = 0;  //bruges til at give at tilføje id's til elementer.
function addLinje() {
    if(($("#recepID").val() === null) || ($("#recepnavn").val() === '')){
        alert('udfyld alle felter');
    }else {
        ledeligeRaavare();
        counter++;
        console.log(counter);
        let coutnum = counter;
        console.log(coutnum);
        $("#raavareLinjer").append(
            '<tr id="raavareLines' + counter + '">\n' +
            '        <td id="RåvareID">' +
            '<select name="RaavarID"  id="ledeligeNavn' + counter + '">' +
            '<option value="" disabled selected>Vælg Råvare</option>' +
            '</select>' +
            '</td>\n' +
            '        <td >' +
            '<input type="number" id="netto' + counter + '" placeholder="Mængde 1.0001" value="" step="0.0001" max="20" min="0.05">' +
            '</td>\n' +
            '        <td >' +
            '<input type="number" id="tol' + counter + '"  placeholder="Tolerance 0.1" value="" step="0.01" max="10" min="0.1"> ' +
            '</td>\n' +
            '<td>\n' +
            '            <button class="hvr-buzz" onclick="fjernRaavar('+coutnum+');" id="fjern">fjern</button>\n' +
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
        //  document.getElementById("raavareLinjer"+counter+"").innerHTML +=
        // '<div id="raavareLinjer'+(counter + 1)+'"></div>'
    });

}

function selectbtn(counter) {
    $(document).ready(function () {
        // var select = document.getElementById('ledeligeID');
        let selectNavn = document.getElementById('ledeligeNavn'+counter+'');
        let ledeliglist = localStorage.getItem("ledeligeRaavarID").split(",");
        let ledeligNavnList = localStorage.getItem("ledeligeRaavarNavn").split(",");
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
        // document.getElementById("recepID").readOnly = true;
        // document.getElementById("recepnavn").readOnly = true;

        if(counter===1) {
            $("#addRaavare").show();
        }

        //   $("#videre").hide();
    });
}

function retur() {
    $(document).ready(function () {
        counter = 0;
        switchP('FarmaScreen/index.html');
    });
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
    let errorID = false;
    for (let i = 1; i <= counter ; i++) {
        if (errorID) {
            i = counter + 1
        } else {
            if ($('#ledeligeNavn' + i + '').length) {
                const RID = document.getElementById("recepID").value;
                const RNavn = $("#recepnavn").val();
                const RaaNavn = document.getElementById('ledeligeNavn' + i + '').value;
                const RaaID = alleMap.get(RaaNavn);
                console.log("test3 " + RaaID);
                const Rnetto = $("#netto" + i + "").val();
                console.log("test4 " + Rnetto);
                const Rtol = $("#tol" + i + "").val();
                console.log("test4 " + Rtol);
                const jsonData = {
                    receptId: RID,
                    receptNavn: RNavn,
                    raavareId: RaaID,
                    nonNetto: Rnetto,
                    tolerance: Rtol
                };
                console.log(JSON.stringify(jsonData));
                $.ajax({
                    url: "/BoilerPlate_war_exploded/rest/Recept/opretRecept/" + (i - 1)+"",
                    type: 'POST',
                    contentType: "application/json",
                    dataType: 'json',
                    data: JSON.stringify(jsonData),
                    success: function (data) {
                        console.log(i);
                        console.log(counter);
                        if (i === counter) {
                            console.log(counter);
                            console.log(i);
                            retur();
                            console.log(counter);
                            console.log(i);
                        }
                    },
                    error: function (data) {
                        if (data.status === 406){
                            errorID = true;
                            alert(data.responseText);
                            console.log(counter);
                        }else {
                            i = i - 1;
                            alert('External Error: sendes igen');
                        }
                    }
                });
            }
        }
    }
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
