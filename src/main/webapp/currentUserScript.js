/**Når denne JS fil er kaldt i et html dokument, sørge det for at Headeren med IDet "brigerInfo" bliver opdateret med info om hvem der er logget ind
 */
$("document").ready(function () {
    var loginID = localStorage.getItem("loginID");
    $("#brugerInfo").text("Bruger ID: " + loginID);
})

