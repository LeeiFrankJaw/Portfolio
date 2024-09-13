var mojibake = {
    '/#B7#BD#D5#FD#B7#C2#CB#CE_GBK': '方正仿宋_GBK',
    '/#B7#BD#D5#FD#BA#DA#CC#E5_GBK': '方正黑体_GBK',
    '/#B7#BD#D5#FD#BF#AC#CC#E5_GBK': '方正楷体_GBK',
    '/#B7#BD#D5#FD#CA#E9#CB#CE_GBK': '方正书宋_GBK',
    '/#CB#CE#CC#E5': '宋体'
}

function pdfFixfonts() {
    var srcDoc;

    // initialize source document
    srcDoc = new PDFDocument(scriptArgs[0]);

    var startPage = 0,
        endPage = srcDoc.countPages();

    var srcPage, keys;
    for (var i = startPage; i < endPage; i++) {
        srcPage = srcDoc.findPage(i);
        keys = [];
        srcPage.Resources.Font.forEach(function (val, key) {
            // if (val.BaseFont.toString().slice(0,2) === '/#')
            if (val.BaseFont.toString() in mojibake)
                val.BaseFont = mojibake[val.BaseFont.toString()];
        });
    }

    if (scriptArgs[1] === undefined) {
        scriptArgs.push('output.pdf');
    }
    srcDoc.save(scriptArgs[1], 'compress,garbage,sanitize');
}

if (scriptArgs.length < 1) {
    print('usage: mutool run pdf-fixfonts.js input.pdf [output.pdf]');
    // scriptArgs.forEach(function (elem, idx) {print(elem)});
} else
    pdfFixfonts();