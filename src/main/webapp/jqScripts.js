$.ajaxSetup({async: false}); //this file is for general scripts used all over the site


async function sendAjax(link, successFunc, errorFunc=function (data) {console.log(data);},
                        type="GET", jsonData="None", showbool = true){ /**Brugt til nemt at sende Requests til Backenden gennem Ajax */

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
        complete: () => ((showbool) ? $(".loads").hide() : 0)
    });
}

async function viewlist(headers, link, tableName, btnHtmlfunc) { /**Genererer linjene i en tabel ud fra data hentet fra backenden */
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
            console.log(data)
        })
    );
}

async function sleep(ms){ /**Pauser programmet i ms millisekunder */
    await new Promise(resolve => setTimeout(resolve, ms))
}


function switchP(page) { /**Load html fra path ind i body på yderste index.html */
    $("body").load(page);
}

function error(err) {  /**Error handling: errors fra backenden har en besked i responeText, hvis det ikke er en internal server error (500) */
    const status = err.status;
    if (status != 500) alert(err.responseText);
    else alert("ERROR: Intern serverfejl. Prøv igen")
}