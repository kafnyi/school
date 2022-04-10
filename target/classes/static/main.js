function applyStudentSearch(Cn, N, Bd, D, fun) {
    let CNTtd = Cn.value;
    let Ntd = N.value;
    let BDtd = Bd.value;
    let Dtd = D.value;
    studentSearch(CNTtd, Ntd, BDtd, Dtd, fun);
}

function studentSearch(id, name, birth, diary, fun) {
    let xhr = new XMLHttpRequest();
    let url = "/api/v1/searchForStudent";
    xhr.open("POST", url, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let response = xhr.responseText;
            let jsonResp = JSON.parse(response);
            if (fun === 1) {
                createStudentSearchResponseTable(jsonResp)
            } else {
                modifyStudentDatasTable(jsonResp)
            }
            ;
        }
    }
    const search = {id: id, name: name, birth: birth, diary: diary};
    const data = JSON.stringify(search);
    xhr.send(data);
}

function createStudentSearchResponseTable(json) {
    let i;
    let response = json;
    let table = "<tr><th>Card Number</th><th>Name</th><th>Birth date</th><th>Diary ID</th></tr>";
    let x = response;
    for (i = 0; i < x.length; i++) {
        table += "<tr><td onclick=\"studentSearch(" + x[i].id + ",null,null,null,2)\"> " +
            x[i].id +
            "</td><td onclick=\"studentSearch(" + x[i].id + ",null,null,null,2)\">" +
            x[i].name +
            "</td><td>" +
            x[i].sbirth +
            "</td><td>" +
            x[i].sdiaryID +
            "</td></tr>";
    }
    document.getElementById("resultTable").innerHTML = table
}

function modifyStudentDatasTable(jsonToModify) {
    let student = jsonToModify;
    let tableForModify = "<tr><th> Name </th><th><textarea ID=\"SMNt\" placeholder=\"" + student[0].name + "\"   type=\"text\" rows=\"1\"/></textarea></th><th><button>Modify</button></th></tr>" +
        "<tr><th>Card Number</th><th><textarea ID=\"SMIDt\" placeholder=\"" + student[0].id + "\"   type=\"text\" rows=\"1\"/></textarea></th><th><button>Modify</button></th></tr>" +
        "<tr><th>Birth Date</th><th><textarea ID=\"SMBt\" placeholder=\"" + student[0].sbirth + "\"   type=\"text\" rows=\"1\"/></textarea></th><th><button>Modify</button></th></tr>" +
        "<tr><th>Diary ID</th><th><textarea ID=\"SMDIDt\" placeholder=\"" + student[0].sdiaryID + "\"   type=\"text\" rows=\"1\"/></textarea></th><th><button>Modify</button></th></tr>" //+
    "<tr><th><button>Refresh</button></th></tr>" +
    "<tr><th><button>Modify</button></th></tr>" +
    "<tr><th><button>Delete</button></th></tr>";
    document.getElementById("resultTable").innerHTML = tableForModify
}

function getSearchField(fun) {
    let tableForSearch = "<td style=\"width: 10%\">\n" +
        "            <textarea maxlength=\"11\" id=\"CNsi\" name=\"CNt\" placeholder=\"Card Number\" type=\"text\" rows=\"1\"/></textarea>\n" +
        "        </td>\n" +
        "        <td class=\"style1\" style=\"width: 25%\">\n" +
        "            <textarea ID=\"Nsi\" placeholder=\"Name\" style=\"width: 230px\" type=\"text\" rows=\"1\"></textarea>\n" +
        "        </td>\n" +
        "        <td class=\"style1\" style=\"width: 25%\">\n" +
        "            <textarea maxlength=\"10\" ID=\"BDsi\" placeholder=\"Birthday\" style=\"width: 130px\" type=\"text\"\n" +
        "                      rows=\"1\"/></textarea>\n" +
        "        </td>\n" +
        "        <td class=\"style1\" style=\"width: 25%\">\n" +
        "            <textarea maxlength=\"8\" ID=\"Dsi\" placeholder=\"Diary ID\" type=\"text\" rows=\"1\"/></textarea>\n" +
        "        </td>\n" +
        "\n" +
        "        <td class=\"style1\" rowspan=\"2\" style=\"width: 15%\">\n" +
        "            <button onclick=\"applyStudentSearch(document.getElementById('CNsi'), document.getElementById('Nsi'), document.getElementById('BDsi'), document.getElementById('Dsi')," + fun + ")\"\n" +
        "                    style=\"width: 100px\">Search\n" +
        "            </button>\n" +
        "        </td>";
    document.getElementById("searchTable").innerHTML = tableForSearch;
}

