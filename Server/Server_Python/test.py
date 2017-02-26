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
	
	#Handler for the POST requests
	def do_POST(self):

		if self.path.endswith("/login/"):

                
			self.send_header('Content-type','text/html')
			self.end_headers()
			# Send the html message
			self.wfile.write("'REQUEST_METHOD':'POST',")
			self.wfile.write(self.headers)


			loginList = (self.rfile.read(int(self.headers['Content-Length'])))

			user_dict = json.loads(loginList)
			email_login = user_dict['email']
			password_login = user_dict['password']


			cur.execute("SELECT email, password FROM login")
			loginTable = list(cur)
			t = 0

			for i, j in loginTable:
				#print "i = ", i, "j=",j
				if i == email_login and j == password_login:
					t = t + 1
					print "\n\n ===> Welcome dude !! I know you <=== \n"
					print " ===> ",email_login, password_login, "\n\n"

					return self.send_response(200)
			if t == 0:
					print "\n\nBaaad Get out !! I don't know who you are!! \n\n"
					
		
		if self.path.endswith("/signup/"):
			self.send_header('Content-type','text/html')
			self.end_headers()
			# Send the html message
			self.wfile.write("'REQUEST_METHOD':'POST',")
			self.wfile.write(self.headers)


			signupList = (self.rfile.read(int(self.headers['Content-Length'])))

			user_dict = json.loads(signupList)
			firstNameSignup = user_dict['firstname']
			lastnameSignup = user_dict['lastname']
			emailSignup = user_dict['email']
			passwordSignup = user_dict['password']

			print signupList

			cur.execute("INSERT INTO login "
               "(f_name, l_name, email, password) "
               "VALUES (%s, %s, %s, %s)", [firstNameSignup, lastnameSignup, emailSignup, passwordSignup])

			db.commit()

			print "\nfirst == >",firstNameSignup,"\nLast== >", lastnameSignup, "\nemail== >", emailSignup, "\npassword == >",passwordSignup


		if self.path.endswith("/location/"):
			self.send_header('Content-type','text/html')
			self.end_headers()
			self.wfile.write("'REQUEST_METHOD':'POST',")
			self.wfile.write(self.headers)

			locationList = (self.rfile.read(int(self.headers['Content-Length'])))

			print "\n\nlocation ==>", locationList

			location_dict = json.loads(locationList)
			locationLat = location_dict['lat']
			locationLong = location_dict['long']

			cur.execute("INSERT INTO location "
               "(l_lat, l_long) "
               "VALUES (%s, %s)", [locationLat, locationLong])

			db.commit()

			print locationLat, locationLong



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