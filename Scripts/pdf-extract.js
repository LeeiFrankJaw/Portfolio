
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


function pdfExtract() {
    var srcDoc, dstDoc;

    // initialize destination document
    dstDoc = new PDFDocument();
    var dstTrailer = dstDoc.getTrailer();
    var pageLabels = dstDoc.createObject();
    pageLabels.writeObject(dstDoc.newDictionary());
    pageLabels.Nums = dstDoc.newArray();
    dstTrailer.Root.PageLabels = pageLabels;
    
    // initialize source document
    srcDoc = new PDFDocument(argv[2]);
    var srcTrailer = srcDoc.getTrailer();

    // copy metadata
    dstTrailer.Info = graftObject(dstDoc, srcDoc, srcTrailer.Info);

    // initialize page range
    var startPage = parseInt(argv[3], 10);
    var endPage;
    if (argv[4] === undefined) {
        endPage = srcDoc.countPages();
    } else {
        endPage = parseInt(argv[4], 10);
    }

    var srcMap = [];
    for (var i = startPage-1 ; i < endPage; i++) {
        copyPage(dstDoc, srcDoc, i, srcMap);
    }

    dstDoc.save(argv[1], "compress");
}


if (argv.length < 4)
    print("usage: mutool run pdf-extract.js output.pdf input.pdf start_page [end_page]");
else
    pdfExtract();
