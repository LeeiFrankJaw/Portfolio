complete <- function(directory, id = 1:332) {
	get_complete <- function(directory, id) {
		my_table <- read.csv(sprintf("%s/%03d.csv", directory, id))
		my_table <- my_table[complete.cases(my_table),]
		data.frame(id=id, nobs=nrow(my_table))
	}
	acc_table <- get_complete(directory, id[[1]])
	for (i in tail(id, -1)) {
		tmp_table <- get_complete(directory, i)
		acc_table <- rbind(acc_table, tmp_table)
	}
	acc_table
}