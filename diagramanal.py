s = input("Enter string ")
mp = {}
for i in range(len(s) -2 ):
    st = s[i:i+3]
    if st in mp:
        mp[ st ] += 1
    else:
        mp[st] = 1

mx_string = ""
mx_val = 0

for i in mp:
    if mx_val < mp[i]:
        mx_val = mp[i]
        mx_string = i

k = ord( mx_string[0] ) - ord( 'T' )

def decrypt(msg , k ):
    dMsg = ""
    for i in msg :
        x = ((( ord(i) -ord('A') ) - k ) % 26 ) + ord('A')
        dMsg += chr(x)
    return dMsg

print(mp)
print( decrypt( s , k ) )