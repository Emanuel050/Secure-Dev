import base64
from Crypto.Cipher import AES

SP_VERSION = "3.5"
ENCRYPTION_KEY = "9gK7e8Tax3rEm1Iv"


def decrypt(password):
    password = password.strip('"')
    VALID_PASSWORD_PREFIX = "##"
    if password == '' or not password.startswith(VALID_PASSWORD_PREFIX):
        return password
    password = password[2:]  # remove prefix

    BS = 16  # block size
    # pad = lambda s: s + (BS - len(s) % BS) * chr(BS - len(s) % BS)
    unpad = lambda s: s[:-ord(s[len(s) - 1:])]

    try:
        decoded_password = base64.b64decode(password)
        decrypted_password = unpad(AES.new(key=ENCRYPTION_KEY).decrypt(decoded_password))
    except:
        print "Error decoding password!"
        return password

    return decrypted_password
