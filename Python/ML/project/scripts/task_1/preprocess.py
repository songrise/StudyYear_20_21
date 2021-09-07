# -*- coding : utf-8 -*-
# @FileName  : preprocess.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2021-05-01
# @Github    ：https://github.com/songrise
# @Descriptions: Preprocessing module, this is doing exactly same thing as MapReduce.

# %% load dataset
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import RobustScaler  # insensitive to outliers
from sklearn.compose import ColumnTransformer
from sklearn.utils import shuffle

train = pd.read_csv("../../data/Teleplay.csv", delimiter=",")
test = pd.read_csv("../../data/New_Teleplay.csv", delimiter=",")


# %% helper functions

def tokenize(input_str: str = None) -> list:
    try:
        assert isinstance(input_str, str)
    except AssertionError as e:
        return list()

    return [word.strip() for word in input_str.split(",")]


def get_all_genre() -> list:
    genre = train["genre"]
    all_genre = set()
    for line in genre:
        for item in tokenize(line):
            all_genre.add(item)
    # Python set does not guarantee order, so convert to list
    return list(all_genre)


all_genre = get_all_genre()
all_type = np.unique(train["type"].tolist()).tolist()


def encode_genre(arr: list, all_genre: list) -> list:
    """
        encode genre to a vector, like "multi-hot encoder"
    """
    encoded = np.zeros_like(all_genre, dtype=np.int64)
    for genre in arr:
        encoded[all_genre.index(genre)] = 1
    return encoded


def encode_type(arr: list, all_type: list) -> list:
    encoded = [all_type.index(t) for t in arr]
    return encoded


# %%
# data processing workflow
def process(data, mode):
    """
    Input: raw data, array-like
    Output: processed data, array-like
    Parameter: data: raw data; mode: 0 for training data, 1 for testing data, 2 for task 2
    """
    if mode == 1:  # if it is testing data, drop the rating since they are all na
        data = data.drop(["rating"], axis=1)
    elif mode == 2:
        # in task 2 (CB), fill missing rating, such that they will not be dropped
        data["rating"].fillna(data.rating.mean(), inplace=True)

    members_mean = data["members"].mean()
    data["members"].fillna(members_mean, inplace=True)
    data["type"].fillna("medium", inplace=True)  # our assumption
    episodes_mean = data[data["episodes"] !=
                         "Unknown"].episodes.astype("float").mean()
    print(episodes_mean)

    data.loc[data["episodes"] == "Unknown", "episodes"] = episodes_mean

    # data.replace("Unknown", episodes_mean)

    # vectorization
    encoded_genre = pd.DataFrame(
        np.array([encode_genre(tokenize(genre), all_genre) for genre in data["genre"]], dtype=np.int64), columns=all_genre, dtype=np.int64)
    encoded_type = pd.DataFrame(
        np.array(encode_type(data["type"], all_type), dtype=np.int64), columns=["type"])

    # drop useless feature,
    data = data.drop(["genre", "type", "name"], axis=1)

    data = data.astype("float")

    # concatenate vectorized type and genre
    data = pd.concat([data, encoded_type], axis=1)
    data = pd.concat([data, encoded_genre], axis=1)
    # if mode == 1:
    #     data.fillna(0, inplace=True)
    data.dropna(axis=0, how="any", inplace=True)

    # %% Capping (to deal with outliers)
    data.loc[data["episodes"] > 1000, "episodes"] = 1000
    data.loc[data["members"] > 10000, "members"] = 10000

    # %% apply transformation
    transformer = ColumnTransformer(
        transformers=[('cat', RobustScaler(), ["episodes"])])
    episodes_transformed = transformer.fit_transform(data)
    data.episodes = episodes_transformed

    transformer = ColumnTransformer(
        transformers=[('cat', RobustScaler(), ["members"])])
    members_transformed = transformer.fit_transform(data)
    data.members = members_transformed

    if mode == 0:
        data = shuffle(data)

    print(data.info())
    return data


# %%
# if __name__ == "__main__":
processed_train = process(train, 0)
processed_train.head()
processed_test = process(test, 1)
processed_test.head()
# for task 2
# processed_train = process(train, 2)
# processed_train.head()

processed_train.to_csv("processed_teleplay.csv", index=False)
processed_test.to_csv("processed_newTeleplay.csv", index=False)

print(processed_train.shape)
print(processed_test.shape)

print("Done")
# %%
