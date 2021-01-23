# %%
from keras.layers import Dense
from keras.layers import Flatten
from keras.layers import Dropout
from keras.models import Sequential
from keras.datasets import mnist
from sklearn.model_selection import train_test_split
import keras
import pandas as pd


# %% model
model = Sequential()
model.add(Flatten(input_shape=[28, 28]))
model.add(Dense(300, activation="tanh"))
model.add(Dropout(0.25))
model.add(Dense(200, activation="tanh"))
model.add(Dense(10, activation="softmax"))
model.compile(loss='sparse_categorical_crossentropy',
              optimizer="sgd", metrics=["accuracy"])
model.summary()
# %%
(x_train, y_train), (x_test, y_test) = mnist.load_data()
x_train, x_val, y_train, y_val = train_test_split(
    x_train, y_train, test_size=0.2)

# %%
early_stop = keras.callbacks.EarlyStopping(
    patience=4, restore_best_weights=True)
model.fit(x_train, y_train, epochs=100, validation_data=(
          x_val, y_val), callbacks=[early_stop])

# %%
