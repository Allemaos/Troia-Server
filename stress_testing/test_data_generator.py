import os
import sys
import csv
import random

LABELS = ["correct", "incorrect"]


def gen_items(n_workers, n_objects, n_golds):
    workers = ['worker_' + str(i) for i in xrange(n_workers)]
    objects = ['object_labels_quite_long_' + str(i)
            for i in xrange(n_objects)]
    golds = ['gold_object_labels_quite_long_' + str(i)
            for i in xrange(n_golds)]
    return workers, objects, golds


def generate_data(iterations):
    n_workers = iterations / 50
    n_objects = iterations / 5
    n_golds = n_objects / 20
    workers, objects, golds = gen_items(n_workers, n_objects, n_golds)
    objects += golds
    labels = ["correct", "incorrect"]
    assigns = [
        (random.choice(workers), random.choice(objects), random.choice(labels))
        for _ in xrange(iterations)
    ]
    costs = [(labels[i], labels[j], 1 - int(i == j))
        for i in xrange(2) for j in xrange(2)]
    return costs, golds, assigns


def save_csv(path, data):
    with open(path, 'w') as F:
        writer = csv.writer(F, delimiter='\t')
        writer.writerows(data)


def save_data(folder, costs, golds, assigns):
    save_csv(folder + "costs", costs)
    save_csv(folder + "correct", golds)
    save_csv(folder + "input", assigns)


def generate_set_and_save(folder, iterations):
    data = generate_data(iterations)
    folder = folder + str(iterations) + '/'
    if not os.path.exists(folder):
        os.makedirs(folder)
    save_data(folder, *data)


def main(argv):
    folder = argv[0]
    iterss = [pref * (10 ** zeroes) for pref in [1, 5]
            for zeroes in [2, 3, 4, 5]]
    for iters in iterss:
        generate_set_and_save(folder, iters)


if __name__ == "__main__":
    main(sys.argv[1:])
