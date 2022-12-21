import numpy as np

# a is the bigger number
def egcd(a,b):
    x,y = 0,1
    while(b != 0):
        q, r = a // b , a % b
        m = x - q*y
        a,b , x,y = b,r , y,m
    return a , x

def modinv(a, m):
   gcd, x = egcd(m, a)
   if gcd != 1:
       return None
   else:
       return x % m


def getArray(st):
    st = np.array([ ord(i) - ord('A') for i in st ])
    st = st.reshape( (-1,3) )
    return st

def getString(ar):
    ar = [ chr(int(i) + ord('A') ) for i in ar.reshape(-1) ]
    return ''.join(ar)

def encrypt(pt , k):
    pt = getArray(pt)
    k = getArray(k)

    et = np.dot(pt , k) % 26
    return getString(et)

def decrypt(et, k):
    et = getArray(et)
    k = getArray(k)
    
    keyInv = np.rint(np.linalg.inv(k) * np.linalg.det(k)).astype(int) % 26
    keyInv = (modinv(np.linalg.det(k) , 26) * keyInv ) % 26

    pt = np.dot(et , keyInv) % 26
    return getString(pt)

pt = "THEQUICKBROWN"
key = "GYBNQKURP"

pt = pt + 'Z' * (3 - len(pt) % 3)

et = encrypt(pt,key)
print(et)
pt = decrypt(et , key)
print(pt)
