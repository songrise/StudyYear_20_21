# -*- coding : utf-8 -*-
# @FileName  : tensor.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2020-09-15
# @Github    ：https://github.com/songrise
# @Descriptions: None


import torch

print(torch.ones((3, 3), dtype=bool))

x = torch.rand((3, 3), dtype=float)
y = torch.rand((3, 3), dtype=float)
x += y  # or x.add_(y)
print(x)
print(x[:, 2])

x = torch.randn(4, 4)
print(x.view(16))

# let us run this cell only if CUDA is available
# We will use ``torch.device`` objects to move tensors in and out of GPU
if torch.cuda.is_available():
    device = torch.device("cuda")          # a CUDA device object
    y = torch.ones_like(x, device=device)  # directly create a tensor on GPU
    # or just use strings ``.to("cuda")``
    x = x.to(device)
    z = x + y
    print(z)
    # ``.to`` can also change dtype together!
    print(z.to("cpu", torch.double))
