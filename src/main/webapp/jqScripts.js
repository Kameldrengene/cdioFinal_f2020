$.ajaxSetup({async: false}); //this file is for general scripts used all over the site

function sendAjax(link, successFunc, errorFunc=function (data) {console.log(data);}, type="GET"){
    $.ajax({
        url: link,
        type: type,
        async: true,
        contentType: "application/json",
        dataType: "json",
        success: function (data) {successFunc(data)},
        error: function (data) {errorFunc(data)}
    });
}


function viewlist(headers, link, tableName, btnHtmlfunc) {
    $(document).ready(sendAjax(link ,function (BEdata) {
            var data = '<tr>\n';
            for (let i = 0; i < headers.length; i++){
                data += '<th>'+ headers[i] +'</th>';
            }
            data += '</tr>';

            $.each(BEdata,function (key,value) {
                //console.log(value);
                data += '<tr>';
                $.each(value, function (key2, inner) {
                    data += '<td>' + inner + '</td>'
                });
                data += btnHtmlfunc(value);
                data +=  '</tr>';
            });
            $('#' + tableName).html(data);
        })
    );
}

async function sleep(ms){
    await new Promise(resolve => setTimeout(resolve, ms))
}


function switchP(page) {
    $("body").load(page);
}