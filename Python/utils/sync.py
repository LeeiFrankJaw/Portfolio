#!/usr/bin/env python

from __future__ import print_function

import logging
import sched
import sys
import time
from datetime import datetime
from subprocess import call as run


def get_logger():
    level = logging.INFO
    logger = logging.getLogger(__name__)
    logger.setLevel(level)
    ch = logging.StreamHandler()
    ch.setLevel(level)
    formatter = logging.Formatter('%(asctime)s:{}'.format(
        logging.BASIC_FORMAT))
    ch.setFormatter(formatter)
    logger.addHandler(ch)
    return logger


logger = get_logger()

# def print_time():
#     print('From print_time: {}'.format(datetime.now()))

# cnt = 0

# def print_time_and_raise():
#     global cnt
#     cnt += 1
#     print(cnt)
#     if cnt >= 5 and cnt % 2 == 0:
#         raise Exception('can you not be interrupted by me?')
#     print_time()


def repeat_every(time_diff, action, argument):
    s = sched.scheduler(time.time, time.sleep)

    def repeater(*args):
        try:
            action(*args)
        except Exception:
            logger.exception('{} raised the following exception.'.format(
                action.__name__))
        finally:
            s.enter(time_diff, 1, repeater, args)

    s.enter(0, 1, repeater, argument)
    s.run()


def main():
    t1 = datetime.now()
    logger.info('job started')
    run(['mbsync', '-a'])
    t2 = datetime.now()
    logger.info('job finished')
    logger.info('duration: {}'.format(t2 - t1))


if __name__ == '__main__':
    sys.exit(repeat_every(60 * 15, main, ()))
