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

# def orthographic(pc):
#     ortho_mat = np.array(
#         [[1, 0, 0, 0], [0, 1, 0, 0], [0, 0, 0, 0], [0, 0, 0, 1]])
#     # use homogeneous coordinates
#     pos = np.hstack((pc, np.ones((pc.shape[0], 1))))
#     # rotate the pcs along the z-axis
#     deg = np.pi/4
#     rot_mat = np.array([[np.cos(deg), -np.sin(deg), 0, 0],
#                         [np.sin(deg), np.cos(deg), 0, 0], [0, 0, 1, 0], [0, 0, 0, 1]])
#     pos = pos@ rot_mat

#     pc_2 = pos @ ortho_mat
#     # convert back to cartesian coordinates
#     pc_2 = pc_2 / pc_2[:, 3:]
#     pc_2 = pc_2[:, 0:2]
#     # set (255,255,255) as vertex color
#     color = np.array([[255, 255, 255]]*pc_2.shape[0])
#     pc_2 = np.hstack((pc_2, color))
#     # show the image
#     show_img(pc_2, (200, 200))

def view_port(n_x, n_y):
    """
    return the view port
    :param n_x: number of pixels in x direction
    :param n_y: number of pixels in y direction
    :return: view port matrix
    """
    return np.array([[n_x/2, 0, 0, (n_x-1)/2],
                     [0, n_y/2, 0, (n_y-1)/2],
                     [0, 0, 1, 0],
                     [0, 0, 0, 1]])


def orthographic(view_param: list = None):
    """
    transform any view volume into canonical view volume
    return: 4x4 orthographic matrix
    """
    if view_param is None:
        view_param = [1, -1, -1, 1, 1, -1]
    l, r, b, t, n, f = view_param
    # formula 7.3
    ortho_mat = np.array([
        [2/(r-l), 0, 0, -(r+l)/(r-l)],
        [0, 2/(t-b), 0, -(t+b)/(t-b)],
        [0, 0, 2/(n-f), -(n+f)/(n-f)],
        [0, 0, 0, 1]])
    return ortho_mat


def look_at(eye, at, up):
    """
    look at matrix, see section 7.1.3
    :param eye: the position of the camera
    :param at: the position of the target
    :param up: the up direction
    :return: look at matrix
    """
    f = np.array(eye) - np.array(at)
    l = np.cross(f, up)/np.linalg.norm(np.cross(f, up))
    u = np.cross(f, l)/np.linalg.norm(np.cross(f, l))

    # rotate to align the axis
    rotate_matrix = np.array(
        [[l[0], l[1], l[2], 0], [u[0], u[1], u[2], 0],
            [f[0], f[1], f[2], 0], [0, 0, 0, 1.0]]
    )
    # translate to origin
    translate_matrix = np.array(
        [[1, 0, 0, -eye[0]], [0, 1, 0, -eye[1]],
            [0, 0, 1, -eye[2]], [0, 0, 0, 1.0]]
    )

    # notice the order
    return rotate_matrix @ translate_matrix


def perspective(n, f):
    """
    perspective projection matrix, see section 7.3
    :param n: near plane
    :param f: far plane
    :return: perspective-to-orthographic projection matrix
    """
    return np.linalg.inv(np.array([[f, 0, 0, 0],
                                   [0, f, 0, 0],
                                   [0, 0, 0, f*n],
                                   [0, 0, -1, n+f]]))


def mvp(model_param, view_param, projection_param):
    """
    return the matrix of model-view-projection
    :param model_param: model parameters
    :param view_param: the view parameters
    :param projection_param: the projection parameters
    :return: the matrix of view-projection
    """
    # temporarily hardcode the parameters
    # view-port matrix
    view_mat = view_port(1024, 768)
    # orthographic projection
    ortho_mat = orthographic(None)
    # perspective-to-orthographic projection
    perspective_mat = perspective(0.1, 100)
    # look at matrix
    look_at_mat = look_at(
        [0, 0, 0], [0, 0, -1], [0, 1, 0])
    # combine them, see p.153
    return view_mat @ ortho_mat @ perspective_mat @ look_at_mat


def load_vert():
    # return a cube for testing
    # vertices shall be col vectors
    vert = np.array([[0, 0, 0], [1, 0, 0], [1, 1, 0], [0, 1, 0], [
                    0, 0, 1], [1, 0, 1], [1, 1, 1], [0, 1, 1]])

    return vert


def draw_line(canvas, a, b):
    """
    draw a line from a to b, use PIL library
    :param canvas: the image
    :param a: start point
    :param b: end point
    :return: None
    """


def render():
    # temporarily hard code the parameters
    mvp_mat = mvp(None, None, None)
    verts = load_vert()
    # convert to homogeneous coordinates
    verts = np.hstack((verts, np.ones((verts.shape[0], 1))))
    # convert into screen coordinates
    screen_verts = np.matmul(mvp_mat, verts.T)
    # convert into image coordinates
    screen_verts = screen_verts[:2, :]
    # draw verts in image
    res = Image.new("RGB", (1024, 768))
    for i in range(screen_verts.shape[1]):
        try:
            res.putpixel((int(screen_verts[0, i]), int(
                screen_verts[1, i])), (255, 0, 0))
        except:
            pass
    res.show()
    return screen_verts.T


render()

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
