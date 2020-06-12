$("document").ready(function () {

    const batchID = localStorage.getItem("activeRBId");

    $("#batchID").html(batchID);

    $.getJSON("/BoilerPlate_war_exploded/rest/Raavarebatch/getBatch/" + batchID, function(data) {
        $("#raavareID").html(data.raavareId);
        $("#actualAmount").html(data.aktuelMaengde);
        $("#oriAmount").html(data.startMaengde);

    })

    $("#gem").click(function () {

        const newAmount = $(".brugertable").find("td").eq(0).text();
        console.log(newAmount);

    })

    $("table").keypress(function(e){
        const newAmount = $(".brugertable").find("td").eq(0).text();
        console.log(newAmount);
        return e.which != 13;
    });


});

// function save(){
//     const newAmount = $(".brugertable").find("td").eq(0).text();
// }