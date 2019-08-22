console.log(
    $$('tr.odd, tr.even')
        .map(y =>
             Array.from(y.querySelectorAll('td'))
             .filter((_, i) => ![2, 3].includes(i))
             .map(x => x.innerText)
             .map((t, i) => {
                 switch (i) {
                 case 0:
                     t = t + '.';
                     break;
                 // case 2:
                 //     t = '- ' + t;
                 //     break;
                 case 2:
                     t = `(${t})`;
                     break;
                 default:
                     break;
                 }
                 return t;
             })
             .join(' '))
        .join('\n')
);
