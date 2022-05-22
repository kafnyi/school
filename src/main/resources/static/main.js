
function profileButton() {

}

function searchMenuButton() {
    let searchField = "<tr>" +
        "    <td>Searching for :\n" +
        "        <select id=\"SFS\">\n" +
        "            <option value=\"Student\">Student</option>\n" +
        "            <option value=\"Diary\">Diary</option>\n" +
        "            <option value=\"Class\">Class</option>\n" +
        "            <option value=\"Subject\">Subject</option>\n" +
        "            <option value=\"Mark\">Mark</option>\n" +
        "        </select>\n" +
        "    </td>" +
        "    <td>Search with :\n" +
        "        <select id=\"SWS\">\n" +
        "            <option value=\"Student\" onselect='selectSClarifiedInnerRow(\"Student\")'>Student</option>\n" +
        "            <option value=\"Diary\" onselect='selectSClarifiedInnerRow(\"Diary\")'>Diary</option>\n" +
        "            <option value=\"Class\" onselect='selectSClarifiedInnerRow(\"Class\")'>Class</option>\n" +
        "            <option value=\"Subject\" onselect='selectSClarifiedInnerRow(\"Subject\")'>Subject</option>\n" +
        "            <option value=\"Mark\" onselect='selectSClarifiedInnerRow(\"Mark\")'>Mark</option>\n" +
        "        </select>\n" +
        "    </td>\n" +
        "    <td id=\"sClarifiedInnerRow\" name='sClarifiedInnerRow'>" +
        "<input type='button' value='Submit' onclick='selectSClarifiedInnerRow(document.getElementById(\"SWS\").value)'>" +
        "    </td>" +
        "</tr>"
    document.getElementById("searchTable").innerHTML = searchField
}

function selectSClarifiedInnerRow(selected) {
    let rValueStart = "<tr><td>"
    let rValueSB = "Search By :"
    let rValueSI
    let rValueStd = "        <select name=\"SBS\">\n" +
        "            <option value=\"studentId\" onselect='rValueSI=getSpecificInputField(1)'>Student ID</option>\n" +
        "            <option value=\"Name\" onselect='rValueSI=getSpecificInputField(0)'>Name</option>\n" +
        "            <option value=\"BirtheDate\" onselect='rValueSI=getSpecificInputField(4)'>Birth Date</option>\n" +
        "        </select>\n"
    let rValueDry = "        <select name=\"SBD\">\n" +
        "            <option value=\"diaryId\" onselect='rValueSI=getSpecificInputField(2)'>Diary ID</option>\n" +
        "            <option value=\"studentId\" onselect='rValueSI=getSpecificInputField(1)'>Student ID</option>\n" +
        "            <option value=\"classId\"onselect='rValueSI=getSpecificInputField(2)'>Class ID</option>\n" +
        "        </select>\n"
    let rValueCls = "        <select name=\"SBC\">\n" +
        "            <option value=\"classId\" onselect='rValueSI=getSpecificInputField(2)'>Class ID</option>\n" +
        "            <option value=\"grade\" onselect='rValueSI=getSpecificInputField(3)'>Grade</option>\n" +
        "            <option value=\"sign\" onselect='rValueSI=getSpecificInputField(6)'>Sign</option>\n" +
        "            <option value=\"year\" onselect='rValueSI=getSpecificInputField(5)'>Year</option>\n" +
        "            <option value=\"teacherId\" onselect='rValueSI=getSpecificInputField(2)'>Teacher ID</option>\n" +
        "        </select>\n"
    let rValueSbj = "        <select name=\"SBSj\">\n" +
        "            <option value=\"subjectId\" onselect='rValueSI=getSpecificInputField(2)'>Subject ID</option>\n" +
        "            <option value=\"subjectName\" onselect='rValueSI=getSpecificInputField(0)'>Subject Name</option>\n" +
        "            <option value=\"teacherId\" onselect='rValueSI=getSpecificInputField(2)'>Teacher ID</option>\n" +
        "        </select>\n"
    let rValueMrk = "        <select name=\"SBS\">\n" +
        "            <option value=\"markId\" onselect='rValueSI=getSpecificInputField(2)'>Mark ID</option>\n" +
        "            <option value=\"diaryId\" onselect='rValueSI=getSpecificInputField(2)'>Diary ID</option>\n" +
        "            <option value=\"date\" onselect='rValueSI=getSpecificInputField(4)'>Date</option>\n" +
        "            <option value=\"subjectId\"onselect='rValueSI=getSpecificInputField(2)'>Subject ID</option>\n" +
        "            <option value=\"Mark\" onselect='rValueSI=getSpecificInputField(7)'>Mark</option>\n" +
        "        </select>\n"
    let rValueStop = "</td></tr>"

    let resValue = "";
    if (selected != Student) {
        if (selected != Diary) {
            if (selected != Class) {
                if (selected != Subject) {
                    if (selected != Mark) {
                        resValue = "<td> please select what you want to search by </td> "
                        document.getElementById("searchTable2").innerHTML = resValue
                        return document.getElementById("searchTable2").innerHTML = resValue;
                    }
                    resValue += rValueStart + rValueMrk + rValueStop
                    return document.getElementById("searchTable2").innerHTML = resValue;
                }
                resValue += rValueStart + rValueSbj + rValueStop
                return document.getElementById("searchTable2").innerHTML = resValue;
            }
            resValue += rValueStart + rValueCls + rValueStop
            return document.getElementById("searchTable2").innerHTML = resValue;
        }
        resValue += rValueStart + rValueDry + rValueStop
        return document.getElementById("searchTable2").innerHTML = resValue;
    }
    resValue += rValueStart + rValueStd + rValueStop
    return document.getElementById("searchTable2").innerHTML = resValue;

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
        case 0:
            document.getElementById("VSI").innerHTML = "<input type=\"text\" />"
            break;
        case 1:
            document.getElementById("VSI").innerHTML =
                "<input type=\"number\" min=\"10000000000\" max=\"99999999999\" step=\"1\" />";
            break;
        case 2:
            document.getElementById("VSI").innerHTML =
                "<input type=\"number\" min=\"0\"/>";
            break;
        case 3:
            document.getElementById("VSI").innerHTML =
                "<input type=\"number\" min=\"0\" max=\"14\" step=\"1\" />";
            break;
        case 4:
            document.getElementById("VSI").innerHTML =
                "<input type=\"date\"/>"
            break;
        case 5:
            document.getElementById("VSI").innerHTML =
                "<input type=\"number\" min=\"1900\" max=\"2099\" step=\"1\" value=\"2022\" />";
            break;
        case 6:
            document.getElementById("VSI").innerHTML =
                "<input type=\"text\" maxlength='1' pattern='[A-Z]'/>";
            break;
        case 7:
            document.getElementById("VSI").innerHTML =
                "<input type=\"number\" min=\"1\" max=\"5\" step=\"1\" value=\"5\" />";
            return;
        default:
            window.alert("Error! Missing SearchInputSpecification!")
            return
    }
}