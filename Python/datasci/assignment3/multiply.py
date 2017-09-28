import MapReduce
import sys


mr = MapReduce.MapReduce()

# =============================
# Do not modify above this line

def mapper(record):
    # key: document identifier
    # value: document contents
    if record[0] == 'a':
        for k in range(5):
            key = (record[1], k)
            mr.emit_intermediate(key, record)
    elif record[0] == 'b':
        for i in range(5):
            key = (i, record[2])
            mr.emit_intermediate(key, record)

def reducer(key, list_of_values):
    # key: word
    # value: list of occurrence counts
    a_s = filter(lambda x: x[0] == 'a', list_of_values)
    b_s = filter(lambda x: x[0] == 'b', list_of_values)
    total = 0
    for a in a_s:
        for b in b_s:
            if a[2] == b[1]:
                total += a[3] * b[3]
    mr.emit(key+(total,))

# Do not modify below this line
# =============================
if __name__ == '__main__':
  inputdata = open(sys.argv[1])
  mr.execute(inputdata, mapper, reducer)
