pollutantmean <- function(directory, pollutant, id = 1:332) {
	s <- 0
	n <- 0
	for (i in id) {
		my_table <- read.csv(sprintf("%s/%03d.csv", directory, i))
		my_table <- my_table[complete.cases(my_table[[pollutant]]),]
		s <- s + sum(my_table[[pollutant]])
		n <- n + nrow(my_table)
	}
	s / n
}