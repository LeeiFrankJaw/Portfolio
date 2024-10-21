ATTACH DATABASE '<Calibre Library>/metadata.db' AS working;

.mode line
-- .timer on
-- SELECT MB.sort,
--        MB.author_sort
--   FROM books AS MB,
--        working.books AS WB
--  WHERE MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--  LIMIT 1;

.print
.print --------------------------------
.print Check if there are any new items
.print --------------------------------

SELECT sort AS title_sort, author_sort, id
  FROM working.books
 WHERE author_sort NOT IN
       (SELECT author_sort FROM books)
    OR sort NOT IN
       (SELECT sort FROM books)
 ORDER BY id DESC;

.print
.print --------------------------
.print Check author link mismatch
.print --------------------------

SELECT name AS author, MA.link, WA.link
  FROM authors AS MA
 INNER JOIN working.authors WA USING(name)
 WHERE MA.link <> WA.link;

-- .print
-- .print ------------------------------
-- .print Check tag mismatch (version 1)
-- .print ------------------------------

-- SELECT MB.sort, MB.author_sort, MB.id, WB.id
--   FROM books AS MB,
--        working.books AS WB
--  WHERE MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--    AND (SELECT count(*) > 0
--           FROM tags AS MT2,
--                books_tags_link AS MBT2,
--                working.tags AS WT2,
--                working.books_tags_link AS WBT2
--          WHERE MBT2.tag = MT2.id
--            AND WBT2.tag = WT2.id
--            AND MBT2.book = MB.id
--            AND WBT2.book = WB.id
--            AND (MT2.name NOT IN
--                 (SELECT name
--                    FROM working.tags AS WT3,
--                         working.books_tags_link AS WBT3
--                   WHERE WBT3.tag = WT3.id
--                     AND WBT3.book = WB.id)
--                 OR
--                 WT2.name NOT IN
--                 (SELECT name
--                    FROM tags AS MT3,
--                         books_tags_link AS MBT3
--                   WHERE MBT3.tag = MT3.id
--                     AND MBT3.book = MB.id)));

-- .print
-- .print ------------------------------
-- .print Check tag mismatch (version 2)
-- .print ------------------------------

-- SELECT MB.sort, MB.author_sort, MT.name, WT.name
--   FROM books AS MB,
--        working.books AS WB,
--        tags AS MT,
--        books_tags_link AS MBT,
--        working.tags AS WT,
--        working.books_tags_link AS WBT
--  WHERE MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--    AND MBT.tag = MT.id
--    AND WBT.tag = WT.id
--    AND MBT.book = MB.id
--    AND WBT.book = WB.id
--  GROUP BY MB.id, WB.id, MT.name, WT.name
-- HAVING MT.name NOT IN
--        (SELECT name
--           FROM working.tags AS WT2,
--                working.books_tags_link AS WBT2
--          WHERE WBT2.tag = WT2.id
--            AND WBT2.book = WB.id)
--     OR WT.name NOT IN
--        (SELECT name
--           FROM tags AS MT2,
--                books_tags_link AS MBT2
--          WHERE MBT2.tag = MT2.id
--            AND MBT2.book = MB.id);

-- .print
-- .print ------------------------------
-- .print Check tag mismatch (version 3)
-- .print ------------------------------

-- SELECT MB.sort, MB.author_sort, MT.name, WT.name
--   FROM books AS MB,
--        working.books AS WB,
--        tags AS MT,
--        books_tags_link AS MBT,
--        working.tags AS WT,
--        working.books_tags_link AS WBT
--  WHERE MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--    AND MBT.tag = MT.id
--    AND WBT.tag = WT.id
--    AND MBT.book = MB.id
--    AND WBT.book = WB.id
--    AND (MT.name NOT IN
--         (SELECT name
--            FROM working.tags AS WT2,
--                 working.books_tags_link AS WBT2
--           WHERE WBT2.tag = WT2.id
--             AND WBT2.book = WB.id)
--         OR WT.name NOT IN
--         (SELECT name
--            FROM tags AS MT2,
--                 books_tags_link AS MBT2
--           WHERE MBT2.tag = MT2.id
--             AND MBT2.book = MB.id));

-- .print
-- .print ------------------------------
-- .print Check tag mismatch (version 4)
-- .print ------------------------------

-- SELECT MB.sort, MB.author_sort, MB.id, MT.name AS tag, WB.id, WT.name AS tag
--   FROM books AS MB
--  INNER JOIN working.books AS WB
--     ON MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--   LEFT OUTER JOIN books_tags_link AS MBT
--     ON MBT.book = MB.id
--   LEFT OUTER JOIN tags AS MT
--     ON MBT.tag = MT.id
--   LEFT OUTER JOIN working.books_tags_link AS WBT
--     ON WBT.book = WB.id
--   LEFT OUTER JOIN working.tags AS WT
--     ON WBT.tag = WT.id
--  WHERE
--    NOT (MBT.tag IS NULL
--         AND WBT.tag IS NULL)
--    AND (MT.name NOT IN
--         (SELECT name
--            FROM working.tags AS WT2,
--                 working.books_tags_link AS WBT2
--           WHERE WBT2.tag = WT2.id
--             AND WBT2.book = WB.id)
--         OR WT.name NOT IN
--         (SELECT name
--            FROM tags AS MT2,
--                 books_tags_link AS MBT2
--           WHERE MBT2.tag = MT2.id
--             AND MBT2.book = MB.id));

-- SELECT MB.sort, MB.author_sort, MT.name, WT.name
--   FROM books AS MB
--  INNER JOIN working.books AS WB
--     ON MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--   LEFT OUTER JOIN books_tags_link AS MBT
--     ON MBT.book = MB.id
--   LEFT OUTER JOIN tags AS MT
--     ON MBT.tag = MT.id
--   LEFT OUTER JOIN working.books_tags_link AS WBT
--     ON WBT.book = WB.id
--   LEFT OUTER JOIN working.tags AS WT
--     ON WBT.tag = WT.id
--  WHERE
--    NOT (MBT.tag IS NULL
--         AND WBT.tag IS NULL
--          OR MT.name IN
--             (SELECT name
--                FROM working.tags AS WT2,
--                     working.books_tags_link AS WBT2
--               WHERE WBT2.tag = WT2.id
--                 AND WBT2.book = WB.id)
--         AND WT.name IN
--             (SELECT name
--                FROM tags AS MT2,
--                     books_tags_link AS MBT2
--               WHERE MBT2.tag = MT2.id
--                 AND MBT2.book = MB.id));

.print
.print ------------------------------
.print Check tag mismatch (version 5)
.print ------------------------------

SELECT MB.sort, MB.author_sort, MB.id, MT.name AS tag, WB.id, NULL AS tag
  FROM books AS MB
 INNER JOIN working.books AS WB
    ON MB.sort = WB.sort
   AND MB.author_sort = WB.author_sort
  LEFT OUTER JOIN books_tags_link AS MBT
    ON MBT.book = MB.id
  LEFT OUTER JOIN tags AS MT
    ON MBT.tag = MT.id
 WHERE MT.name IS NOT NULL
   AND MT.name NOT IN
        (SELECT name
           FROM working.tags AS WT,
                working.books_tags_link AS WBT
          WHERE WBT.tag = WT.id
            AND WBT.book = WB.id)
 UNION
SELECT MB.sort, MB.author_sort, MB.id, NULL AS tag, WB.id, WT.name AS tag
  FROM books AS MB
 INNER JOIN working.books AS WB
    ON MB.sort = WB.sort
   AND MB.author_sort = WB.author_sort
  LEFT OUTER JOIN working.books_tags_link AS WBT
    ON WBT.book = WB.id
  LEFT OUTER JOIN working.tags AS WT
    ON WBT.tag = WT.id
 WHERE WBT.tag IS NOT NULL
   AND WT.name NOT IN
        (SELECT name
           FROM tags AS MT,
                books_tags_link AS MBT
          WHERE MBT.tag = MT.id
            AND MBT.book = MB.id);

.print
.print -----------------------
.print Check tag link mismatch
.print -----------------------

SELECT name AS tag, MT.link, WT.link
  FROM tags AS MT
 INNER JOIN working.tags AS WT USING(name)
 WHERE MT.link <> WT.link;

.print
.print ----------------------
.print Check pubdate mismatch
.print ----------------------

SELECT MB.sort, MB.author_sort, MB.id, MB.pubdate, WB.id, WB.pubdate
  FROM books AS MB,
       working.books AS WB
 WHERE MB.sort = WB.sort
   AND MB.author_sort = WB.author_sort
   AND datetime(MB.pubdate) <> datetime(WB.pubdate)
 ORDER BY MB.id DESC;

.print
.print ------------------------
.print Check publisher mismatch
.print ------------------------

SELECT MB.sort, MB.author_sort, MB.id, MP.name as publisher, WB.id, WP.name as publisher
  FROM books AS MB
  LEFT OUTER JOIN books_publishers_link AS MBP
    ON MBP.book = MB.id
  LEFT OUTER JOIN publishers AS MP
    ON MBP.publisher = MP.id
 INNER JOIN working.books AS WB
    ON MB.sort = WB.sort
   AND MB.author_sort = WB.author_sort
  LEFT OUTER JOIN working.books_publishers_link AS WBP
    ON WBP.book = WB.id
  LEFT OUTER JOIN working.publishers AS WP
    ON WBP.publisher = WP.id
 WHERE
   NOT (MP.name IS NULL AND WP.name IS NULL)
   AND (MP.name IS NULL OR WP.name IS NULL
        OR MP.name <> WP.name);

.print
.print -----------------------------
.print Check publisher link mismatch
.print -----------------------------

SELECT name, MP.link, WP.link
  FROM publishers AS MP
 INNER JOIN working.publishers AS WP USING(name)
 WHERE MP.link <> WP.link;

.print
.print ---------------------
.print Check series mismatch
.print ---------------------

SELECT MB.sort, MB.author_sort, MB.id, MS.name, WB.id, WS.name
  FROM books AS MB
 INNER JOIN working.books AS WB
    ON MB.sort = WB.sort
   AND MB.author_sort = WB.author_sort
  LEFT OUTER JOIN books_series_link AS MBS
    ON MBS.book = MB.id
  LEFT OUTER JOIN series AS MS
    ON MBS.series = MS.id
  LEFT OUTER JOIN working.books_series_link AS WBS
    ON WBS.book = WB.id
  LEFT OUTER JOIN working.series AS WS
    ON WBS.series = WS.id
 WHERE
   NOT (MS.name IS NULL AND WS.name IS NULL)
   AND (MS.name IS NULL OR WS.name IS NULL
        OR MS.name <> WS.name);

.print
.print --------------------------
.print Check series link mismatch
.print --------------------------

SELECT name AS series, MS.link, WS.link
  FROM series AS MS
 INNER JOIN working.series AS WS USING(name)
 WHERE MS.link <> WS.link;

-- .print
-- .print -------------------------
-- .print Check identifier mismatch
-- .print -------------------------

-- SELECT MB.sort, MB.author_sort, MB.id, MI.type, MI.val, WB.id, WI.type, WI.val
--   FROM books AS MB
--   LEFT OUTER JOIN identifiers AS MI
--     ON MI.book = MB.id
--  INNER JOIN working.books AS WB
--     ON MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--   LEFT OUTER JOIN working.identifiers AS WI
--     ON WI.book = WB.id
--  WHERE
--    NOT (MI.id IS NULL AND WI.id IS NULL)
--    AND (MI.type NOT IN
--         (SELECT type
--            FROM working.identifiers AS WI2
--           WHERE WI2.book = WB.id)
--         OR WI.type NOT IN
--         (SELECT type
--            FROM identifiers AS MI2
--           WHERE MI2.book = MB.id)
--         OR MI.type = WI.type AND MI.val <> WI.val)
--  ORDER BY WB.id DESC;

.print
.print -------------------------------------
.print Check identifier mismatch (version 2)
.print -------------------------------------

WITH B AS (SELECT MB.sort AS title_sort, MB.author_sort,
                  MB.id AS id1, WB.id AS id2
             FROM books AS MB,
                  working.books AS WB
            WHERE MB.sort = WB.sort
              AND MB.author_sort = WB.author_sort)
SELECT title_sort, author_sort,
       id1 AS id, type, val,
       id2 AS id, NULL AS type, NULL AS val
  FROM B
 INNER JOIN identifiers AS MI
    ON MI.book = id1
 WHERE NOT EXISTS
       (SELECT *
          FROM working.identifiers
         WHERE book = id2
           AND type = MI.type)
 UNION
SELECT title_sort, author_sort,
       id1 AS id, NULL AS type, NULL AS val,
       id2 AS id, type, val
  FROM B
 INNER JOIN working.identifiers AS WI
    ON WI.book = id2
 WHERE NOT EXISTS
       (SELECT *
          FROM identifiers
         WHERE book = id1
           AND type = WI.type)
 UNION
SELECT title_sort, author_sort,
       id1 AS id, MI.type, MI.val,
       id2 AS id, WI.type, WI.val
  FROM B
 INNER JOIN identifiers AS MI
    ON MI.book = id1
 INNER JOIN working.identifiers AS WI
    ON WI.book = id2
   AND WI.type = MI.type
 WHERE MI.val <> WI.val;

.print
.print ----------------------
.print Check comment mismatch
.print ----------------------

SELECT MB.sort, MB.author_sort, MB.id, MC.text, WB.id, WC.text
  FROM books AS MB
 INNER JOIN working.books AS WB
    ON MB.sort = WB.sort
   AND MB.author_sort = WB.author_sort
  LEFT OUTER JOIN comments AS MC
    ON MC.book = MB.id
  LEFT OUTER JOIN working.comments AS WC
    ON WC.book = WB.id
 WHERE
   NOT (MC.text IS NULL AND WC.text IS NULL)
   AND (MC.text IS NULL OR WC.text IS NULL
        OR MC.text <> WC.text)
 ORDER BY WB.id DESC;

-- .print
-- .print -----------------------
-- .print Check language mismatch
-- .print -----------------------

-- SELECT MB.sort, MB.author_sort, MB.id, ML.lang_code AS lang, WB.id, NULL AS lang
--   FROM books AS MB
--   LEFT OUTER JOIN books_languages_link AS MBL
--     ON MBL.book = MB.id
--   LEFT OUTER JOIN languages AS ML
--     ON MBL.lang_code = ML.id
--  INNER JOIN working.books AS WB
--     ON MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--  WHERE ML.lang_code IS NOT NULL
--    AND ML.lang_code NOT IN
--        (SELECT WL.lang_code
--           FROM working.books_languages_link AS WBL,
--                working.languages AS WL
--          WHERE WBL.book = WB.id
--            AND WBL.lang_code = WL.id)
--  UNION
-- SELECT MB.sort, MB.author_sort, MB.id, NULL AS lang, WB.id, WL.lang_code AS lang
--   FROM working.books AS WB
--   LEFT OUTER JOIN working.books_languages_link AS WBL
--     ON WBL.book = WB.id
--   LEFT OUTER JOIN working.languages AS WL
--     ON WBL.lang_code = WL.id
--  INNER JOIN books AS MB
--     ON MB.sort = WB.sort
--    AND MB.author_sort = WB.author_sort
--  WHERE WL.lang_code IS NOT NULL
--    AND WL.lang_code NOT IN
--        (SELECT ML.lang_code
--           FROM books_languages_link AS MBL,
--                languages AS ML
--          WHERE MBL.book = MB.id
--            AND MBL.lang_code = ML.id);

.print
.print -----------------------------------
.print Check language mismatch (version 2)
.print -----------------------------------

WITH B AS (SELECT MB.sort AS title_sort, MB.author_sort,
                  MB.id AS id1, WB.id AS id2
             FROM books AS MB,
                  working.books AS WB
            WHERE MB.sort = WB.sort
              AND MB.author_sort = WB.author_sort)
SELECT title_sort, author_sort,
       id1 AS id, ML.lang_code AS lang,
       id2 AS id, NULL AS lang
  FROM B
 INNER JOIN books_languages_link AS MBL
    ON MBL.book = id1
 INNER JOIN languages AS ML
    ON MBL.lang_code = ML.id
 WHERE NOT EXISTS
       (SELECT *
          FROM working.books_languages_link AS WBL,
               working.languages AS WL
         WHERE WBL.book = id2
           AND WBL.lang_code = WL.id
           AND WL.lang_code = ML.lang_code)
 UNION
SELECT title_sort, author_sort,
       id1 AS id, NULL AS lang,
       id2 AS id, WL.lang_code AS lang
  FROM B
 INNER JOIN working.books_languages_link AS WBL
    ON WBL.book = id2
 INNER JOIN working.languages AS WL
    ON WBL.lang_code = WL.id
 WHERE NOT EXISTS
       (SELECT *
          FROM books_languages_link AS MBL,
               languages AS ML
         WHERE MBL.book = id1
           AND MBL.lang_code = ML.id
           AND WL.lang_code = ML.lang_code);

-- Local Variables:
-- sql-product: sqlite
-- End:
