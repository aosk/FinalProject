from flask import Flask, Response, render_template, request
import json
from subprocess import Popen, PIPE
import os
from tempfile import mkdtemp
from werkzeug import secure_filename
import tweepy
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener


ckey = "8f7BNyJXuPiLURMShMkCHLeQR"
csecret = "9JTIS56Qei1sEUwDOpPBdD8ObdkQJ7a2SQEmyKEdsIXftCdFA3"

atoken = "254286536-xas2Ly4NlCXlw4m77dxtvY9qyDEE4cberURB18Xt"
asecret = " jGWmzCnlHg7Y0ILvKP3cJp2LM2H51wnVV3b15tTPVDhNX"


class listener(StreamListener):
    def on_data(self, data):
        try:
            if data is not None:
                tweet = data.split(',"text":"')[1].split('","source')[0]

                print "Hi "+tweet
                print "\n"
                # userName = data.split(',"screen_name":"')[1].split('","location')[0]
                # index()
                
        except BaseException as e:
            print ('failed on_data', e)
        return

    def on_error(self, status):

        print status 


auth = tweepy.OAuthHandler(ckey, csecret)
auth.set_access_token(atoken, asecret)
api = tweepy.API(auth)

twitterStream = Stream(auth, listener())
twitterStream.filter(track=["Dublin"])

