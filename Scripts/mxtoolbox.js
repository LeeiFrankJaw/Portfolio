console.log(
    $$('div.tool-result-div.lookup-type-asn:first-child .table-column-CIDR_Range')
        .map(x => "add dircidr " + x.innerText + ' comment "AS Number"')
        .join('\n')
);
