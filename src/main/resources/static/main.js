function profileButton() {

}



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
    let rValueSB = "Search By :"
    let rValueSI
    let rValueStd = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"StudentId\" name='SIdI'>Student ID</option>\n" +
        "            <option value=\"Name\" name='TextI'>Name</option>\n" +
        "            <option value=\"Date\"  name='DateI'>Birth Date</option>\n" +
        "        </select>\n"
    let rValueDry = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"DiaryId\" name='IdI'>Diary ID</option>\n" +
        "            <option value=\"StudentId\" name='SIdI'>Student ID</option>\n" +
        "            <option value=\"ClassId\" name='IdI'>Class ID</option>\n" +
        "        </select>\n"
    let rValueCls = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"ClassId\" name='IdI'>Class ID</option>\n" +
        "            <option value=\"Grade\" name='GradeI'>Grade</option>\n" +
        "            <option value=\"Sign\" name='SignI'>Sign</option>\n" +
        "            <option value=\"Year\" name='YearI'>Year</option>\n" +
        "            <option value=\"TeacherId\" name='IdI'>Teacher ID</option>\n" +
        "        </select>\n"
    let rValueSbj = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"SubjectId\" name='IdI'>Subject ID</option>\n" +
        "            <option value=\"Name\" name='TextI'>Subject Name</option>\n" +
        "            <option value=\"TeacherId\" name='IdI'>Teacher ID</option>\n" +
        "        </select>\n"
    let rValueMrk = "        <select id=\"SBS\" " +
        "onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"MarkId\" name='IdI'>Mark ID</option>\n" +
        "            <option value=\"DiaryId\" name='IdI'>Diary ID</option>\n" +
        "            <option value=\"Date\" name='DateI'>Date</option>\n" +
        "            <option value=\"SubjectId\" name='IdI'>Subject ID</option>\n" +
        "            <option value=\"Mark\" name='MarkI'>Mark</option>\n" +
        "        </select>\n"
    let resValue;

    switch (selected) {
        case "Student" :
            resValue = rValueSB + rValueStd
            document.getElementById("SCIR").innerHTML = resValue;
            break
        case "Diary":
            resValue = rValueSB + rValueDry
            document.getElementById("SCIR").innerHTML = resValue;
            break
        case "Class":
            resValue = rValueSB + rValueCls
            document.getElementById("SCIR").innerHTML = resValue;
            break
        case "Subject":
            resValue = rValueSB + rValueSbj
            document.getElementById("SCIR").innerHTML = resValue;
            break
        case "Mark":
            resValue = rValueSB + rValueMrk
            document.getElementById("SCIR").innerHTML = resValue;
            break
        default:
            resValue = "please select what you want to search by "
            document.getElementById("SCIR").innerHTML = resValue;
            break
    }
}

function getSpecificInputField(spec) {
    /**
     * Spec:[0-7]
     * 0- simple text input
     * 1- Student ID input
     * 2- simple number input
     * 3- Grade input
     * 4- Date input
     * 5- numeric Year input
     * 6- Sign input
     * 7- Mark input
     */
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
        "<input type='button' style='width: 150px' value='Search' onclick='confirmSearch()'>"
}

function confirmSearch() {
    let sFor = document.getElementById("SFS").value
    let sWith = document.getElementById("SWS").value
    let sBy = document.getElementById("SBS").value
    let sVal = document.getElementById("SVF").value

    let xhr = new XMLHttpRequest();
    let url = "/tli/search";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let result = JSON.parse(xhr.response)
            createSearchResponseTable(result)
        }
    }
    const search = {rFor: sFor, rWith: sWith, rBy: sBy, rValue: sVal};
    const data = JSON.stringify(search);
    xhr.send(data);
}

function addMenuButton() {
    let toAddSelectField = "<tr>" +
        "    <td style='width: 50%'>Adding a :\n" +
        "        <select id=\"AFS\" onchange='addFieldGenerate(document.getElementById(\"AFS\").value)'>\n" +
        "            <option value=\"Student\">Student</option>\n" +
        "            <option value=\"Diary\">Diary</option>\n" +
        "            <option value=\"Class\">Class</option>\n" +
        "            <option value=\"Subject\">Subject</option>\n" +
        "            <option value=\"Mark\">Mark</option>\n" +
        "        </select>\n" +
        "    </td>" +
        "</tr>"
    document.getElementById("searchTable").innerHTML = toAddSelectField;
    addFieldGenerate(document.getElementById("AFS").value)

}

function addFieldGenerate(select) {
    let studentCase = "<tr>" +
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
        "onclick='confirmAdding()'>" +
        "</td>" +
        "</tr>"

    let diaryCase = "<tr>" +
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
        "onclick='confirmAdding()'" +
        "></td>" +
        "</tr>"

    let subjectCase = "<tr>" +
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
        "onclick='confirmAdding()'" +
        "></td>" +
        "</tr>"

    let classCase = "<tr>" +
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
        "onclick='confirmAdding()'" +
        "></td>" +
        "</tr>"

    let markCase = "<tr>" +
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
        "onclick='confirmAdding()'" +
        "></td>" +
        "</tr>"

    let teacherCase = "<tr>" +
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
        "onclick='confirmAdding()'>" +
        "</td>" +
        "</tr>"

    switch (select) {
        case "Student" :
            document.getElementById("searchTable2").innerHTML = studentCase
            break;
        case "Diary":
            document.getElementById("searchTable2").innerHTML = diaryCase
            break;
        case "Class":
            document.getElementById("searchTable2").innerHTML = classCase
            break;
        case "Subject":
            document.getElementById("searchTable2").innerHTML = subjectCase
            break;
        case "Mark":
            document.getElementById("searchTable2").innerHTML = markCase
            break;
        case "Teacher":
            document.getElementById("searchTable2").innerHTML = teacherCase
            break;
    }


}

function confirmAdding() {

    let addFieldType = document.getElementById("AFS").value
    let addV

    let studentAdding ={
        type: document.getElementById("AFS").value,
        id: document.getElementById("ASSId").value,
        name: document.getElementById("ASN").value,
        date: document.getElementById("ASD").value
    }
    let diaryAdding = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("ADDId").value,
        studentID: document.getElementById("ADSId").value,
        classID: document.getElementById("ADCId").value
    }
    let subjectAdding = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("ASjSjId").value,
        subjectName: document.getElementById("ASjN").value,
        teacherID: document.getElementById("ASjTId").value
    }
    let teacherAdding ={
        type: document.getElementById("AFS").value,
        id: document.getElementById("ATId").value,
        name: document.getElementById("ATN").value,
        date: document.getElementById("ATD").value
    }
    let classAdding = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("ACCId").value,
        grade: document.getElementById("ACG").value,
        sign: document.getElementById("ACS").value,
        year: document.getElementById("ACY").value,
        teacherID: document.getElementById("ACTId").value,
    }
    let markAdding = {
        type: document.getElementById("AFS").value,
        id: document.getElementById("AMMId").value,
        diaryID: document.getElementById("AMDId").value,
        date: document.getElementById("AMD").value,
        subjectID: document.getElementById("AMSjId").value,
        mark: document.getElementById("AMM").value
    }
    switch (addFieldType) {

        case "Student":
            addV = studentAdding
            break;
        case "Diary":
            addV = diaryAdding
            break;
        case "Subject":
            addV = subjectAdding
            break;
        case "Teacher":
            addV = teacherAdding
            break;
        case "Class":
            addV = classAdding
            break;
        case "Mark":
            addV = markAdding
            break;
    }
    addRequestSend(addV)
}
function addRequestSend(request){
    let xhr = new XMLHttpRequest();
    let url = "/tli/adding";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.alert("sucess adding" + xhr.response)
        }
    }
    const adding = request;
    const data = JSON.stringify(adding);
    xhr.send(data);
}

function createSearchResponseTable(json) {
    let type = json[0];
    let response = json;
    let table;
    let student;
    let forward = json;


    switch (type) {
        case "Student" :table = getStudentTable(response)
            break;
        case "Teacher" :table = getTeacherTable(response)
            break;
        case "Class" :table = getClassTable(response)
            break;
        case "Subject" :table = getSubjectTable(response)
            break;
        case "Diary":table = getDiaryTable(response)
            break;
        case "Mark" :table = getMarkTable(response)
            break;
        default :
    }
    document.getElementById("searchTable2").innerHTML = table
}

function getStudentTable(data){
    let table;
    table = "<tr><th>Student ID</th><th>Name</th><th>Birth date</th></tr>";
    for (let i = 1; i < data.length; i++) {
        let student = data[i];
        table += "<tr><td> " +
            student.id +
            "</td><td >" +
            student.name +
            "</td><td>" +
            student.birthDate +
            "</td></tr>";
    }
    return table;
}

function getDiaryTable(data){
    let table;
    table = "<tr><th>Diary ID</th><th>Student ID</th><th>Class ID</th></tr>";
    for (let i = 1; i < data.length; i++) {
        let diary = data[i]
        table += "<tr><td> " +
            diary.id +
            "</td><td onclick=\"\">" +
            diary.studentID +
            "</td><td>" +
            diary.classID +
            "</td></tr>";
    }
    return table;
}

function getClassTable(data){
    let table;
    table = "<tr><th>Class ID</th><th>Year</th><th>Grade</th><th>Sign</th><th>Teacher ID</th></tr>";
    for (let i = 1; i < data.length; i++) {
        let division = data[i]
        table += "<tr><td> " +
            division.id +
            "</td><td onclick=\"\">" +
            division.year +
            "</td><td>" +
            division.grade +
            "</td><td>" +
            division.sign +
            "</td><td>" +
            division.teacherID +
            "</td></tr>";
    }
    return table;
}

function getSubjectTable(data){
    let table;
    table = "<tr><th>Subject ID</th><th>Name</th><th>Teacher ID</th></tr>";
    for (let i = 1; i < data.length; i++) {
        let subject = data[i]
        table += "<tr><td> " +
            subject.id +
            "</td><td>" +
            subject.name +
            "</td><td>" +
            subject.teacherID +
            "</td></tr>";
    }
    return table;
}

function getMarkTable(data) {
    let table;
   table = "<tr><th>Mark ID</th><th>Diary ID</th><th>Date</th><th>Subject ID</th><th>Mark</th></tr>"
    for (let i = 1; i < data.length; i++) {
        let mark =data[i]
        table += "<tr><td> " +
            mark.id +
            "</td><td onclick=\"\">" +
            mark.diaryID +
            "</td><td>" +
            mark.date +
            "</td><td>" +
            mark.subjectID +
            "</td><td>" +
            mark.mark +
            "</td></tr>";
    }
    return table;
}

function getTeacherTable(data){
    let table;
    table = "<tr><th>Teacher ID</th><th>Name</th><th>Birth date</th></tr>";
    for (let i = 1; i < data.length; i++) {
        let teacher = data[i]
        table += "<tr><td> " +
            teacher.id +
            "</td><td onclick=\"\">" +
            teacher.name +
            "</td><td>" +
            teacher.date +
            "</td></tr>";
    }
    return table;
}

function getStudentModifyTable(student){
    let studentTable = "<tr><td>Name :</td><td><input id='MNI' type='text' value='" + student.name + "' placeholder='Student Name'></td></tr>" +
        "<tr><td>Student ID :</td><td><input id='MIdI' type='number' value='"+student.id+"' placeholder='Student ID' min='10000000000' max='99999999999'></td></tr>" +
        "<tr><td>Birthday :</td><td><input id='MDI' type='date' value='" + student.birthDate + "' placeholder='Birthday'></td></tr>" +
        "<tr><td><input type='button' value='Modify' style='width: 150px' ></td>" +
        "<td><input type='button' value='Delete' style='width: 150px' ></td></tr>"
    return studentTable
}

function getTeacherModifyTable(teacher){
    let teacherTable = "<tr><td>Name :</td><td><input id='MNI' type='text' value='" + teacher.name + "' placeholder='Teacher Name'></td></tr>" +
        "<tr><td>ID :</td><td><input id='MIdI' type='number' value='" + teacher.id + "' placeholder='Teacher ID' min='1000000000' max='9999999999'></td></tr>" +
        "<tr><td>Birthday :</td><td><input id='MDI' type='date' value='" + teacher.birthDate + "' placeholder='Birthday'></td></tr>" +
        "<tr><td><input type='button' value='Modify' style='width: 150px' onclick='confirmModify(\"Teacher\" , " + teacher + ")'></td>" +
        "<td><input type='button' value='Delete' style='width: 150px' onclick='confirmDelete(\"Teacher\" , " + teacher + ")'></td></tr>"
    return teacherTable
}

function getDiaryModifyTable(diary){
    let diaryTable = "<tr><td>Diary ID :</td><td><input id='MIdI' type='number' value='" + diary.id + "' placeholder='Diary ID' min='0'></td></tr>" +
        "<tr><td>Student ID :</td><td><input id='MSIdI' type='number' value='" + diary.studentId + "' placeholder='Student ID' min='10000000000' max='99999999999'></td></tr>" +
        "<tr><td>Class ID :</td><td><input id='MCIdI' type='number' value='" + diary.classId + "' placeholder='Class ID' min='0'></td></tr>" +
        "<tr><td><input type='button' value='Modify' style='width: 150px' onclick='confirmModify(\"Diary\"  , " + diary + ")'></td>" +
        "<td><input type='button' value='Delete' style='width: 150px' onclick='confirmDelete(\"Diary\"  , " + diary + ")'></td></tr>"
    return diaryTable
}

function getSubjectModifyTable(subject){
    let subjectTable = "<tr><td>Subject Name :</td><td><input id='MNI' type='text' value='" + subject.name + "' placeholder='Subject Name'></td></tr>" +
        "<tr><td>ID :</td><td><input id='MSjIdI' type='number' value='" + subject.id + "' placeholder='Subject ID' min='0'></td></tr>" +
        "<tr><td>Teacher ID :</td><td><input id='MTIdI' type='number' value='" + subject.teacherId + "' placeholder='Teacher ID' min='1000000000' max='9999999999'></td></tr>" +
        "<tr><td><input type='button' value='Modify' style='width: 150px' onclick='confirmModify(\"Subject\" , " + subject + ")'></td>" +
        "<td><input type='button' value='Delete' style='width: 150px' onclick='confirmDelete(\"Subject\" , " + subject + ")'></td></tr>"
    return subjectTable
}

function getClassModifyTable(division){
    let classTable = "<tr><td>Class ID :</td><td><input id='MCdI' type='number' value='" + division.id + "' placeholder='Class ID' min='0'></td></tr>" +
        "<tr><td> Grade :</td><td><input id='MGI' type='number' value='" + division.grade + "' placeholder='Grade' min='1' max='14'></td></tr>" +
        "<tr><td>Sign :</td><td><input id='MSI' type='text' value='" + division.sign + "' placeholder='Sign' maxlength='1' pattern='[A-Z]'></td></tr>" +
        "<tr><td>Year :</td><td><input id='MYI' type='number' value='" + division.year + "' placeholder='Year' min='1900' max='2099'></td></tr>" +
        "<tr><td>Teacher ID :</td><td><input id='MTIdI' type='number' value='" + division.teacherId + "' placeholder='Teacher ID' min='1000000000' max='9999999999'></td></tr>" +
        "<tr><td><input type='button' value='Modify' style='width: 150px' onclick='confirmModify(" + tableType + " , " + division + ")'></td>" +
        "<td><input type='button' value='Delete' style='width: 150px' onclick='confirmDelete(" + tableType + " , " + division + ")'></td></tr>"
    return classTable
}

function getMarkModifyTable(mark){
    let markTable =  "<tr><td>Mark ID :</td><td><input id='MIdI' type='number' value='" + mark.id + "' placeholder='Mark ID' min='0'></td></tr>" +
        "<tr><td> Diary ID :</td><td><input id='MDIdI' type='number' value='" + mark.diaryId + "' placeholder='Diary ID' min='0'></td></tr>" +
        "<tr><td>Date :</td><td><input id='MDI' type='date' value='" + mark.date + "' placeholder='Date'></td></tr>" +
        "<tr><td>Subject ID :</td><td><input id='MSjIdI' type='number' value='" + mark.subjectId + "' placeholder='Subject ID' min='0'></td></tr>" +
        "<tr><td>Mark :</td><td><input id='MMI' type='number' value='" + mark.mark + "' placeholder='Mark' min='1' max='5'></td></tr>" +
        "<tr><td><input type='button' value='Modify' style='width: 150px' onclick='confirmModify(" + tableType + " , " + mark + ")'></td>" +
        "<td><input type='button' value='Delete' style='width: 150px' onclick='confirmDelete(" + tableType + " , " + mark + ")'></td></tr>"
}


function createModifyTable(type, data) {

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