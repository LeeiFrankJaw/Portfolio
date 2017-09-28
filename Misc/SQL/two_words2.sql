SELECT COUNT(*) FROM (
	SELECT * FROM (
		SELECT DISTINCT docid
		FROM frequency
		WHERE term = "transactions"
	) x JOIN (
		SELECT DISTINCT docid
		FROM frequency
		WHERE term = "world"
	) y ON x.docid = y.docid
);
