#!/usr/bin/env python
import asyncio
import json
import os
import re
import sys
# import threading
from os import listdir
from signal import SIGINT, SIGTERM, SIGCHLD
from subprocess import PIPE, STDOUT, Popen
from threading import Thread  # , Lock

# import requests

p = re.compile(r'time=(.*) ms')

# lock = Lock()


def run_in_thread(config):
    proc = Popen(['ping', '-c', '1', '-W', '1', config['server']], stdout=PIPE)
    proc.wait()
    process_result(config, proc)


def process_result(config, proc):
    line = proc.stdout.readline().decode()
    while line:
        m = p.search(line)
        if m:
            latency = float(m.group(1))
            print(f'{config["server"]}\t{latency} ms')
            # lock.acquire()
            config['latency'] = latency
            # lock.release()
        line = proc.stdout.readline().decode()


def load(fname):
    with open(fname) as f:
        res = json.load(f)
    res['fname'] = fname
    return res


configs = [load(fname) for fname in listdir() if fname.endswith('.json')]

threads = [Thread(target=run_in_thread, args=(config,)) for config in configs]

for thread in threads:
    thread.start()

for thread in threads:
    thread.join()


configs = sorted(filter(lambda x: 'latency' in x, configs),
                 key=lambda x: x['latency'])

print('-' * 20)

for config in configs:
    print(f'{config["server"]}\t{config["latency"]} ms')

# fastest = configs[0]

# print(f'select server {fastest["server"]} ({fastest["latency"]} ms).')

# sslocal = ['ss-local', '-v', '-c', fastest['fname']]
# sslocal_shell = f'ss-local -v -c {fastest["fname"]}'

# proc = Popen(sslocal)
# stdout=PIPE, stderr=STDOUT, universal_newlines=True, bufsize=1)
# output = os.fdopen(sys.stdout.fileno())

# for line in iter(output.readline, b''):
#     sys.stdout.write(line)
#     sys.stdout.flush()


# class LogProtocol(asyncio.SubprocessProtocol):
#     def __init__(self, exit_future):
#         self.exit_future = exit_future

#     def pipe_data_received(self, fd, data):
#         # with open('log.log', 'wb') as f:
#         #     f.write(data)
#         sys.stdout.buffer.write(data)
#         sys.stdout.buffer.write('\nhere\n'.encode())

#     def process_exited(self):
#         self.exit_future.set_result(True)


# async def start_sslocal(loop):
#     # exit_future = asyncio.Future(loop=loop)
#     # master, slave = os.openpty()
#     proc = await asyncio.create_subprocess_exec(
#         # *sslocal,
#         './a.out',
#         # lambda: LogProtocol(exit_future),
#         # sys.executable, '-c', code,
#         stdout=asyncio.subprocess.PIPE,
#         # stdout=sys.stdout.fileno(),
#         # stdin=sys.stdin.fileno(),
#         stderr=sys.stderr.fileno()
#     )
#     line = await proc.stdout.readline()
#     print('here')
#     while line:
#         sys.stdout.buffer.write(line)
#         sys.stdout.buffer.write(line)
#         line = await proc.stdout.readline()


# def cleanup():
#     proc.terminate()
#     proc.wait()
#     loop.stop()


# loop = asyncio.get_event_loop()

# for signum in (SIGINT, SIGTERM, SIGCHLD):
#     loop.add_signal_handler(signum, cleanup)

# try:
#     # loop.run_until_complete(start_sslocal(loop))
#     loop.run_forever()
# finally:
#     loop.close()
