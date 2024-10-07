var srcDoc;

var mojibake = {
    '/#B7#BD#D5#FD#B7#C2#CB#CE_GBK': '方正仿宋_GBK',
    '/#B7#BD#D5#FD#BA#DA#CC#E5_GBK': '方正黑体_GBK',
    '/#B7#BD#D5#FD#BF#AC#CC#E5_GBK': '方正楷体_GBK',
    '/#B7#BD#D5#FD#CA#E9#CB#CE_GBK': '方正书宋_GBK',
    '/#BA#DA#CC#E5': '黑体',
    '/#CB#CE#CC#E5': '宋体'
}

var ascii = {
    '方正黑体简体': 'FZHei-B01S'
}

var psname = {
    '方正书宋_GBK': 'FZSSK--GBK1-0',
    '方正仿宋_GBK': 'FZFSK--GBK1-0',
    '方正楷体_GBK': 'FZKTK--GBK1-0',
    '方正黑体_GBK': 'FZHTK--GBK1-0',
    '宋体': 'SimSun',
    '黑体': 'SimHei'
}

function fixEncoding(val, key, obj, fname) {
    if (val[key][fname]() in obj) {
        var target = obj[val[key][fname]()];
        print(val[key] + '\t-->\t' + target);
        val[key] = target;
    }
}

function fixFamily(fd) {
    if (typeof fd.FontFile2 === 'undefined'
        && typeof fd.FontFamily !== 'undefined'
        && fd.FontFamily.toString()[0] === '<'
        && fd.FontFamily.asString() in ascii) {
        var target = ascii[fd.FontFamily.asString()];
        print(fd.FontFamily.asString() + '\t-->\t' + target);
        fd.FontFamily = srcDoc.newString(target);
    }
}

function fixFont(val, key) {
    if (val.Subtype.asName() === 'Type0'
        && val.DescendantFonts[0].Subtype.asName() === 'CIDFontType2') {
        var fd = val.DescendantFonts[0].FontDescriptor;
        fixEncoding(val, 'BaseFont', mojibake, 'toString');
        fixEncoding(val.DescendantFonts[0], 'BaseFont', mojibake, 'toString');
        fixEncoding(fd, 'FontName', mojibake, 'toString');
        fixEncoding(val.DescendantFonts[0], 'BaseFont', psname, 'asName');
        fixEncoding(fd, 'FontName', psname, 'asName');
        fixFamily(fd);
    }
}

function pdfFixfonts() {
    // initialize source document
    srcDoc = new PDFDocument(scriptArgs[0]);

    var startPage = 0,
        endPage = srcDoc.countPages();

    var srcPage;
    for (var i = startPage; i < endPage; i++) {
        srcPage = srcDoc.findPage(i);
        srcPage.Resources.Font.forEach(fixFont);
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
