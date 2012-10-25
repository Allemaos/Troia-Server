import time

import numpy as np

from common import prepare_troia_client, save_csv, TEST_CASES, timeit

TEST_CASES = TEST_CASES[:-3]
JID = "COMPUTATION_TEST"
REPEAT = 2


def send_job(dataset_folder):
    tc, inputt = prepare_troia_client(JID, dataset_folder)
    # inputt = inputt[: 15 * 100]
    N = 50000
    if len(inputt) < N:
        inputs = [inputt]
    else:
        inputs = [inputt[i * N: (i + 1) * N] for i in xrange(len(inputt) / N)]
    for inputt in inputs:
        tc.load_worker_assigned_labels(inputt, JID)
    return tc


def single_test(dataset_folder):
    print "single test"
    tc = send_job(dataset_folder)
    time.sleep(5)
    return timeit(tc.compute_blocking, 10, JID)


def test(dataset_folder):
    times = np.array([single_test(dataset_folder) for _ in xrange(REPEAT)])
    return (np.mean(times), np.std(times))


def do_test_case(test_case):
    print "Working on: " + str(test_case)
    folder = './datasets/' + str(test_case)
    avg, std = test(folder)
    return (test_case, avg, std)


def main():
    res = (do_test_case(test_case) for test_case in TEST_CASES)
    w = []
    for r in res:
        print r
        w.append(r)
    save_csv('computation_times.txt', w)


if __name__ == '__main__':
    main()
