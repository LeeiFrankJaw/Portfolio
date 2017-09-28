best <- function(state, outcome) {
	data <- read.csv("outcome-of-care-measures.csv",
			colClasses = "character")
	data <- split(data, data$State)
	if (!state %in% names(data)) stop("invalid state")

	prefix <- "Hospital.30.Day.Death..Mortality..Rates.from."
	if (outcome == "heart attack")
		outcome <- paste(prefix, "Heart.Attack", sep = "")
	else if (outcome == "heart failure")
		outcome <- paste(prefix, "Heart.Failure", sep = "")
	else if (outcome == "pneumonia")
		outcome <- paste(prefix, "Pneumonia", sep = "")
	else
		stop("invalid outcome")

	x <- data[[state]]
	x[, outcome] <- as.numeric(x[, outcome])
	x <- x[complete.cases(x[[outcome]]),]
	x <- x[ order(x[[outcome]], x[["Hospital.Name"]]), ]
	x[1,]$Hospital.Name
}