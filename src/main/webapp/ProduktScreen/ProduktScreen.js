$("document").ready(function () {

    const loginRole = localStorage.getItem("loginRole");

    //Load the correct button: "logud" or "tilbage". Depending what role is logged in.
    if(loginRole.localeCompare("prodLeder") != 0){

        //Then return page must be Farma
        const returnPage = "'FarmaScreen/Farma.html'";
        $(".logudEllerTilbage").html('<input id="logoutbtn" type="submit" class="hvr-buzz screenbtn" value="Retur" onclick="switchP(' + returnPage + ')">');
        $(".workAs").hide();

    } else{
        $(".logudEllerTilbage").html('<input id="logoutbtn" type="submit" class="hvr-buzz screenbtn" value="Log ud" onclick="window.location.reload()">')
    }

})