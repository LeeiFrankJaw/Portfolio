rankall <- function(outcome, num = "best") {
	data <- read.csv("outcome-of-care-measures.csv",
			colClasses = "character")
	data <- split(data, data$State)

	prefix <- "Hospital.30.Day.Death..Mortality..Rates.from."
	if (outcome == "heart attack")
		outcome <- paste(prefix, "Heart.Attack", sep = "")
	else if (outcome == "heart failure")
		outcome <- paste(prefix, "Heart.Failure", sep = "")
	else if (outcome == "pneumonia")
		outcome <- paste(prefix, "Pneumonia", sep = "")
	else
		stop("invalid outcome")

	rankstate <- function(x) {
		x[, outcome] <- as.numeric(x[, outcome])
		x <- x[complete.cases(x[[outcome]]),]
		x <- x[ order(x[[outcome]], x[["Hospital.Name"]]), ]
		
		if (num == "best")
			num <- 1
		else if (num == "worst")
			num <- nrow(x)
			
		data.frame(hospital = x[num,]$Hospital.Name,
			state = x[1,]$State, row.names = c(x[1,]$State),
			stringsAsFactors=FALSE)
	}
	res <- lapply(data, rankstate)
	unsplit(res, names(res))
}