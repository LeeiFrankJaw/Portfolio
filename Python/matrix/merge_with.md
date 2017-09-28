Here's my solution code in Python 3 for the general case.

I first wrote the _merge_ function and then extend it to the more general _merge\_with_ function, which takes a function and various number of dictionaries. Were there any duplicate keys in those dictionaries, apply the supplied function to the values whose keys are duplicate.

The _merge_ function can be redefined using the _merge\_with_ function, as in the case of _merger_ function. The name _merger_ means to merge them all and keep the rightmost values, were there any duplicates. So does the _mergel_ function, which keep the leftmost.

All the functions here ¡ª _merge_, _merge\_with_, _mergel_, and _merger_ ¡ª are generic in the case that they take arbitrary number of dictionary arguments. Specifically, _merge\_with_ must take as argument a function compatible with the data to which it will apply.

    from functools import reduce
    from operator import or_
    
    def merge(*dicts):
        return { k: reduce(lambda d, x: x.get(k, d), dicts, None)
                 for k in reduce(or_, map(lambda x: x.keys(), dicts), set()) }
    
    def merge_with(f, *dicts):
        return { k: (lambda x: f(*x) if len(x)>1 else x[0])([ d[k] for d in dicts
                                                              if k in d ])
                 for k in reduce(or_, map(lambda x: x.keys(), dicts), set()) }
    
    mergel = lambda *dicts: merge_with(lambda *x: x[0], *dicts)
    
    merger = lambda *dicts: merge_with(lambda *x: x[-1], *dicts)

Tests

    >>> squares = { k:k*k for k in range(4) }
    >>> squares
    {0: 0, 1: 1, 2: 4, 3: 9}
    >>> cubes = { k:k**3 for k in range(2,6) }
    >>> cubes
    {2: 8, 3: 27, 4: 64, 5: 125}
    >>> merger(squares, cubes)
    {0: 0, 1: 1, 2: 8, 3: 27, 4: 64, 5: 125}
    >>> merger(cubes, squares)
    {0: 0, 1: 1, 2: 4, 3: 9, 4: 64, 5: 125}
    >>> mergel(squares, cubes)
    {0: 0, 1: 1, 2: 4, 3: 9, 4: 64, 5: 125}
    >>> mergel(cubes, squares)
    {0: 0, 1: 1, 2: 8, 3: 27, 4: 64, 5: 125}
    >>> merge(squares, cubes)
    {0: 0, 1: 1, 2: 8, 3: 27, 4: 64, 5: 125}
    >>> merge(cubes, squares)
    {0: 0, 1: 1, 2: 4, 3: 9, 4: 64, 5: 125}
    >>> merge_with(lambda x, y: x+y, squares, cubes)
    {0: 0, 1: 1, 2: 12, 3: 36, 4: 64, 5: 125}
    >>> merge_with(lambda x, y: x*y, squares, cubes)
    {0: 0, 1: 1, 2: 32, 3: 243, 4: 64, 5: 125}

**Update**

After I wrote the above, I find there's another way to do it.

    from functools import reduce
    
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

Notice that the definitions for _mergel_ and _merger_ using _merge\_with_ have been changed with new functions as first arguments. The _f_ function must now be binary. The tests provided above still works. Here are some more tests to show the generality of those functions.

    >>> merge() == {}
    True
    >>> merge(squares) == squares
    True
    >>> merge(cubes) == cubes
    True
    >>> mergel() == {}
    True
    >>> mergel(squares) == squares
    True
    >>> mergel(cubes) == cubes
    True
    >>> merger() == {}
    True
    >>> merger(squares) == squares
    True
    >>> merger(cubes) == cubes
    True
    >>> merge_with(lambda x, y: x+y, squares, cubes, squares)
    {0: 0, 1: 2, 2: 16, 3: 45, 4: 64, 5: 125}
    >>> merge_with(lambda x, y: x*y, squares, cubes, squares)
    {0: 0, 1: 1, 2: 128, 3: 2187, 4: 64, 5: 125}