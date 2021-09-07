import os

##############user rating table generation################
# SELECT * from rating
# WHERE user_id = 53698


def map_3(line):
    # assume each line is a record in database
    emit(line.user_id, line)


def reduce_3(key, value_list):
    """each key corresponds to one feature"""
    # simply emit them for aggregation
    emit(line, null)
