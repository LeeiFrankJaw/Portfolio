from functools import reduce
##from operator import or_

##def merge(*dicts):
##    return { k: reduce(lambda d, x: x.get(k, d), dicts, None)
##             for k in reduce(or_, map(lambda x: x.keys(), dicts), set()) }
##
##def merge_with(f, *dicts):
##    return { k: (lambda x: f(*x) if len(x)>1 else x[0])([ d[k] for d in dicts
##                                                          if k in d ])
##             for k in reduce(or_, map(lambda x: x.keys(), dicts), set()) }
##
##mergel = lambda *dicts: merge_with(lambda *x: x[0], *dicts)
##
##merger = lambda *dicts: merge_with(lambda *x: x[-1], *dicts)

def merge(*dicts):
    return reduce(lambda d1, d2: reduce(lambda d, t:
                                        dict(list(d.items())+[t]),
                                        d2.items(), d1),
                  dicts, {})

def merge_with(f, *dicts):
    return reduce(lambda d1, d2: reduce(lambda d, t:
                                        dict(list(d.items()) +
                                             [(t[0], f(d[t[0]], t[1])
                                               if t[0] in d else
                                               t[1])]),
                                        d2.items(), d1),
                  dicts, {})

mergel = lambda *dicts: merge_with(lambda x, y: x, *dicts)
merger = lambda *dicts: merge_with(lambda x, y: y, *dicts)
