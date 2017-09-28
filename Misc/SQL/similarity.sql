SELECT SUM(value) FROM (
	SELECT crude.count * earn.count AS value
	FROM (
		SELECT *
		FROM frequency
		WHERE docid = '10080_txt_crude'
	) crude
	INNER JOIN (
		SELECT *
		FROM frequency
		WHERE docid = '17035_txt_earn'
	) earn
	ON crude.term = earn.term
)
