def encrypt(pt,k):
    et = ""
    for i in range(len(pt)):
        char = pt[i]
        et += chr( (ord(char) - ord('A') + k ) % 26 + ord('A') )
    return et

def decrypt(et,k):
    pt = ""
    for i in range(len(et)):
        char = et[i]
        pt += chr( (ord(char) - ord('A') - k ) % 26 + ord('A') )
    return pt


et = encrypt("MANOJ" , 3)
print(et)
pt = decrypt(et , 3)
print(pt)

# THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG
# WKHTXLFNEURZQIRAMXPSVRYHUWKHODCBGRJ