async function deleteUser(ID) {
    console.log("Delete user:" + ID);
    if(confirm("Do you sure you want to delete user: "+ID+"?")){
        await fetch("/BoilerPlate_war_exploded/rest/live/mysql_json/deleteUser/"+ID);
        loadUserTable();
    }
}

async function updateUser(ID) {
    localStorage.setItem("userID",ID);
    location.href = "updatebruger.html";
}


function create_attributes() {
    const tr = document.createElement("tr");
    tr.appendChild(createTD("Bruger ID"));
    tr.appendChild(createTD("Brugernavn"));
    tr.appendChild(createTD("Initialer"));
    tr.appendChild(createTD("CPR-Nummer"));
    tr.appendChild(createTD("Password"));
    tr.appendChild(createTD("Roller"));

    return tr;
}

function create_row(user) {
    const tr = document.createElement("tr");

    tr.appendChild(createCell(user.userID));
    tr.appendChild(createCell(user.userName));
    tr.appendChild(createCell(user.ini));
    tr.appendChild(createCell(user.cpr));
    tr.appendChild(createCell(user.password));
    tr.appendChild(createCell(user.rolesToString));

    const removeButton = document.createElement("input");
    removeButton.id = "deleteuser";
    removeButton.className = "slet";
    removeButton.type = "button";
    removeButton.value = "slet";
    removeButton.addEventListener("click", () => deleteUser(user.userID), false);

    const updateButton = document.createElement("input");
    updateButton.id = "updateuser";
    updateButton.className = "update";
    updateButton.type = "button";
    updateButton.value = "update";
    updateButton.addEventListener("click", () => updateUser(user.userID), false);

    tr.appendChild(createCell(updateButton));
    tr.appendChild(createCell(removeButton));

    return tr;
}

/**
 * @param {string|Node} content
 * @return {!HTMLTableDataCellElement}
 */
function createCell(content) {
    const td = document.createElement("td");
    td.append(content);

    return td;
}

function createTD(content) {
    const th = document.createElement("th");
    th.append(content);

    return th;
}

loadUserTable();

setInterval(loadUserTable, 3000);

async function loadUserTable() {
    const response = await fetch("/BoilerPlate_war_exploded/rest/live/mysql_json");
    const json = await response.json();

    const table = document.createElement("table");
    table.className = "brugertable";
    table.appendChild(create_attributes());
    for (const user of json.data) {
        table.appendChild(create_row(user));
    }

    // Clear userList first
    document.getElementById("userList").innerHTML = "";
    document.getElementById("userList").appendChild(table);
}
