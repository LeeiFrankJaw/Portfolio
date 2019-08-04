console.log(
    $$('tr.odd, tr.even')
        .map(y =>
             Array.from(y.querySelectorAll('td'))
             .filter((_, i) => ![2, 3].includes(i))
             .map(x => x.innerText)
             .join('\t'))
        .join('\n')
);
