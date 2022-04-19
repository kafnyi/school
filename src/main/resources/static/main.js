//Requests
//request, what gives a List<> as response
function studentSearchRequest(id, name, birth, diary, fun) {
    let xhr = new XMLHttpRequest();
    let url = "/api/v1/searchForStudent";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let response = xhr.responseText;
            let jsonResp = JSON.parse(response);
            if (fun === 1) {
                createStudentSearchResponseTable(jsonResp)
            } else if (fun === 2 || fun === 3) {
                createStudentPersonalDatasTable(jsonResp, fun)
            }
        }
    }
    const search = {id: id, name: name, birth: birth, diary: diary};
    const data = JSON.stringify(search);
    xhr.send(data);
}

function addNewStudentRequest(id, name, birth, diary) {
    window.alert(id, name, birth, diary)
    let xhr = new XMLHttpRequest();
    let url = "/api/v1/addNewStudent";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            studentSearchRequest(id, name, birth, diary, 2)
        }
    }
    const adding = {id: id, name: name, birth: birth, diary: diary};
    const data = JSON.stringify(adding);
    xhr.send(data);
}

function deleteStudentRequest(Id) {
    let xhr = new XMLHttpRequest();
    let url = "/api/v1/deleteStudent";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.alert("Student Deleted")
        }
    }
    const deleting = {id: Id, name: "", birth: "", diary: ""};
    const data = JSON.stringify(deleting);
    xhr.send(data);
}

function getSearchField() {
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
        "            <textarea maxlength=\"4\" ID=\"Dsi\" placeholder=\"Diary ID\" type=\"text\" rows=\"1\"/></textarea>\n" +
        "        </td>\n" +
        "\n" +
        "        <td class=\"style1\" rowspan=\"2\" style=\"width: 15%\">\n" +
        "            <button onclick=\"applyStudentSearch(document.getElementById('CNsi'), document.getElementById('Nsi'), document.getElementById('BDsi'), document.getElementById('Dsi')," + 1 + ")\"\n" +
        "                    style=\"width: 100px\">Search\n" +
        "            </button>\n" +
        "        </td>";
    document.getElementById("searchTable").innerHTML = tableForSearch;
}

function applyStudentSearch(Cn, N, Bd, D, fun) {
    let CNTtd = Cn.value;
    let Ntd = N.value;
    let BDtd = Bd.value;
    let Dtd = D.value;
    studentSearchRequest(CNTtd, Ntd, BDtd, Dtd, fun);
}

function applyAddingStudent() {
    let jsonTemp = {"name": "Student Name", "id": "Card Number", "sbirth": "YYYY-MM-DD", "sdiaryID": "Diary"}
    let jsonToCreate = JSON.stringify(jsonTemp)
    createStudentPersonalDatasTable(jsonToCreate, 3)
}

function applyStudentRefresh(Cn) {
    studentSearchRequest(Cn, "", "", "", 2)
}

function applyStudentDelete(Cn, Nm) {
    let Id = Cn;
    let name = Nm
    let proceedText = "Are You sure to DELETE the student, named : " + name + "?"
    if (confirm(proceedText) == true) {
        deleteStudentRequest(Id);

    } else {
        window.alert("The student Delete session cancelled")
    }
}

function createStudentSearchResponseTable(json) {
    let i;
    let response = json;
    let table = "<tr><th>Card Number</th><th>Name</th><th>Birth date</th><th>Diary ID</th></tr>";
    let x = response;
    for (i = 0; i < x.length; i++) {
        table += "<tr><td onclick=\"studentSearchRequest(" + x[i].id + ",null,null,null,2)\"> " +
            x[i].id +
            "</td><td onclick=\"studentSearchRequest(" + x[i].id + ",null,null,null,2)\">" +
            x[i].name +
            "</td><td>" +
            x[i].sbirth +
            "</td><td>" +
            x[i].sdiaryID +
            "</td></tr>";
    }
    document.getElementById("resultTable").innerHTML = table
}

function createStudentPersonalDatasTable(jsonToModify, command) {
    let student = jsonToModify
    let tId
    let tName
    let tBirth
    let tDiary
    let bottomButtons
    let tableForModify
    if (command === 2) {
        tId = student[0].id
        tName = student[0].name
        tBirth = student[0].sbirth
        tDiary = student[0].sdiaryID
        let refreshContent = "(" + tId + ")"
        let deleteContent = "( " + tId + " , \"" + tName + "\") \' "
        tableForModify = "<tr><th> Name   :<textarea  ID=\"SMNt\" placeholder=\"" + tName + "\"   type=\"text\" rows=\"1\" cols='50'/></textarea></th></tr>" +
            "<tr><th>Card Number   :<textarea maxlength=\"11\" ID=\"SMIDt\" placeholder=\"" + tId + "\"   type=\"text\" rows=\"1\" cols='11'/></textarea></th></tr>" +
            "<tr><th>Birth Date   :<textarea maxlength=\"10\" ID=\"SMBt\" placeholder=\"" + tBirth + "\"   type=\"text\" rows=\"1\" cols='10'/></textarea></th></tr>" +
            "<tr><th>Diary ID   :<textarea maxlength=\"4\" ID=\"SMDIDt\" placeholder=\"" + tDiary + "\"   type=\"text\" rows=\"1\" cols='4'/></textarea></th></tr>" +
            "<tr><th><button onclick=\"applyStudentRefresh " + refreshContent + " \" >Refresh</button></th></tr>" +
            "<tr><th><button>Modify</button></th></tr>" +
            "<tr><th><button onclick=\'applyStudentDelete " + deleteContent + ">Delete</button></th></tr>"
    } else if (command === 3) {
        tId = "Card Number"
        tName = "Student Name"
        tBirth = "YYYY-MM-DD"
        tDiary = "Diary"
        tableForModify = "<tr><th> Name   :<textarea  ID=\"SMNt\" placeholder=\"" + tName + "\"   type=\"text\" rows=\"1\" cols='50'/></textarea></th></tr>" +
            "<tr><th>Card Number   :<textarea maxlength=\"11\" ID=\"SMIDt\" placeholder=\"" + tId + "\"   type=\"text\" rows=\"1\" cols='11'/></textarea></th></tr>" +
            "<tr><th>Birth Date   :<textarea maxlength=\"10\" ID=\"SMBt\" placeholder=\"" + tBirth + "\"   type=\"text\" rows=\"1\" cols='10'/></textarea></th></tr>" +
            "<tr><th>Diary ID   :<textarea maxlength=\"4\" ID=\"SMDIDt\" placeholder=\"" + tDiary + "\"   type=\"text\" rows=\"1\" cols='4'/></textarea></th></tr>" +
            "<tr><th><button onclick=\"window.alert(document.getElementById('SMIDt').value, document.getElementById('SMNt').value, document.getElementById('SMBt').value, document.getElementById('SMDIDt').value);" +
            "addNewStudentRequest(document.getElementById('SMIDt').value, document.getElementById('SMNt').value, document.getElementById('SMBt').value, document.getElementById('SMDIDt').value)\">Add</button></th></tr>"
    }


    document.getElementById("searchTable").innerHTML = ""
    document.getElementById("resultTable").innerHTML = tableForModify
}



