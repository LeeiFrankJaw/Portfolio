import MapReduce
import sys


mr = MapReduce.MapReduce()

# =============================
# Do not modify above this line

def mapper(record):
    # key: document identifier
    # value: document contents
    key = record[1]
    value = record[:]
    mr.emit_intermediate(key, value)

def reducer(key, list_of_values):
    # key: word
    # value: list of occurrence counts
    if (len(list_of_values) > 1):
        line_items = filter(lambda x: x[0] == 'line_item', list_of_values)
        orders = filter(lambda x: x[0] == 'order', list_of_values)
        for order in orders:
            for line_item in line_items:
                total = order + line_item
                mr.emit((total))

# Do not modify below this line
# =============================
if __name__ == '__main__':
  inputdata = open(sys.argv[1])
  mr.execute(inputdata, mapper, reducer)
