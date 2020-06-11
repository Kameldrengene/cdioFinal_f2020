$("document").ready(function () {

    const activeRVBID = localStorage.getItem("activeRVBID");

    $("#RVBID").html(activeRVBID);

    $.getJSON("/BoilerPlate_war_exploded/rest/RVB/getRVB/" + activeRVBID,function (data) {
        $("#RVID").html(data.RVBID);
        $("#actualAmount").html(
            "<form> " +
            "<input type='text' id='newAmount' value=" + data.aktuelMaengde + ">" +
            "</form>"

        );
        $("#oriAmount").html(data.maengde);

    })



})