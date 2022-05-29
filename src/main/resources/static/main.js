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
        "        <select id=\"SWS\" onchange='selectSClarifiedInnerRow(document.getElementById(\"SWS\").value); getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
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
    getSpecificInputField(document.getElementById("SBS").value)
}

function selectSClarifiedInnerRow(selected) {
    let rValueSB = "Search By :"
    let rValueSI
    let rValueStd = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"SIdI\" >Student ID</option>\n" +
        "            <option value=\"TxtI\" >Name</option>\n" +
        "            <option value=\"DtI\"  >Birth Date</option>\n" +
        "        </select>\n"
    let rValueDry = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"NmbI\" >Diary ID</option>\n" +
        "            <option value=\"SIdI\" >Student ID</option>\n" +
        "            <option value=\"NmbI\" >Class ID</option>\n" +
        "        </select>\n"
    let rValueCls = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"NmbI\" >Class ID</option>\n" +
        "            <option value=\"GrdI\" >Grade</option>\n" +
        "            <option value=\"SgnI\" >Sign</option>\n" +
        "            <option value=\"NYrI\" >Year</option>\n" +
        "            <option value=\"NmbI\" >Teacher ID</option>\n" +
        "        </select>\n"
    let rValueSbj = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"NmbI\" >Subject ID</option>\n" +
        "            <option value=\"TxtI\" >Subject Name</option>\n" +
        "            <option value=\"NmbI\" >Teacher ID</option>\n" +
        "        </select>\n"
    let rValueMrk = "        <select id=\"SBS\" onchange='getSpecificInputField(document.getElementById(\"SBS\").value)'>\n" +
        "            <option value=\"NmbI\" >Mark ID</option>\n" +
        "            <option value=\"NmbI\" >Diary ID</option>\n" +
        "            <option value=\"DtI\" >Date</option>\n" +
        "            <option value=\"NmbI\" >Subject ID</option>\n" +
        "            <option value=\"MrkI\" >Mark</option>\n" +
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
        case "TxtI":
            document.getElementById("VSI").innerHTML =
                "<input id='SVF' type=\"text\" />"
            break;
        case "SIdI":
            document.getElementById("VSI").innerHTML =
                "<input id='SVF' type=\"number\" min=\"10000000000\" max=\"99999999999\" step=\"1\" />";
            break;
        case "NmbI":
            document.getElementById("VSI").innerHTML =
                "<input id='SVF' type=\"number\" min=\"0\"/>";
            break;
        case "GrdI":
            document.getElementById("VSI").innerHTML =
                "<input id='SVF' type=\"number\" min=\"0\" max=\"14\" step=\"1\" />";
            break;
        case "DtI":
            document.getElementById("VSI").innerHTML =
                "<input id='SVF' type=\"date\"/>"
            break;
        case "NYrI":
            document.getElementById("VSI").innerHTML =
                "<input id='SVF' type=\"number\" min=\"1900\" max=\"2099\" step=\"1\" value=\"2022\" />";
            break;
        case "SgnI":
            document.getElementById("VSI").innerHTML =
                "<input id='SVF' type=\"text\" maxlength='1' pattern='[A-Z]'/>";
            break;
        case "MrkI":
            document.getElementById("VSI").innerHTML =
                "<input id='SVF' type=\"number\" min=\"1\" max=\"5\" step=\"1\" value=\"5\" />";
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
    window.alert("the confirmed search is :" + sFor + ";" + sWith + ";" + sBy + ";" + sVal);
}