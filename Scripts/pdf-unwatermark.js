function pdfUnwatermark() {
    var srcDoc;

    srcDoc = new PDFDocument(argv[1]);

    var startPage = 0,
        endPage = srcDoc.countPages();

    var srcPage, keys;
    for (var i = startPage; i < endPage; i++) {
        srcPage = srcDoc.findPage(i);
        keys = [];
        if (srcPage.Resources.XObject === undefined) {
            break;
        }
        srcPage.Resources.XObject.forEach(function (key, val) {
            if (val.Subtype == 'Form'
                && val.PieceInfo
                && val.PieceInfo.ADBE_CompoundType
                && val.PieceInfo.ADBE_CompoundType.Private == 'Watermark') {
                keys.push(key);
            }
        });
        keys.forEach(function (key) {
            srcPage.Resources.XObject.delete(key);
        });
        if (srcPage.Contents.length > 1) {
            srcPage.Contents.delete(srcPage.Contents.length-1);
        }
        // if (srcPage.Contents === undefined) {
        //     break;
        // }
        // (function (cleanStream) {
        //     if (srcPage.Contents) {
        //         if (srcPage.Contents.isArray()) {
        //             srcPage.Contents.forEach(function (index, val) { cleanStream(val) });
        //         }
        //         if (srcPage.Contents.isStream()) {
        //             cleanStream(srcPage.Contents);
        //         }
        //     }
        // }(function (val) {
        //     var stream = val.readStream();
        //     var buffer = new Buffer(),
        //         statement = [];
        //     for (var i = 0; i < stream.length; i++) {
        //         var b = stream[i];
        //         var c = String.fromCharCode(b);
        //         if (c !== '/') {
        //             buffer.writeByte(b);
        //         } else {
        //             while ((c = String.fromCharCode(stream[++i])) !== ' ') {
        //                 statement.push(c);
        //             }
        //             var name = statement.join('')
        //             if (keys.some(function (key) {return key === name})) {
        //                 if (String.fromCharCode(stream[i+1], stream[i+2], stream[i+3]) !== 'Do\n') {
        //                     new Error('Form object should be followed by a Do operator');
        //                 }
        //                 i += 3;
        //             } else {
        //                 buffer.write('/' + name + ' ');
        //             }
        //             statement = [];
        //         }
        //     }
        //     val.writeStream(buffer);
        // }));
    }
    if (argv[2] === undefined) {
        argv.push('output.pdf');
    }
    srcDoc.save(argv[2], 'compress,garbage,sanitize');
}

if (typeof argv === 'undefined') {
    var argv = [""].concat(scriptArgs);
}

if (argv.length < 2) {
    print("usage: mutool run pdf-unwatermark.js input.pdf [output.pdf]");
} else {
    pdfUnwatermark();
}
