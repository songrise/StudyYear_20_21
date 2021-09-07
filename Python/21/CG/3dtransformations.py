# -*- coding : utf-8 -*-
# @FileName  : 3dtransformations.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2021-09-04
# @Github    ：https://github.com/songrise
# @Descriptions: to test 3d transformations, use point cloud as an example
# NB: vectors are row vec

# %%
import numpy as np
import open3d as o3d

pc = []
# generate a cube mesh
for x in range(0, 10):
    for y in range(0, 20):
        for z in range(0, 40):
            pc.append(np.array([x, y, z]))
pc = np.array(pc)
# pc format : Nx3


def show_pcs(pc1, pc2):

    pcd1 = o3d.geometry.PointCloud()
    pcd1.points = o3d.utility.Vector3dVector(pc1)
    pcd1.paint_uniform_color([1, 0, 0])  # ! Re: red
    # draw xyz axis
    # test_pcd.points = o3d.utility.Vector3dVector(points)  # 定义点云坐标位置
    # test_pcd.colors = o3d.Vector3dVector(colors)  # 定义点云的颜色

    pcd2 = o3d.geometry.PointCloud()
    pcd2.points = o3d.utility.Vector3dVector(pc2)
    pcd2.paint_uniform_color([0, 1, 0])  # ! Re: green

    o3d.visualization.draw_geometries([pcd1, pcd2])


# %% rotation along x-axis (p.124)
deg = np.pi/4
# use homogeneous coordinates
pos = np.hstack((pc, np.ones((pc.shape[0], 1))))

rotate_mat = np.array(
    [[1, 0, 0, 0], [0, np.cos(deg), -np.sin(deg), 0], [0, np.sin(deg), np.cos(deg), 0], [0, 0, 0, 1]])
pc_2 = pos @ rotate_mat
# convert back to cartesian coordinates
pc_2 = pc_2 / pc_2[:, 3:]
pc_2 = pc_2[:, 0:3]
show_pcs(pc, pc_2)

# %% arbitary rotation (p.125)
deg_x = np.pi/4
deg_y = np.pi/8
# use homogeneous coordinates
pos = np.hstack((pc, np.ones((pc.shape[0], 1))))

rotate_mat_x = np.array(
    [[1, 0, 0, 0], [0, np.cos(deg_x), -np.sin(deg_x), 0], [0, np.sin(deg_x), np.cos(deg_x), 0], [0, 0, 0, 1]])

rotate_mat_y = np.array([[np.cos(deg_y), 0, -np.sin(deg_y), 0],
                         [0, 1, 0, 0], [np.sin(deg_y), 0, np.cos(deg_y), 0], [0, 0, 0, 1]])

# notice the order of rotation matrices, x first then y,
# because we are using row vec, so rotate_mat left mul pos
rotate_mat = rotate_mat_x @ rotate_mat_y

pc_2 = pos @ rotate_mat
# convert back to cartesian coordinates
pc_2 = pc_2 / pc_2[:, 3:]
pc_2 = pc_2[:, 0:3]
show_pcs(pc, pc_2)

# %%
