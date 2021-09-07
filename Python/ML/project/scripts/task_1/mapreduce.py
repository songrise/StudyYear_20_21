import os

##############mean value computation################


def map_1(line):
    # input lines of the dataset
    for feature_name, feature_value in enumerate(line):
        if not feature_value.is_null():
            emit(feature_name, feature_value)


def reduce_1(key, value_list):
    """each key corresponds to one feature"""
    emit(key, value_list.mean())


##############missing value imputation################


def map_2(line):
    # input lines of the dataset
    if line.contain_null():
        i = line.find_na()
        line[i] = feature_mean[i]  # computed in mapreduce 1
    emit(line, "")  # emit whole line, value unused in this case.


def reduce_2(key, value_list):
    """each key corresponds to one feature"""
    emit(line, "")
