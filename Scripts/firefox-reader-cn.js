var c = document.querySelector('.container');
c.lang = "zh-CN";
c.innerHTML = c.innerHTML.replaceAll('。”', '<span class="songhw3punct">。</span>”');
c.innerHTML = c.innerHTML.replaceAll('《', '<span class="adobe-guillemet">《</span>');
c.innerHTML = c.innerHTML.replaceAll('》', '<span class="adobe-guillemet">》</span>');
c.innerHTML = c.innerHTML.replaceAll('·', '<span class="interpunct">・</span>');
// c.innerHTML = c.innerHTML.replaceAll('　', '');
// c.innerHTML = c.innerHTML.replaceAll('&nbsp;', '');

// var ss = document.querySelectorAll('section:has(img:only-child)');
// ss.forEach((s) => {s.outerHTML = s.outerHTML.replaceAll('<section>', '<p>').replaceAll('</section>', '</p>')});

// var bs = document.querySelectorAll('.reader-title, strong');
// bs.forEach((b) => {b.innerHTML = b.innerHTML.replaceAll('？！', '<span class="pf-pwid">？</span>！')});

// c.innerHTML = c.innerHTML.replaceAll('( ', '(');
// c.innerHTML = c.innerHTML.replaceAll(' )', ')');

// c.innerHTML = c.innerHTML.replaceAll('(', '（');
// c.innerHTML = c.innerHTML.replaceAll(')', '）');
// c.innerHTML = c.innerHTML.replaceAll('i）', 'i） ');
// c.innerHTML = c.innerHTML.replaceAll('[', '［');
// c.innerHTML = c.innerHTML.replaceAll(']', '］');

// c.innerHTML = c.innerHTML.replaceAll(', ', '，');
// c.innerHTML = c.innerHTML.replaceAll(',', '，');
// c.innerHTML = c.innerHTML.replaceAll(':', '：');
// c.innerHTML = c.innerHTML.replaceAll('. ', '．');
// c.innerHTML = c.innerHTML.replaceAll(' <span>', '<span>');
