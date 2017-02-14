

import tweepy

consumer_key = '8f7BNyJXuPiLURMShMkCHLeQR'
consumer_secret = '9JTIS56Qei1sEUwDOpPBdD8ObdkQJ7a2SQEmyKEdsIXftCdFA3'
access_token = 'xas2Ly4NlCXlw4m77dxtvY9qyDEE4cberURB18Xt'
access_token_secret = 'jGWmzCnlHg7Y0ILvKP3cJp2LM2H51wnVV3b15tTPVDhNX'
 
# OAuth process, using the keys and tokens
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)
 
# Creation of the actual interface, using authentication
api = tweepy.API(auth)
 
# Sample method, used to update a status
api.update_status('Hello Python Central!')
