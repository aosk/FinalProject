'''
Abdul Server, testing the login with the DB adn
'''


#!/usr/bin/python
from BaseHTTPServer import BaseHTTPRequestHandler,HTTPServer
import json
import MySQLdb


# specify the port
PORT_NUMBER = 8000

# Set up DB connection
db = MySQLdb.connect(host="127.0.0.1",    # Host: localhost
                     user="root",         # root
                     passwd="",  # empty
                     db="letsunite2")        # letsunite2

cur = db.cursor()



#This class will handles any incoming request from
#the browser 
class myHandler(BaseHTTPRequestHandler):
	
	#Handler for the GET requests
	def do_POST(self):
                
		self.send_response(200)
		self.send_header('Content-type','text/html')
		self.end_headers()
		# Send the html message
		self.wfile.write("'REQUEST_METHOD':'POST',")
		self.wfile.write(self.headers)


		newList = (self.rfile.read(int(self.headers['Content-Length'])))

		user_dict = json.loads(newList)
		email_login = user_dict['email']
		password_login = user_dict['password']


		print email_login, password_login, "\n\n\n\n"
		#testing DB




		cur.execute("SELECT email, password FROM login")
		loginTable = list(cur)

		for i, j in loginTable:
			#print "i = ", i, "j=",j
			if i == email_login and j == password_login:
				print "I know you "
				return True
		return False

try:
        #Create a web server and define the handler to manage the
        #incoming request
        server = HTTPServer(('', PORT_NUMBER), myHandler)
        print 'Started httpserver on port ' , PORT_NUMBER

        #Wait forever for incoming htto requests
        server.serve_forever()

except KeyboardInterrupt:
	print '^C received, shutting down the web server'
	server.socket.close()