import functools


def singleton(cls):
    """Use class as singletone."""

    cls.__new_original__ = cls.__new__

    @staticmethod
    @functools.wraps(cls.__new__)
    def singleton_new(cls, *args, **kwargs):
        it = cls.__dict__.get('__it__')
        if it is not None:
            return it

        cls.__it__ = it = cls.__new_original__(cls, *args, **kwargs)
        it.__init_original__(*args, **kwargs)
        return it

    cls.__new__ = singleton_new
    cls.__init_original__ = cls.__init__
    cls.__init__ = object.__init__
    return cls


class Singleton(object):

    def __init__(self, cls):
        self.__dict__['__instance'] = instance = cls()
        # for attr in dir(instance):
        #     self.__dict__[attr] = getattr(instance, attr)

    def __getattr__(self, attr):
        return getattr(self.__dict__['__instance'], attr)

    def __setattr__(self, attr, value):
        setattr(self.__dict__['__instance'], attr, value)
        self.__dict__[attr] = getattr(self.__dict__['__instance'], attr)
