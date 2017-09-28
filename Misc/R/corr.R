corr <- function(directory, threshold = 0) {
	id <- 1:332
	get_complete <- function(directory, id) {
		my_table <- read.csv(sprintf("%s/%03d.csv", directory, id))
		my_table <- my_table[complete.cases(my_table),]
	}
	cors <- vector(mode = "numeric", length = 0)
	for (i in id) {
		tmp_table <- get_complete(directory, i)
		if (nrow(tmp_table) > threshold) {
			x <- tmp_table[["sulfate"]]
			y <- tmp_table[["nitrate"]]
			cors <- c(cors, cor(x,y))
		}
	}
	cors
}