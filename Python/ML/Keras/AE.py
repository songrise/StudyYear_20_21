import tensorflow as tf
import keras
from keras.layers import Input, Dense, Conv2D, Conv2DTranspose, Flatten
from keras.models import Model
from keras.datasets import mnist
import numpy as np
import matplotlib.pyplot as plt

(x_train, _), (x_test, _) = mnist.load_data()

x_train = x_train.astype('float32') / 255.
x_test = x_test.astype('float32') / 255.
x_train = x_train.reshape((len(x_train), np.prod(x_train.shape[1:])))
x_test = x_test.reshape((len(x_test), np.prod(x_test.shape[1:])))
print(x_train.shape)
print(x_test.shape)

#!!!!!!
encoding_dim = 32
input_img = Input(shape=(784,))

encoded = Conv2D(16, 3, activation="relu")(input_img)
# encoded = Flatten()(encoded)
encoded = Dense(encoding_dim, activation='relu')(encoded)

decoded = Dense(784, activation='sigmoid')(encoded)
decoded = Conv2DTranspose(16, 3, activation="relu")(encoded)

autoencoder = Model(inputs=input_img, outputs=decoded)
encoder = Model(inputs=input_img, outputs=encoded)

encoded_input = Input(shape=(encoding_dim,))
decoder_layer = autoencoder.layers[-1]

decoder = Model(inputs=encoded_input, outputs=decoder_layer(encoded_input))

autoencoder.compile(optimizer='adadelta', loss='binary_crossentropy')

autoencoder.fit(x_train, x_train, epochs=10, batch_size=256,
                shuffle=True, validation_data=(x_test, x_test))

encoded_imgs = encoder.predict(x_test)
decoded_imgs = decoder.predict(encoded_imgs)

n = 10  # how many digits we will display
plt.figure(figsize=(20, 4))
for i in range(n):
    ax = plt.subplot(2, n, i + 1)
    plt.imshow(x_test[i].reshape(28, 28))
    plt.gray()
    ax.get_xaxis().set_visible(False)
    ax.get_yaxis().set_visible(False)

    ax = plt.subplot(2, n, i + 1 + n)
    plt.imshow(decoded_imgs[i].reshape(28, 28))
    plt.gray()
    ax.get_xaxis().set_visible(False)
    ax.get_yaxis().set_visible(False)
plt.show()


keras.optimizers.SGD()


class Autoencoder(Model):
    def __init__(self, latent_dim):
        super(Autoencoder, self).__init__()
        self.latent_dim = latent_dim
        self.encoder = tf.keras.Sequential([
            layers.Flatten(),
            layers.Dense(latent_dim, activation='relu'),
        ])
        self.decoder = tf.keras.Sequential([
            layers.Dense(784, activation='sigmoid'),
            layers.Reshape((28, 28))
        ])

    def call(self, x):
        encoded = self.encoder(x)
        decoded = self.decoder(encoded)
        return decoded


autoencoder = Autoencoder(latent_dim)


class Autoencoder(Model):
    def __init__(self, latent_dim):
        super(Autoencoder, self).__init__()
        self.latent_dim = latent_dim

        self.encoder = tf.keras.Sequential([
            Conv2D(64, (3, 3), activation='relu', input_shape=(28, 28, 3)),
            Conv2D(32, (3, 3), activation='relu',),
            layers.Flatten(),
            layers.Dense(300, activation='relu'),
            layers.Dense(latent_dim, activation='relu'),
        ])

        self.decoder = tf.keras.Sequential([
            layers.Dense(300, activation='relu'),
            Conv2D(32, (3, 3), activation='relu',),
            Conv2D(64, (3, 3), activation='relu'),
            Conv2D(3, 1, activation='relu'),
            layers.Reshape((28, 28))
        ])

    def call(self, x):
        encoded = self.encoder(x)
        decoded = self.decoder(encoded)
        return decoded


model = Autoencoder(64)
model.compile(optimizer='adam', loss=tf.keras.losses.MeanSquaredError())
model.build()

from keras import layers

class Denoise(Model):
    def __init__(self):
        super(Denoise, self).__init__()
        self.encoder = tf.keras.Sequential([
            layers.Input(shape=(80, 80, 3)),
            layers.Conv2D(16, (3, 3), activation='relu',
                          padding='same', strides=2),
            layers.Conv2D(8, (3, 3), activation='relu',
                          padding='same', strides=2),
            layers.Conv2D(4, (3, 3), activation='relu',
                          padding='same', strides=2),
            layers.Flatten(),
            layers.Dense(64)
        ])

        self.decoder = tf.keras.Sequential([
            layer.Input(self.encoder.output_shape)
            layers.Dense(400),
            layers.Reshape((10, 10)),
            layers.Conv2DTranspose(4, kernel_size=3, strides=2,
                                   activation='relu', padding='same'),
            layers.Conv2DTranspose(8, kernel_size=3, strides=2,
                                   activation='relu', padding='same'),
            layers.Conv2DTranspose(16, kernel_size=3, strides=2,
                                   activation='relu', padding='same'),
            layers.Conv2D(3, kernel_size=(3, 3), activation='sigmoid', padding='same')])

    def call(self, x):
        encoded = self.encoder(x)
        decoded = self.decoder(encoded)
        return decoded
