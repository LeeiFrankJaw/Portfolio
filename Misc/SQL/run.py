import sqlite3, sys

def sql_execute(cur, sql):
    cur.execute(sql)
    for row in cur.fetchall():
        for col in row:
            print col,
        print

def main():
    con = sqlite3.connect(sys.argv[1])
    cur = con.cursor()

    try:
        with open(sys.argv[2]) as f:
            sql_execute(cur, f.read())
    except IOError:
        try:
            sql_execute(cur, sys.argv[2])
        except:
            print "there's an error"

if __name__ == '__main__':
    main()
