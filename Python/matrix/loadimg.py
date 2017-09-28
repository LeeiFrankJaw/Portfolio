from geometry_lab import *
from image_mat_util import *

location, color = img = file2mat('mushroom2.png')

def transform(m, label='location'):
    if label=='location':
        return (m*location, color)
    elif label=='color':
        return (location, m*color)
    else:
        return (m*location, m*color)
