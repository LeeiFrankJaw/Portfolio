import MapReduce
import sys


mr = MapReduce.MapReduce()

# =============================
# Do not modify above this line

def mapper(record):
    # key: document identifier
    # value: document contents
    key = record[0]
    value = record[:]
    mr.emit_intermediate(key, value)
    key = record[1]
    mr.emit_intermediate(key, value)

def reducer(key, list_of_values):
    # key: word
    # value: list of occurrence counts
    friendings = map(lambda x: x[1],
                     filter(lambda x: x[0] == key, list_of_values))
    friendeds = map(lambda x: x[0],
                    filter(lambda x: x[1] == key, list_of_values))
    for friending in friendings:
        if friending not in friendeds:
            mr.emit((key, friending))
            mr.emit((friending, key))

# Do not modify below this line
# =============================
if __name__ == '__main__':
  inputdata = open(sys.argv[1])
  mr.execute(inputdata, mapper, reducer)
