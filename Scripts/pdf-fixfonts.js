var srcDoc;

var mojibake = {
    '/#B7#BD#D5#FD#B7#C2#CB#CE_GBK': '方正仿宋_GBK',
    '/#B7#BD#D5#FD#BA#DA#CC#E5_GBK': '方正黑体_GBK',
    '/#B7#BD#D5#FD#BF#AC#CC#E5_GBK': '方正楷体_GBK',
    '/#B7#BD#D5#FD#CA#E9#CB#CE_GBK': '方正书宋_GBK',
    '/#BA#DA#CC#E5': '黑体',
    '/#CB#CE#CC#E5': '宋体',
    '/#B2#D3#A9#FA#C5#E9': '細明體',
    '/#A5#FE#AFu#B7#A2#AE#D1': '全真楷書',
    '/#A5#FE#AFu#A4#A4#B6#C2#C5#E9': '全真中黑體',
}

var ascii = {
    '方正黑体简体': 'FZHei-B01S',
}

var psname = {
    '方正书宋_GBK': 'FZSSK--GBK1-0',
    '方正仿宋_GBK': 'FZFSK--GBK1-0',
    '方正楷体_GBK': 'FZKTK--GBK1-0',
    '方正黑体_GBK': 'FZHTK--GBK1-0',
    '宋体': 'SimSun',
    '黑体': 'SimHei',
    '細明體': 'MingLiU',
    'DFKai-SB': 'DFKaiShu-SB-Estd-BF',
    // 'PingFang TC:Medium': 'PingFangTC-Medium',
    'TimesNewRoman': 'TimesNewRomanPSMT',
}

// var substitution = {
//     '全真楷書': 'DFKai-SB',
//     '全真中黑體': 'PingFang TC:Medium',
// }

function fixEncoding(val, key, obj, fname) {
    if (val[key][fname]() in obj) {
        var target = obj[val[key][fname]()];
        print(val[key] + '\t-->\t' + target);
        val[key] = target;
    }
}

function fixFamily(fd) {
    // fd.FontFamily = srcDoc.newString(fd.FontName.asName());
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
        // fixEncoding(val.DescendantFonts[0], 'BaseFont', substitution, 'asName');
        // fixEncoding(fd, 'FontName', substitution, 'asName');
        fixEncoding(val.DescendantFonts[0], 'BaseFont', psname, 'asName');
        fixEncoding(fd, 'FontName', psname, 'asName');
        fixFamily(fd);
    }
    if (val.Subtype.asName() === 'TrueType') {
        var fd = val.FontDescriptor;
        fixEncoding(val, 'BaseFont', psname, 'asName');
        fixEncoding(fd, 'FontName', psname, 'asName');
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
