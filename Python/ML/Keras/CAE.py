import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import tensorflow as tf

from sklearn.metrics import accuracy_score, precision_score, recall_score
from sklearn.model_selection import train_test_split
from tensorflow.keras import layers, losses
from tensorflow.keras.datasets import fashion_mnist
from tensorflow.keras.models import Model
batch_size = 256
num_classes = 10
epochs = 50

latent_dim = 64


class Denoise(Model):
  def __init__(self):
    super(Denoise, self).__init__()
    self.encoder = tf.keras.Sequential([

        layers.Conv2D(64, (3, 3), activation='relu',
                      padding='same', strides=2, input_shape=(80, 80, 3)),
        layers.Conv2D(32, (3, 3), activation='relu',
                      padding='same', strides=2),
        layers.Conv2D(16, (3, 3), activation='relu',
                      padding='same', strides=2),
        layers.Flatten(),
        layers.Dense(300, activation='relu'),
        layers.Dense(64, activation='relu')
    ])

    self.decoder = tf.keras.Sequential([
        layers.Input(self.encoder.output_shape),
        layers.Dense(300, activation='relu'),
        layers.Dense(400, activation='relu'),
        layers.Reshape((10, 10, 4)),
        layers.Conv2DTranspose(16, kernel_size=3, strides=2,
                               activation='relu', padding='same'),
        layers.Conv2DTranspose(32, kernel_size=3, strides=2,
                               activation='relu', padding='same'),
        layers.Conv2DTranspose(64, kernel_size=3, strides=2,
                               activation='relu', padding='same'),
        layers.Conv2D(3, kernel_size=(3, 3), activation='sigmoid', padding='same')])

  def call(self, x):
    encoded = self.encoder(x)
    decoded = self.decoder(encoded)
    return decoded


model = Denoise()


model.build(input_shape=(None, 80, 80, 3))
model.compile(optimizer='adam', loss=tf.keras.losses.MeanSquaredError())
model.summary()
