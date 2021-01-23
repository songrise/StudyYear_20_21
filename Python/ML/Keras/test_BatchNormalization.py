# %%
import pandas as pd
import keras
from keras.layers import Dense
from keras.layers import Flatten
from keras.layers import Dropout
from keras.layers import MaxPool2D
from keras.layers import Conv2D
from keras.layers import BatchNormalization
from keras.models import Sequential
from keras.datasets import fashion_mnist
from keras.callbacks import EarlyStopping
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from sklearn.preprocessing import MinMaxScaler
import numpy as np


# %% A baseline CNN model
model = Sequential()

model.add(Conv2D(64, 5, padding="same",
                 activation="relu", input_shape=[28, 28, 1]))
model.add(MaxPool2D())
model.add(BatchNormalization())
model.add(Conv2D(32, 3, padding="same", activation="relu"))
model.add(BatchNormalization())
model.add(Conv2D(16, 3, padding="same", activation="relu"))
model.add(BatchNormalization())
model.add(Dense(300, activation="relu"))
model.add(Flatten())
model.add(Dense(10, activation="relu"))
model.compile(loss='sparse_categorical_crossentropy',
              optimizer="sgd", metrics=["accuracy"])
model.summary()
# %%
(x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()
x_train, x_val, y_train, y_val = train_test_split(
    x_train, y_train, test_size=0.2)
x_train = x_train.reshape(-1, 28, 28, 1)
x_val = x_val.reshape(-1, 28, 28, 1)

# %%
early_stop = EarlyStopping(
    patience=4, restore_best_weights=True)

model.fit(x_train, y_train, epochs=100, validation_data=(
          x_val, y_val), callbacks=[early_stop])

# %%
