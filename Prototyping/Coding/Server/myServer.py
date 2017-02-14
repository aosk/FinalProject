from flask import Flask, Response, render_template, request
import json





#! /usr/local/bin/python  -*- coding: UTF-8 -*-


app = Flask(__name__)

@app.route("/", methods=['GET'])
def index():

    return Response(response='GET', status=400)

@app.route("/", methods=['POST'])
def postData():
    if request.method == 'POST':
        print request.POST.get('email')
        print request.POST.get('password')
        
        return Response(response='DONE', status=200)

    return Response(response='error', status=400)



if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8080,  debug=True)




