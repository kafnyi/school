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
    selectSClarifiedInnerRow(document.getElementById("SWS").value)
    getSpecificInputField(document.getElementById("SBS").value, "VSI", "SVF")
}

function selectSClarifiedInnerRow(selected) {
    let rValueSB = "Search By :"
    let rValueSI
    let rValueStd = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value,\"VSI\",\"SVF\")'>\n" +
        "            <option value=\"StudentId\" name='SIdI'>Student ID</option>\n" +
        "            <option value=\"Name\" name='TextI'>Name</option>\n" +
        "            <option value=\"Date\"  name='DateI'>Birth Date</option>\n" +
        "        </select>\n"
    let rValueDry = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value,\"VSI\",\"SVF\")'>\n" +
        "            <option value=\"DiaryId\" name='IdI'>Diary ID</option>\n" +
        "            <option value=\"StudentId\" name='SIdI'>Student ID</option>\n" +
        "            <option value=\"ClassId\" name='IdI'>Class ID</option>\n" +
        "        </select>\n"
    let rValueCls = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value,\"VSI\",\"SVF\")'>\n" +
        "            <option value=\"ClassId\" name='IdI'>Class ID</option>\n" +
        "            <option value=\"Grade\" name='GradeI'>Grade</option>\n" +
        "            <option value=\"Sign\" name='SignI'>Sign</option>\n" +
        "            <option value=\"Year\" name='YearI'>Year</option>\n" +
        "            <option value=\"TeacherId\" name='IdI'>Teacher ID</option>\n" +
        "        </select>\n"
    let rValueSbj = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value,\"VSI\",\"SVF\")'>\n" +
        "            <option value=\"SubjectId\" name='IdI'>Subject ID</option>\n" +
        "            <option value=\"Name\" name='TextI'>Subject Name</option>\n" +
        "            <option value=\"TeacherId\" name='IdI'>Teacher ID</option>\n" +
        "        </select>\n"
    let rValueMrk = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value,\"VSI\",\"SVF\")'>\n" +
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

function getSpecificInputField(spec, place, ID) {
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
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"text\" />"
            break;
        case "StudentId":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"10000000000\" max=\"99999999999\" step=\"1\" />";
            break;
        case "TeacherId":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"0\"/>";
            break;
        case "SubjectId":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"0\"/>";
            break;
        case "MarkId":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"0\"/>";
            break;
        case "DiaryId":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"0\"/>";
            break;
        case "ClassId":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"0\"/>";
            break;
        case "Grade":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"0\" max=\"14\" step=\"1\" />";
            break;
        case "Date":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"date\"/>"
            break;
        case "Year":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"1900\" max=\"2099\" step=\"1\" value=\"2022\" />";
            break;
        case "Sign":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"text\" maxlength='1' pattern='[A-Z]'/>";
            break;
        case "Mark":
            document.getElementById(place).innerHTML =
                "<input id=\"" + ID + "\" type=\"number\" min=\"1\" max=\"5\" step=\"1\" value=\"5\" />";
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
    let url = "/tli/search/Student";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.alert("response arrive\n response:" + xhr.response)
        }
    }
    const search = {rFor: sFor, rWith: sWith, rBy: sBy, rValue: sVal};
    const data = JSON.stringify(search);
    xhr.send(data);
    window.alert(search);
}

function addMenuButton() {
    let toAddSelectField = "<tr>" +
        "    <td style='width: 50%'>Searching for :\n" +
        "        <select id=\"AFS\">\n" +
        "            <option value=\"Student\">Student</option>\n" +
        "            <option value=\"Diary\">Diary</option>\n" +
        "            <option value=\"Class\">Class</option>\n" +
        "            <option value=\"Subject\">Subject</option>\n" +
        "            <option value=\"Mark\">Mark</option>\n" +
        "        </select>\n" +
        "    </td>" +
        "<td>" +
        "<input id='AddselectConfirmButton' type='button' style='width: 150px' value='Confirm Select' onclick='addFieldGenerate(document.getElementById(\"AFS\").value)'>" +
        "</td>" +
        "</tr>" +
        "<tr id='AddDetailField'>" +
        "</tr>"
    document.getElementById("searchTable").innerHTML = toAddSelectField;

}

function addFieldGenerate(select) {
    let studentCase = "<td id='c1'>Student ID : " + getSpecificInputField("StudentId", "c1", "ASId") + "</td>" +
        "<td id='c2'>Student Name : " + getSpecificInputField("Name", "c2", "ASN") + "</td>" +
        "<td id='c3'> Birth Date : " + getSpecificInputField("Date", "c3", "ASD") + "</td>" +
        "<td><input type='button' style='width: 150px' value='ADD' " +
        "onclick='confirmAdding(document.getElementById(\"AFS\").value)'></td>"

    let diaryCase = "<td id='c1'>Diary ID : " + getSpecificInputField("DiaryId", "c1", "ADId") + "</td>" +
        "<td id='c2'>Student ID : " + getSpecificInputField("StudentId", "c2", "ADS") + "</td>" +
        "<td id='c3'> Class ID : " + getSpecificInputField("ClassId", "c3", "ADC") + "</td>" +
        "<td><input type='button' style='width: 150px' value='ADD' " +
        "onclick='confirmAdding(document.getElementById(\"AFS\").value)'></td>"

    switch (select) {
        case "Student" :
            document.getElementById("searchTable2").innerHTML = studentCase
        case "Diary":
            document.getElementById("searchTable2").innerHTML = diaryCase
    }


}

function confirmAdding() {
    let sFor = document.getElementById("SFS").value
    let sWith = document.getElementById("SWS").value
    let sBy = document.getElementById("SBS").value
    let sVal = document.getElementById("SVF").value

    let xhr = new XMLHttpRequest();
    let url = "/tli/search/Student";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.alert("response arrive\n response:" + xhr.response)
        }
    }
    const search = {rFor: sFor, rWith: sWith, rBy: sBy, rValue: sVal};
    const data = JSON.stringify(search);
    xhr.send(data);
    window.alert(search);


