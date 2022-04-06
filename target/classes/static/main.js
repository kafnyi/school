function FP_preloadImgs() {//v1.0
    var d = document, a = arguments;
    if (!d.FP_imgs) d.FP_imgs = [];
    for (var i = 0; i < a.length; i++) {
        d.FP_imgs[i] = new Image;
        d.FP_imgs[i].src = a[i];
    }
}

function FP_swapImg() {//v1.0
    var doc = document, args = arguments, elm, n;
    doc.$imgSwaps = new Array();
    for (n = 2; n < args.length;
         n += 2) {
        elm = FP_getObjectByID(args[n]);
        if (elm) {
            doc.$imgSwaps[doc.$imgSwaps.length] = elm;
            elm.$src = elm.src;
            elm.src = args[n + 1];
        }
    }
}

function FP_getObjectByID(id, o) {//v1.0
    var c, el, els, f, m, n;
    if (!o) o = document;
    if (o.getElementById) el = o.getElementById(id);
    else if (o.layers) c = o.layers; else if (o.all) el = o.all[id];
    if (el) return el;
    if (o.id == id || o.name == id) return o;
    if (o.childNodes) c = o.childNodes;
    if (c)
        for (n = 0; n < c.length; n++) {
            el = FP_getObjectByID(id, c[n]);
            if (el) return el;
        }
    f = o.forms;
    if (f) for (n = 0; n < f.length; n++) {
        els = f[n].elements;
        for (m = 0; m < els.length; m++) {
            el = FP_getObjectByID(id, els[n]);
            if (el) return el;
        }
    }
    return null;
}

function searchForStudent(id, name, birth, diary) {
    let xhr = new XMLHttpRequest();
    let url = "/api/v1/studentSearch";
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let response = xhr.responseText;
            let jsonResp = JSON.parse(response);
            return jsonResp;
        }
    }
        const search = {ID: id, Name: name, Birth: birth, Diary: diary};
        const data = JSON.stringify(search);
    xhr.send(data);
}

function createResponseTable(json) {
    var i;
    var response = json.response;
    var table = "<tr><th>Card Number</th><th>Name</th><th>Birth date</th><th>Diary ID</th></tr>";
    var x = response.getElementsByTagName("ID");
    for (i = 0; i < x.length; i++) {
        table += "<tr><td>" +
            x[i].getElementsByTagName("ID")[0].childNodes[0].nodeValue +
            "</td><td>" +
            x[i].getElementsByTagName("SName")[0].childNodes[0].nodeValue +
            "</td><td>" +
            x[i].getElementsByTagName("SBirth")[0].childNodes[0].nodeValue +
            "</td><td>" +
            x[i].getElementsByTagName("SDiary")[0].childNodes[0].nodeValue +
            "</td></tr>";
    }
    return table;
}
