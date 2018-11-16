### 2018-2 SNU Advanced Programming Methodology
### HW 2 Coding problem
### Min heap implementation
_parent = lambda i: (i-1)//2
_l_child = lambda i: 2*i + 1
_r_child = lambda i: 2*i + 2


class MinHeap:
    """Implementation of min-heap.
    Internally maintains positions of objects to support constant time random access."""
    def __init__(self):
        self.len = 0
        self.a = []
        self.pos = {}
    
    def __len__(self):
        return self.len
    
    def __contains__(self, item):
        return item in self.pos
    
    def __getitem__(self, key):
        return self.a[self.pos[key]][1]
    
    def __setitem__(self, key, value):
        try:
            self.update(key, value)
        except KeyError:
            self.insert(key, value)
        
    def insert(self, vtx, value):
        self.a.append((vtx, value))
        self.pos[vtx] = self.len
        self._sift_up(self.len)
        self.len += 1

    def update(self, vtx, value):
        idx = self.pos[vtx]
        old_vtx, old_value = self.a[idx]
        self.a[idx] = (old_vtx, value)
        
        if value <= old_value:
            self._sift_up(idx)
        else:
            self._sift_down(idx)
    
    def extract_min(self):
        self.len -= 1
        self._exchange(0, self.len)
        
        self._sift_down(0)
        
        return self.a[self.len]
    
    def _sift_up(self, i):
        if i > 0:
            p = _parent(i)
            if self.a[i][1] < self.a[p][1]:
                self._exchange(i,p)
                self._sift_up(p)
    
    def _sift_down(self, i):
        if i <= _parent(self.len - 1):
            l = self.a[_l_child(i)][1]
            r = self.a[_r_child(i)][1] if _r_child(i) < self.len else float('inf')
            min_idx = i
            if l < self.a[min_idx][1]:
                min_idx = _l_child(i)
            if r < self.a[min_idx][1]:
                min_idx = _r_child(i)
            if min_idx != i:
                self._exchange(i,min_idx)
                return self._sift_down(min_idx)
        return i
    
    def _exchange(self, i, j):
        self.pos[self.a[i][0]] = j
        self.pos[self.a[j][0]] = i
        
        self.a[i], self.a[j] = self.a[j], self.a[i]
