$.ajaxSetup({async: false}); //this file is for general scripts used all over the site


async function sendAjax(link, successFunc, errorFunc=function (data) {console.log(data);}, type="GET", jsonData="None"){

    $(".loads").show()

    await $.ajax({
        url: link,
        type: type,
        async: true,
        contentType: "application/json",
        dataType: "json",
        data: ((jsonData==="None") ? "" : jsonData),
        success: function (data) {successFunc(data)},
        error: function (data) {errorFunc(data)},
        complete: () => $(".loads").hide()
    });
}

async function viewlist(headers, link, tableName, btnHtmlfunc) {
    $(document).ready(
        await sendAjax(link ,function (BEdata) {
            var data = '<tr>\n';
            for (let i = 0; i < headers.length; i++){
                data += '<th>'+ headers[i] +'</th>';
            }
            data += '</tr>';

            $.each(BEdata,function (key,value) {
                data += '<tr>';
                $.each(value, function (key2, inner) {
                    data += '<td>' + inner + '</td>'
                });
                data += btnHtmlfunc(value);
                data +=  '</tr>';
            });
            $('#' + tableName).html(data);
        },
        function (data) {
            alert("Error loading list: ERR.NO.01");
        })
    );
}

async function sleep(ms){
    await new Promise(resolve => setTimeout(resolve, ms))
}


function switchP(page) {
    $("body").load(page);
    $("#addRaavare").hide();
}

async function error(err) {
    const status = err.status;
    if (status != 500) alert(err.responseText);
    else alert("ERROR: Intern serverfejl. Prøv igen")
}