SELECT COUNT(*) FROM (
	SELECT docid, COUNT(term)
	FROM (
		SELECT DISTINCT *
		FROM frequency
		WHERE term = "transactions"
		UNION
		SELECT DISTINCT *
		FROM frequency
		WHERE term = "world"
	) GROUP BY docid
	HAVING COUNT(term) = 2
);
