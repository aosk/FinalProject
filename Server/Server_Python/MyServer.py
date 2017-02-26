#!/usr/bin/env python
"""

Abdulrahman Omar
Simple Server

Send a POST request::
    curl -d "foo=bar&bin=baz" http://localhost
"""

from BaseHTTPServer import BaseHTTPRequestHandler,HTTPServer
import json
import requests  
from flask import Flask


import MySQLdb




app = Flask(__name__)

PORT_NUMBER = 8080
url = "http://localhost:8000"

''' Database setup and testing: '''

db = MySQLdb.connect(host="127.0.0.1",    # Host: localhost
                     user="root",         # root
                     passwd="",  # empty
                     db="letsunite2")        # letsunite2

# create a Cursor object. 
cur = db.cursor()

# Use all the SQL you like
cur.execute("SELECT email FROM login")

# print all the first cell of all the rows
for row in cur.fetchall():
    print row[0]

db.close()



#This class will handles any incoming request from
#the browser 
class myHandler(BaseHTTPRequestHandler):
    
    #Handler for the GET requests
    app.route("/", methods=['GET'])
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type','text/html')
        self.end_headers()
        # Send the html message
        self.wfile.write("It works here: GET")
        return


    #Handler for the POST requests
    @app.route('/', methods=["POST"])
    def login():

        if request.method == "POST":

            json_dict = request.get_json()

            email = request.values.get('email')
            password = request.args.get('password')


            data = {'email': email, 'password': password}
            return "====>",jsonify(data)
        else:

            return Response(response='error', status=400)


try:
    #Create a web server and define the handler to manage the
    #incoming request
    server = HTTPServer(('localhost', PORT_NUMBER), myHandler)
    print 'Started httpserver on port ' , PORT_NUMBER
    
    #Wait forever for incoming htto requests
    server.serve_forever()

except KeyboardInterrupt:
    print '\n"^C" received, shutting down the web server'
    server.socket.close()