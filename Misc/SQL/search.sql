SELECT MAX(similarity) FROM (
	SELECT SUM(value) AS similarity
	FROM (
		SELECT
		corpus.docid AS docid,
		corpus.count * query.count AS value
		FROM frequency AS corpus
		INNER JOIN (
			SELECT 'q' AS docid, 'washington' AS term, 1 AS count
			UNION
			SELECT 'q' AS docid, 'taxes' AS term, 1 AS count
			UNION
			SELECT 'q' AS docid, 'treasury' AS term, 1 AS count
		) AS query
		ON corpus.term = query.term
	) GROUP BY docid
)
