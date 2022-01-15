// A re-implementation of "mutool merge" in JavaScript.

function graftObject(dstDoc, srcDoc, srcObj, map) {
    var srcNum, dstRef, dstObj;
    if (!map)
	map = [];
    if (srcObj.isIndirect()) {
	srcNum = srcObj.asIndirect();
	if (map[srcNum])
	    return map[srcNum];
	map[srcNum] = dstRef = dstDoc.createObject();
	dstRef.writeObject(graftObject(dstDoc, srcDoc, srcObj.resolve(), map));
	if (srcObj.isStream())
	    dstRef.writeRawStream(srcObj.readRawStream());
	return dstRef;
    }
    if (srcObj.isArray()) {
	dstObj = dstDoc.newArray();
	srcObj.forEach(function (key, val) {
	    dstObj[key] = graftObject(dstDoc, srcDoc, val, map);
	});
	return dstObj;
    }
    if (srcObj.isDictionary()) {
	dstObj = dstDoc.newDictionary();
	srcObj.forEach(function (key, val) {
	    dstObj[key] = graftObject(dstDoc, srcDoc, val, map);
	});
	return dstObj;
    }
    return srcObj; /* primitive objects are not bound to a document */
}

function copyPage(dstDoc, srcDoc, pageNumber, map) {
    var srcPage, dstPage;
    srcPage = srcDoc.findPage(pageNumber);
    dstPage = dstDoc.newDictionary();
    dstPage.Type = dstDoc.newName("Page");
    if (srcPage.MediaBox)
        dstPage.MediaBox = graftObject(dstDoc, srcDoc, srcPage.MediaBox, map);
    if (srcPage.Rotate)
        dstPage.Rotate = graftObject(dstDoc, srcDoc, srcPage.Rotate, map);
    if (srcPage.Resources)
        dstPage.Resources = graftObject(dstDoc, srcDoc, srcPage.Resources, map);
    if (srcPage.Contents)
        dstPage.Contents = graftObject(dstDoc, srcDoc, srcPage.Contents, map);
    dstDoc.insertPage(-1, dstDoc.addObject(dstPage));
}

function copyPageLabels(dstDoc, srcDoc, map) {
    var srcRoot = srcDoc.getTrailer().Root;
    var dstRoot = dstDoc.getTrailer().Root;
    var dstPageLabels = dstRoot.PageLabels;
    var dstNums = dstPageLabels.Nums;
    if ("PageLabels" in srcRoot) {
        var srcPageLabels = srcRoot.PageLabels;
        var srcNums = srcPageLabels.Nums;
        for (var i = 0; i < srcNums.length; i += 2) {
            dstNums.push(dstDoc.countPages() + srcNums[i]);
            dstNums.push(graftObject(dstDoc, srcDoc, srcNums[i+1], map));
        }
    }
}

function copyOutlines(dstDoc, srcDoc, map) {
    var srcRoot = srcDoc.getTrailer().Root;
    var dstRoot = dstDoc.getTrailer().Root;
    if ('Outlines' in srcRoot) {
        dstRoot.Outlines = graftObject(dstDoc, srcDoc, srcRoot.Outlines, map);
    }
}

function copyAllPages(dstDoc, srcDoc) {
    var k, n = srcDoc.countPages();
    var srcMap = [];
    copyPageLabels(dstDoc, srcDoc, srcMap);
    for (k = 0; k < n; ++k)
	copyPage(dstDoc, srcDoc, k, srcMap);
    // copyOutlines(dstDoc, srcDoc, srcMap);
}

function pdfMerge() {
    var srcDoc, dstDoc, i;

    dstDoc = new PDFDocument();
    var pageLabels = dstDoc.createObject();
    pageLabels.writeObject(dstDoc.newDictionary());
    pageLabels.Nums = dstDoc.newArray();
    dstDoc.getTrailer().Root.PageLabels = pageLabels;
    for (i = 2; i < argv.length; ++i) {
	srcDoc = new PDFDocument(argv[i]);
	copyAllPages(dstDoc, srcDoc);
    }
    dstDoc.save(argv[1], "compress");
}

if (typeof argv === 'undefined') {
    var argv = [""].concat(scriptArgs);
}

if (argv.length < 3)
    print("usage: mutool run pdf-merge.js output.pdf input1.pdf input2.pdf ...");
else
    pdfMerge();
