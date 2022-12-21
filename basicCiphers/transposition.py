import numpy as np

pt = "THEQUICKBROWNFOX"
key = "JUMP"

def getArray(st):
    st = [ord(i) - ord('A') for i in st]
    return np.array(st)

def getString(ar):
    ar = [ chr(int(i) + ord('A') ) for i in ar.reshape(-1) ]
    return ''.join(ar)

def encrypt(pt , k):
    k = [k.index(i) for i in sorted(list(k))]
    n = len(k)

    pt = pt + 'Z' * (n - len(pt) % n)
    pt = getArray(pt)
    pt = pt.reshape((-1, n))

    et = ""
    for i in k:
        et += getString(pt[:,i])

    return et

def getInv(k):
    key = list(range(len(k)))
    for i in range(len(k)):
        key[k[i]] = i

    return key

def decrypt(et , k):
    k = [k.index(i) for i in sorted(list(k))]
    k = getInv(k)

    n = len(k)
    et = getArray(et)
    et = et.reshape((n, -1))
   
    pt = ""
    for i in range(et.shape[1]):
        pt += getString(et[:,i][np.array(k)])

    return pt

et = encrypt(pt,key)
print(et)
pt = decrypt(et , key)
print(pt)
