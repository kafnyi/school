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
    let url = "/api/v1/searchStudent";
    xhr.open("POST", url, true);
    if (xhr.readyState === 4 && xhr.status === 200) {
        const search = {ID: id, Name: name, Birth: birth, Diary: diary};
        const data = JSON.stringify(search);
        xhr.send(data);
        let response = xhr.responseText;
        let jsonResp = JSON.parse(response);
        return jsonResp;
    }

}

function createResponseTable(json) {
    var i;
    var response = json.response;
    var table = "<tr><th>Card Number</th><th>Name</th></tr>";
    var x = response.getElementsByTagName("CD");
    for (i = 0; i < x.length; i++) {
        table += "<tr><td>" +
            x[i].getElementsByTagName("ARTIST")[0].childNodes[0].nodeValue +
            "</td><td>" +
            x[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue +
            "</td></tr>";
    }
}
