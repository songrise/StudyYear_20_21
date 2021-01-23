# %%
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import Dropout
from keras.layers import Flatten
from keras.layers.convolutional import Conv2D
from keras.layers.convolutional import MaxPooling2D
from keras.utils import np_util
from sklearn.model_selection import train_test_split
# from keras.datasets import mnist
from keras import backend as K
import matplotlib as plt
import pandas as pd
# %%
train = pd.read_csv("../Sklearn/MNIST/Python/SkLearn/MNIST/train.csv")
Y_train = train["label"]
X_train = train.drop(labels=["label"], axis=1)
test = pd.read_csv("../Sklearn/MNIST/Python/SkLearn/MNIST/test.csv")

# Normalization
X_train /= 255.0
test /= 255.0

# reshape
X_train = X_train.value.reshape(-1, 28, 28, 1)
test = test.value.reshape(-1, 28, 28, 1)
# %%
# Model setup
model = Sequential()
model.add(Conv2D(32, (5, 5), input_shape=(1, 28, 28), activation='relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Dropout(0.2))
model.add(Flatten())
model.add(Dense(128, activation='relu'))
model.add(Dense(10, activation='softmax'))
# Compile model
model.compile(loss='categorical_crossentropy',
              optimizer='adam', metrics=['accuracy'])
# %%
# Train
X_train, X_test, Y_train, Y_test = train_test_split(
    X_train, Y_train, test_size=0.1, random_state=2)

model.fit(X_train, Y_train, validation_data=(X_test, Y_test),
          epochs=10, batch_size=200, verbose=2)
# Final evaluation of the model
scores = model.evaluate(X_test, Y_test, verbose=0)
print("Baseline Error: %.2f%%" % (100-scores[1]*100))
