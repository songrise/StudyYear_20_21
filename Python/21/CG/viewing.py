# -*- coding : utf-8 -*-
# @FileName  : viewing.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2021-09-06
# @Github    ：https://github.com/songrise
# @Descriptions: To test viewing projections (orthographic, perspective,)
# %%
import numpy as np
import open3d as o3d
import matplotlib.pyplot as plt
import numpy as np
from PIL import Image, ImageOps

# point clouds
pc = []
# generate a cube mesh
for x in range(0, 40):
    for y in range(0, 80):
        for z in range(0, 120):
            pc.append(np.array([x, y, z]))
pc = np.array(pc)
# pc format : Nx3

# utils


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


def show_img(img, canvas_size=None):
    """
    Displays an image
    :param img: image to be displayed, in format N*(xyrgb)
    :return: None
    """

    # element-wise plot each pixel in the image, use PIL interface
    if canvas_size is None:
        canvas_size = (2048, 2048)
    img = integerize(img)
    x_max = max(img[:, 0])
    y_max = max(img[:, 1])
    res = Image.new("RGB", canvas_size)

    # hard-code the size of the image here
    for pixel in img:

        # notice the order of x and y
        # +512 to put the image to near "center" of the canvas
        pos = (pixel[0]+100, pixel[1]+100)
        rgb = (pixel[2], pixel[3], pixel[4])
        try:
            res.putpixel(pos, rgb)
        except:
            print("out of canvas: ", pos, rgb)
            # break
    # the image is mis-presented possibly due to the axis of image not correspond to cartesian
    res.show()


def integerize(arr):
    """
    Converts a numpy array of float to a numpy array of integer
    :param img: image to be converted
    :return: integer image
    """
    res = []
    for pixel in arr:
        res.append(np.around(pixel))
    return np.array(res, dtype=np.int16)


# %% orthographic projection
# assume camera at (0,0,0), looking at -z direction
# simply drop the z coordinate, assuming homogeneous coordinates
def orthographic(pc):
    ortho_mat = np.array(
        [[1, 0, 0, 0], [0, 1, 0, 0], [0, 0, 0, 0], [0, 0, 0, 1]])
    # use homogeneous coordinates
    pos = np.hstack((pc, np.ones((pc.shape[0], 1))))
    # rotate the pcs along the z-axis
    deg = np.pi/4
    rot_mat = np.array([[np.cos(deg), -np.sin(deg), 0, 0],
                        [np.sin(deg), np.cos(deg), 0, 0], [0, 0, 1, 0], [0, 0, 0, 1]])
    pos = pos@ rot_mat

    pc_2 = pos @ ortho_mat
    # convert back to cartesian coordinates
    pc_2 = pc_2 / pc_2[:, 3:]
    pc_2 = pc_2[:, 0:2]
    # set (255,255,255) as vertex color
    color = np.array([[255, 255, 255]]*pc_2.shape[0])
    pc_2 = np.hstack((pc_2, color))
    # show the image
    show_img(pc_2, (200, 200))


orthographic(pc)

# %% perspective projection
# also assume camera at (0,0,0), looking at -z direction


# def projective(pc):
near = 0.1
far = 100
proj_2_ortho_mat = np.array([[near, 0, 0, 0], [0, near, 0, 0], [
    0, 0, near+far, -near*far], [0, 0, 1, 0]])
ortho_mat = np.array(
    [[1, 0, 0, 0], [0, 1, 0, 0], [0, 0, 0, 0], [0, 0, 0, 1]])
proj_mat = proj_2_ortho_mat@ortho_mat

# use homogeneous coordinates
pos = np.hstack((pc, np.ones((pc.shape[0], 1))))
# rotate the pcs along the z-axis
# deg = np.pi/4
# rot_mat = np.array([[np.cos(deg), -np.sin(deg), 0, 0],
#                     [np.sin(deg), np.cos(deg), 0, 0], [0, 0, 1, 0], [0, 0, 0, 1]])
# pos = pos @ rot_mat

pc_2 = pos @ proj_mat
# convert back to cartesian coordinates
pc_2 = pc_2 / pc_2[:, 3:]
pc_2 = pc_2[:, 0:2]
# set (255,255,255) as vertex color
color = np.array([[255, 255, 255]]*pc_2.shape[0])
pc_2 = np.hstack((pc_2, color))
# show the image
show_img(pc_2, (200, 200))


# projective(pc)

# %%
