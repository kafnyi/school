function profileButton() {

}

//Search Menu functions

function searchMenuButton() {
    let searchField = "<tr>" +
        "    <td style='width: 20%'>Searching for :\n" +
        "        <select id=\"SFS\">\n" +
        "            <option value=\"Student\">Student</option>\n" +
        "            <option value=\"Diary\">Diary</option>\n" +
        "            <option value=\"Class\">Class</option>\n" +
        "            <option value=\"Subject\">Subject</option>\n" +
        "            <option value=\"Mark\">Mark</option>\n" +
        "        </select>\n" +
        "    </td>" +
        "    <td style='width: 20%'>Search with :\n" +
        "        <select id=\"SWS\" onchange='selectSClarifiedInnerRow(document.getElementById(\"SWS\").value); getSpecificInputField(document.getElementById(\"SBS\").value,\"VSI\",\"SVF\")'>\n" +
        "            <option value=\"Student\" >Student</option>\n" +
        "            <option value=\"Diary\" >Diary</option>\n" +
        "            <option value=\"Class\" >Class</option>\n" +
        "            <option value=\"Subject\" >Subject</option>\n" +
        "            <option value=\"Mark\" >Mark</option>\n" +
        "        </select>\n" +
        "    </td>\n" +
        "    <td id=\"SCIR\" style='width: 20%'></td>" +
        "<td id='VSI' style='width: 20%'></td>" +
        "<td id='SMBC' style='width: 20%'></td> " +
        "</tr>"
    document.getElementById("searchTable").innerHTML = searchField
    document.getElementById("searchTable2").innerHTML = ""
    selectSClarifiedInnerRow(document.getElementById("SWS").value)
    getSpecificInputField(document.getElementById("SBS").value)
}

function selectSClarifiedInnerRow(selected) {

    let resValue = "Search By :";

    switch (selected) {
        case "Student" :
            resValue += generateSpecificSelectForStudentSearch();
            break
        case "Diary":
            resValue += generateSpecificSelectForDiarySearch();
            break
        case "Class":
            resValue += generateSpecificSelectForClassSearch();
            break
        case "Subject":
            resValue += generateSpecificSelectForSubjectSearch();
            break
        case "Mark":
            resValue += generateSpecificSelectForMarkSearch();
            break
        default:
            resValue = "please select what you want to search by "
            break
    }
    document.getElementById("SCIR").innerHTML = resValue;
}

function generateSpecificSelectForStudentSearch() {
    let student = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"StudentId\" name='SIdI'>Student ID</option>\n" +
        "            <option value=\"Name\" name='TextI'>Name</option>\n" +
        "            <option value=\"Date\"  name='DateI'>Birth Date</option>\n" +
        "        </select>\n"

    return student;
}

function generateSpecificSelectForDiarySearch() {
    let diary = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"DiaryId\" name='IdI'>Diary ID</option>\n" +
        "            <option value=\"StudentId\" name='SIdI'>Student ID</option>\n" +
        "            <option value=\"ClassId\" name='IdI'>Class ID</option>\n" +
        "        </select>\n"

    return diary;
}

function generateSpecificSelectForSubjectSearch() {
    let subject = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"SubjectId\" name='IdI'>Subject ID</option>\n" +
        "            <option value=\"Name\" name='TextI'>Subject Name</option>\n" +
        "            <option value=\"TeacherId\" name='IdI'>Teacher ID</option>\n" +
        "        </select>\n"

    return subject;
}

function generateSpecificSelectForClassSearch() {
    let division = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"ClassId\" name='IdI'>Class ID</option>\n" +
        "            <option value=\"Grade\" name='GradeI'>Grade</option>\n" +
        "            <option value=\"Sign\" name='SignI'>Sign</option>\n" +
        "            <option value=\"Year\" name='YearI'>Year</option>\n" +
        "            <option value=\"TeacherId\" name='IdI'>Teacher ID</option>\n" +
        "        </select>\n"

    return division;
}

function generateSpecificSelectForMarkSearch() {
    let mark = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"MarkId\" name='IdI'>Mark ID</option>\n" +
        "            <option value=\"DiaryId\" name='IdI'>Diary ID</option>\n" +
        "            <option value=\"Date\" name='DateI'>Date</option>\n" +
        "            <option value=\"SubjectId\" name='IdI'>Subject ID</option>\n" +
        "            <option value=\"Mark\" name='MarkI'>Mark</option>\n" +
        "        </select>\n"

    return mark;
}

function getSpecificInputField(spec) {

    switch (spec) {
        case "Name":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"text\" placeholder=\"Name\"/>"
            break;
        case "StudentId":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"10000000000\" max=\"99999999999\" step=\"1\" placeholder=\"Student ID\"/>";
            break;
        case "TeacherId":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"0\" placeholder=\"Teacher ID\"/>";
            break;
        case "SubjectId":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"0\" placeholder=\"Subject ID\"/>";
            break;
        case "MarkId":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"0\" placeholder=\"Mark ID\"/>";
            break;
        case "DiaryId":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"0\" placeholder=\"Diary ID\"/>";
            break;
        case "ClassId":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"0\" placeholder=\"Class ID\"/>";
            break;
        case "Grade":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"0\" max=\"14\" step=\"1\" placeholder=\"Grade\"/>";
            break;
        case "Date":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"date\" placeholder=\"Date\"/>"
            break;
        case "Year":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"1900\" max=\"2099\" step=\"1\" value=\"2022\" placeholder=\"Year\"/>";
            break;
        case "Sign":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"text\" maxlength='1' pattern='[A-Z]' placeholder=\"Sign\"/>";
            break;
        case "Mark":
            document.getElementById("VSI").innerHTML =
                "<input id=\"SVF\" type=\"number\" min=\"1\" max=\"5\" step=\"1\" value=\"5\" placeholder=\"Mark\"/>";
            break;
        default:
            window.alert("Error! Missing SearchInputSpecification!")
            break;
    }
    document.getElementById("SMBC").innerHTML =
        "<input type='button' style='width: 150px' value='Search' onclick='createAndSendSearchRequest()'>"
}

function createAndSendSearchRequest() {
    let sFor = document.getElementById("SFS").value
    let sWith = document.getElementById("SWS").value
    let sBy = document.getElementById("SBS").value
    let sVal = document.getElementById("SVF").value

    let xhr = new XMLHttpRequest();
    let url = "/tli/search";
    xhr.open("POST", url, true);
    xhr.subscribe()
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let result = JSON.parse(xhr.response)
            createSearchResultTable(result)
        }
    }
    const search = {rFor: sFor, rWith: sWith, rBy: sBy, rValue: sVal};
    const data = JSON.stringify(search);
    xhr.send(data);
}

function createSearchResultTable(json) {
    let type = json[0];
    let result = json;
    let table;
    switch (type) {
        case "Student" :
            table = generateStudentResultTable(result)
            break;
        case "Teacher" :
            table = generateTeacherResultTable(result)
            break;
        case "Class" :
            table = generateClassResultTable(result)
            break;
        case "Subject" :
            table = generateSubjectResultTable(result)
            break;
        case "Diary":
            table = generateDiaryResultTable(result)
            break;
        case "Mark" :
            table = generateMarkResultTable(result)
            break;
        default :

    }


    document.getElementById("searchTable2").innerHTML = table
}

function generateStudentResultTable(result) {
    let table = "<tr><th>Student ID</th><th>Name</th><th>Birth date</th></tr>";
    for (let i = 1; i < result.length; i++) {
        table += "<tr><td> " +
            student.id +
            "</td><td >" +
            student.name +
            "</td><td>" +
            student.birthDate +
            "</td><td><input type='button' value='Modify' onclick='createModifyTable(" + type + ", " + student + ")'></td></tr>";
    }
    return table;
}

function generateTeacherResultTable(result) {
    let table = "<tr><th>Teacher ID</th><th>Name</th><th>Birth date</th></tr>";
    for (let i = 1; i < result.length; i++) {
        table += "<tr><td> " +
            result[i].id +
            "</td><td onclick=\"\">" +
            result[i].name +
            "</td><td>" +
            result[i].date +
            "</td></tr>";
    }
    return table;
}

function generateSubjectResultTable(result) {
    let table = "<tr><th>Subject ID</th><th>Name</th><th>Teacher ID</th></tr>";
    for (let i = 1; i < result.length; i++) {
        table += "<tr><td onclick=\"createModifyTable(" + result[0] + " , " + result[i] + " )\"> " +
            result[i].id +
            "</td><td>" +
            result[i].name +
            "</td><td>" +
            result[i].teacherID +
            "</td></tr>";
    }
    return table;
}

function generateClassResultTable(result) {
    let table = "<tr><th>Class ID</th><th>Year</th><th>Grade</th><th>Sign</th><th>Teacher ID</th></tr>";
    for (let i = 1; i < result.length; i++) {
        table += "<tr><td> " +
            result[i].id +
            "</td><td onclick=\"\">" +
            result[i].year +
            "</td><td>" +
            result[i].grade +
            "</td><td>" +
            result[i].sign +
            "</td><td>" +
            result[i].teacherID +
            "</td></tr>";
    }
    return table;
}

function generateDiaryResultTable(result) {
    let table = "<tr><th>Diary ID</th><th>Student ID</th><th>Class ID</th></tr>";
    for (let i = 1; i < result.length; i++) {
        table += "<tr><td onclick=\"createModifyTable(" + result[0] + " , " + result[i] + " )\"> " +
            result[i].id +
            "</td><td onclick=\"\">" +
            result[i].studentID +
            "</td><td>" +
            result[i].classID +
            "</td></tr>";
    }
    return table;
}

function generateMarkResultTable(result) {
    let table = "<tr><th>Mark ID</th><th>Diary ID</th><th>Date</th><th>Subject ID</th><th>Mark</th></tr>"
    for (let i = 1; i < result.length; i++) {
        table += "<tr><td onclick='createModifyTable(" + result[0] + " , " + result[i] + " )'> " +
            result[i].id +
            "</td><td onclick=\"\">" +
            result[i].diaryID +
            "</td><td>" +
            result[i].date +
            "</td><td>" +
            result[i].subjectID +
            "</td><td>" +
            result[i].mark +
            "</td></tr>";
    }
    return table;
}

//Add Menu functions

function addMenuButton() {
    let toAddSelectField = "<tr>" +
        "    <td style='width: 50%'>Adding a :\n" +
        "        <select id=\"AFS\" onchange='createAddField(document.getElementById(\"AFS\").value)'>\n" +
        "            <option value=\"Student\">Student</option>\n" +
        "            <option value=\"Diary\">Diary</option>\n" +
        "            <option value=\"Class\">Class</option>\n" +
        "            <option value=\"Subject\">Subject</option>\n" +
        "            <option value=\"Mark\">Mark</option>\n" +
        "        </select>\n" +
        "    </td>" +
        "</tr>"
    document.getElementById("searchTable").innerHTML = toAddSelectField;
    createAddField(document.getElementById("AFS").value)

}

function createAddField(select) {

    switch (select) {
        case "Student" :
            document.getElementById("searchTable2").innerHTML = generateStudentAddField()
            break;
        case "Diary":
            document.getElementById("searchTable2").innerHTML = generateDiaryAddField()
            break;
        case "Class":
            document.getElementById("searchTable2").innerHTML = generateClassAddField()
            break;
        case "Subject":
            document.getElementById("searchTable2").innerHTML = generateSubjectAddField()
            break;
        case "Mark":
            document.getElementById("searchTable2").innerHTML = generateMarkAddField()
            break;
        case "Teacher":
            document.getElementById("searchTable2").innerHTML = generateTeacherAddField()
            break;
    }
}

function generateStudentAddField() {
    let studentField = "<tr>" +
        "<td>Student ID :" +
        "<input id=\"ASSId\" type=\"number\" min=\"10000000000\" max=\"99999999999\" step=\"1\" placeholder=\"Student ID\"/>" +
        "</td>" +
        "<td>Student Name : " +
        "<input id=\"ASN\" type=\"text\" placeholder=\"Name\"/><" +
        "/td>" +
        "<td>Birth Date : " +
        "<input id=\"ASD\" type=\"date\" placeholder=\"Date\"/>" +
        "</td>" +
        "<td>" +
        "<input type='button' style='width: 150px' value='ADD' " +
        "onclick='createAndSendAddingRequest()'>" +
        "</td>" +
        "</tr>"

    return studentField;
}

function generateDiaryAddField() {
    let diaryField = "<tr>" +
        "<td>Diary ID : " +
        "<input id=\"ADDId\" type=\"number\" min=\"0\" placeholder=\"Diary ID\"/>" +
        "</td>" +
        "<td>Student ID : " +
        "<input id=\"ADSId\" type=\"text\" placeholder=\"Name\"/>" +
        "</td>" +
        "<td> Class ID : " +
        "<input id=\"ADCId\" type=\"number\" min=\"0\" placeholder=\"Class ID\"/>" +
        "</td>" +
        "<td>" +
        "<input type='button' style='width: 150px' value='ADD' " +
        "onclick='createAndSendAddingRequest()'" +
        "></td>" +
        "</tr>"

    return diaryField;
}

function generateSubjectAddField() {
    let subjectField = "<tr>" +
        "<td>Subject ID : " +
        "<input id=\"ASjSjId\" type=\"number\" min=\"0\" placeholder=\"Subject ID\"/>" +
        "</td>" +
        "<td>Subject Name : " +
        "<input id=\"ASjN\" type=\"text\" placeholder=\"Name\"/>" +
        "</td>" +
        "<td>Teacher ID : " +
        "<input id=\"ASjTId\" type=\"number\" min=\"0\" placeholder=\"Teacher ID\"/>" +
        "</td>" +
        "<td>" +
        "<input type='button' style='width: 150px' value='ADD' " +
        "onclick='createAndSendAddingRequest()'" +
        "></td>" +
        "</tr>"

    return subjectField;
}

function generateClassAddField() {
    let classField = "<tr>" +
        "<td>Class ID : " +
        "<input id=\"ACCId\" type=\"number\" min=\"0\" placeholder=\"Class ID\"/>" +
        "</td>" +
        "<td>Grade : " +
        "<input id=\"ACG\" type=\"number\" min=\"0\" max=\"14\" step=\"1\" placeholder=\"Grade\"/>" +
        "</td>" +
        "<td>Sign : " +
        "<input id=\"ACS\" type=\"text\" maxlength='1' pattern='[A-Z]' placeholder=\"Sign\"/>" +
        "</td>" +
        "</td>" +
        "<td>Year : " +
        "<input id=\"ACY\" type=\"number\" min=\"1900\" max=\"2099\" step=\"1\" value=\"2022\" placeholder=\"Year\"/>" +
        "</td>" +
        "</td>" +
        "<td>Teacher ID : " +
        "<input id=\"ACTId\" type=\"number\" min=\"0\" placeholder=\"Teacher ID\"/>" +
        "</td>" +
        "<td>" +
        "<input type='button' style='width: 150px' value='ADD' " +
        "onclick='createAndSendAddingRequest()'" +
        "></td>" +
        "</tr>"

    return classField;
}

function generateMarkAddField() {
    let markField = "<tr>" +
        "<td>Mark ID : " +
        "<input id=\"AMMId\" type=\"number\" min=\"0\" placeholder=\"Mark ID\"/>" +
        "</td>" +
        "<td>Diary ID : " +
        "<input id=\"AMDId\" type=\"number\" min=\"0\" placeholder=\"Diary ID\"/>" +
        "</td>" +
        "<td>Date : " +
        "<input id=\"AMD\" type=\"date\" placeholder=\"Date\"/>" +
        "</td>" +
        "</td>" +
        "<td>Subject ID : " +
        "<input id=\"AMSjId\" type=\"number\" min=\"0\" placeholder=\"Subject ID\"/>" +
        "</td>" +
        "</td>" +
        "<td>Mark : " +
        "<input id=\"AMM\" type=\"number\" min=\"1\" max=\"5\" step=\"1\" value=\"5\" placeholder=\"Mark\"/>" +
        "</td>" +
        "<td>" +
        "<input type='button' style='width: 150px' value='ADD' " +
        "onclick='createAndSendAddingRequest()'" +
        "></td>" +
        "</tr>"

    return markField;
}

function generateTeacherAddField() {
    let teacherField = "<tr>" +
        "<td>Teacher ID :" +
        "<input id=\"ATTId\" type=\"number\" min=\"0\" placeholder=\"Teacher ID\"/>" +
        "</td>" +
        "<td>Teacher Name : " +
        "<input id=\"ATN\" type=\"text\" placeholder=\"Name\"/><" +
        "/td>" +
        "<td>Birth Date : " +
        "<input id=\"ATD\" type=\"date\" placeholder=\"Date\"/>" +
        "</td>" +
        "<td>" +
        "<input type='button' style='width: 150px' value='ADD' " +
        "onclick='createAndSendAddingRequest()'>" +
        "</td>" +
        "</tr>"

    return teacherField;
}

function createAndSendAddingRequest() {

    let addType = document.getElementById("AFS").value
    let addValue = createAddingValue(addType)

    let xhr = new XMLHttpRequest();
    let url = "/tli/adding";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.alert("sucess adding" + xhr.response)
        }
    }
    const data = JSON.stringify(addValue);
    xhr.send(data);
    window.alert(adding);
}

function createAddingValue(type) {
    let addValue;

    switch (type) {
        case "Student":
            addValue = getStudentDetailsForAdding()
            break;
        case "Diary":
            addValue = getDiaryDetailsForAdding()
            break;
        case "Subject":
            addValue = getSubjectDetailsForAdding()
            break;
        case "Teacher":
            addValue = getTeacherDetailsForAdding()
            break;
        case "Class":
            addValue = getClassDetailsForAdding()
            break;
        case "Mark":
            addValue = getMarkDetailsForAdding()
            break;
    }
    return addValue;
}

function getStudentDetailsForAdding() {
    let student = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("ASSId").value,
        name: document.getElementById("ASN").value,
        date: document.getElementById("ASD").value
    }
    return student;
}

function getDiaryDetailsForAdding() {
    let diary = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("ADDId").value,
        studentID: document.getElementById("ADSId").value,
        classID: document.getElementById("ADCId").value
    }
    return diary;
}

function getClassDetailsForAdding() {
    let division = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("ACCId").value,
        grade: document.getElementById("ACG").value,
        sign: document.getElementById("ACS").value,
        year: document.getElementById("ACY").value,
        teacherID: document.getElementById("ACTId").value,
    }
    return division;
}

function getSubjectDetailsForAdding() {
    let subject = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("ASjSjId").value,
        subjectName: document.getElementById("ASjN").value,
        teacherID: document.getElementById("ASjTId").value
    }
    return subject;
}

function getTeacherDetailsForAdding() {
    let teacher = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("ATId").value,
        name: document.getElementById("ATN").value,
        date: document.getElementById("ATD").value
    }
    return teacher;
}

function getMarkDetailsForAdding() {
    let mark = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("AMMId").value,
        diaryID: document.getElementById("AMDId").value,
        date: document.getElementById("AMD").value,
        subjectID: document.getElementById("AMSjId").value,
        mark: document.getElementById("AMM").value
    }
    return mark;
}

function createModifyTable(type, data) {

    window.alert("pre start")
    let tableType = type
    let original = data
    let table = "<tr> <td>mi a fsz van?00000000</td></tr>";
    window.alert("start")

    switch (tableType) {
        case "Student" : {
            window.alert("hi mivan")
            table = "<tr> <td>mi a fsz van?</td></tr>"
            break;
        }
        case "Diary" : {
            break;
        }
        case "Class" : {
            break;
        }
        case "Subject" : {
            break;
        }
        case "Teacher" : {
            break;
        }
        case "Mark" : {
            break;
        }
        default: {
            table = "default bazdmeg" + tableType
        }
    }
    document.getElementById("searchTable3").innerHTML = table;

    window.alert("lefutott")
}

function confirmModify(type, original) {

    let xhr = new XMLHttpRequest();
    let url = "/tli/modify";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.alert("sucess modify" + xhr.response)
        }
    }
    const modify = type + original;
    const data = JSON.stringify(modify);
    xhr.send(data);
    window.alert(modify);


}

function confirmDelete(type, toDelete) {

    let xhr = new XMLHttpRequest();
    let url = "/tli/delete";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.alert("sucess DELETE")
        }
    }
        const modify = type + toDelete;
        const data = JSON.stringify(modify);
        xhr.send(data);
        window.alert(modify + " is sent to deleteing");


}