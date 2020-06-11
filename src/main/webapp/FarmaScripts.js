

function confirmRaavareUpdate(id){
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ id +"?")){
            switchP('FarmaScreen/VisRaavare/UpdateRaavare/index.html')
            updatedID = id;
        }
        else {
            alert("no worries!");
        }
    });
}
