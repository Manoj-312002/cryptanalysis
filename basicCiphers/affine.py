# b is the bigger number
def egcd(a,b):
    x,y,u,v = 0,1,1,0
    while(a != 0):
        q, r = b // a , b % a
        m, n = x-u*q , y-v*q
        b,a , x,y ,u,v = a,r , u,v , m,n
    gcd = b
    return gcd, x , y

def modinv(a, m):
   gcd, x, y = egcd(a, m)
   if gcd != 1:
       return None
   else:
       return x % m

key = [7,10]

def encrypt(pt,k):
    et = ""
    for i in range(len(pt)):
        char = pt[i]
        et += chr( ( (ord(char) - ord('A'))*k[0] + k[1] ) % 26  + ord('A') ) 
    return et

def decrypt(et,k):
    pt = ""
    kinv = modinv(k[0] , 26)

    for i in range(len(et)):
        char = et[i]
        pt += chr( ( (ord(char) - ord('A')- k[1])*kinv ) % 26 + ord('A') )
    return pt
    
et = encrypt("MANOJ" , key)
print(et)
pt = decrypt(et , key)
print(pt)