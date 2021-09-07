
# -*- coding : utf-8 -*-
# @FileName  : transformations.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2021-09-03
# @Github    ：https://github.com/songrise
# @Descriptions: To test 2D geometrical transformations, referencing CV and CG book

# NB: all points are row vectors, so every transform matrix shall be transposed

# %%
import matplotlib.pyplot as plt
import numpy as np
from PIL import Image, ImageOps

base = Image.open('lena.png')
base = np.array(base)
# convert an image to xyrgb format


def toxyrgb(orig):
    """
    Converts an image from xy to xyrgb format
    :param orig: original image
    :return: xyrgb image
    """
    # 5x2 matix
    res = []
    for x in range(orig.shape[0]):
        for y in range(orig.shape[1]):
            pix = np.array([x, y])
            # concat the r,g,b values
            res.append(np.concatenate((pix, orig[x, y])))

    return np.array(res)


def toxy(converted, shape):
    """
    Converts an image from xyrgb to xy format
    :param converted: image in xyrgb format
    :return: image in xy3 format
    """
    # assume the image is a square

    res = []
    for i in range(converted.shape[0]):
        res.append(converted[i][2:])
    return np.reshape(np.array(res), shape + [3])


def show_img(img):
    """
    Displays an image
    :param img: image to be displayed, in format N*5
    :return: None
    """

    # element-wise plot each pixel in the image, use PIL interface
    img = integerize(img)
    x_max = max(img[:, 0])
    y_max = max(img[:, 1])
    res = Image.new("RGB", (2048, 2048))

    # hard-code the size of the image here
    for pixel in img:

        # notice the order of x and y
        # +512 to put the image to near "center" of the canvas
        pos = (pixel[0]+512, pixel[1]+512)
        rgb = (pixel[2], pixel[3], pixel[4])
        try:
            res.putpixel(pos, rgb)
        except:
            print("out of canvas: ", pos, rgb)
            # break
    # the image is mis-presented possibly due to the axis of image not correspond to cartesian
    res.show()


def bilinear_interpolation(img, x, y):
    """ interpolate an image with bilinear method
    :param img: image to be interpolated
    :param x: x coordinate
    :param y: y coordinate
    :return: interpolated value
    """
    x0 = np.floor(x).astype(int)
    x1 = x0 + 1
    y0 = np.floor(y).astype(int)
    y1 = y0 + 1

    x0 = np.clip(x0, 0, img.shape[1] - 1)
    x1 = np.clip(x1, 0, img.shape[1] - 1)
    y0 = np.clip(y0, 0, img.shape[0] - 1)
    y1 = np.clip(y1, 0, img.shape[0] - 1)

    Ia = img[y0, x0]
    Ib = img[y1, x0]
    Ic = img[y0, x1]
    Id = img[y1, x1]

    wa = (x1 - x) * (y1 - y)
    wb = (x1 - x) * (y - y0)
    wc = (x - x0) * (y1 - y)
    wd = (x - x0) * (y - y0)

    return wa * Ia + wb * Ib + wc * Ic + wd * Id


def integerize(img):
    """
    Converts an image to an integer image
    :param img: image to be converted
    :return: integer image
    """
    res = []
    for pixel in img:
        res.append(np.around(pixel))
    return np.array(res, dtype=np.int16)


show_img(toxyrgb(base))
# plt.imshow(toxy(toxyrgb(base), list(base.shape[0:2])))
base = toxyrgb(base)
# %%
print("===scaling===")
scale_mat = np.array([[1, 0], [0, 2]])
pos = np.matmul(base[:, :2], scale_mat)
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)


show_img(res)


# %% shear
print("===shearing===")


shear_mat = np.array([[1, 1.5], [0, 1]])
pos = np.matmul(base[:, :2], shear_mat)
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)


show_img(res)


# %% rotate
print("===rotating===")

deg = np.pi/4
rotate_mat = np.array(
    [[np.cos(deg), -np.sin(deg)], [np.sin(deg), np.cos(deg)]])
pos = np.matmul(base[:, :2], rotate_mat)
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)


show_img(res)


# %%
print("===try a random linear transformation===")
rand_mat = np.array([[-1, 0.5], [0.3, 1]])
pos = np.matmul(base[:, :2], rand_mat)
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)
# parallelism shall be preserved
show_img(res)

# %%
print("===try a symmetric matrix===")
symm_mat = np.array([[1, 2], [2, 1]])
pos = np.matmul(base[:, :2], symm_mat)
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)
show_img(res)
# eigenvalue decomposed form RSR^T (p.119


# %%
print("===check commutative of transformations (p.116)===")
# check commutativity
AB = shear_mat @ rotate_mat
BA = rotate_mat @ shear_mat
# in fact just need to check if A @ B == B @ A
pos = np.matmul(base[:, :2], AB)
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)
show_img(res)

pos = np.matmul(base[:, :2], BA)
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)
show_img(res)
# matrix multiplication is not commutative
# hence, the order of operations matters.

# %%
print("===Check AB and A^TB^T===")

pos = np.matmul(base[:, :2], rotate_mat)
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)
show_img(res)

pos = np.matmul(rotate_mat.T, base[:, :2].T).T
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)
show_img(res)
# they are the same (at least for rotation?

# %% projection
print("===projection===")
# see https://medium.com/@daniel.j.lenton/part-ii-projective-transformations-in-2d-2e99ac9c7e9f
proj_mat = np.array([[2, 0, 0.001], [0, 2, 0.001], [0.001, 0.9, 1]])
# convert the xy coordinate to a homogeneous coordinate
pos = np.hstack([base[:, :2], np.reshape(np.ones_like(base[:, 1]), (-1, 1))])
pos = np.matmul(pos, proj_mat)
# normalize the coordinates
pos = pos / pos[:, 2:]
pos = pos[:, :2]
rgb = base[:, 2:]
res = np.concatenate([pos, rgb], axis=1)
show_img(res)
# %%
