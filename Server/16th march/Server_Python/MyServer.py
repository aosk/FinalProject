from flask import Flask, Response, render_template, request
import json
import tweepy

#! /usr/local/bin/python  -*- coding: UTF-8 -*-

app = Flask(__name__)


@app.route("/")
def index():


    consumer_key = 'yL0iWUkIh8X2UddhU24sJas53'
    consumer_secret = 'vsojVKCKFIuyhyP9LcL1aCRBZHHoy0EtUeN6B9bOWaRJA5J5NL'
    access_token = '254286536-LRLyCSoU8JI0KBV9pVZwJTQJSfr5o0ZQWaplPFIP'
    access_token_secret = 'dLriHb9z9Fi7WKN1xRYwHfoechCcvxJ0isGZLe6oTr0o7'

    auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    api = tweepy.API(auth)


    search_text = "#events"
    search_number = 900
    search_result = api.search(search_text, rpp = search_number)


    for i in search_result:
        tweet = "this tweet Says:" + i.text 
        #print search_result
        print tweet
        return json.dumps(tweet)

    return json.dumps(text)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8080,  debug=True)




