'''
Abdul Server, testing the login with the DB adn
'''


#!/usr/bin/python
from BaseHTTPServer import BaseHTTPRequestHandler,HTTPServer
import json
import MySQLdb
from eventGenerator import EventGen
import tweepy
from datetime import datetime



# specify the port
PORT_NUMBER = 8000

# Set up DB connection
db = MySQLdb.connect(host="127.0.0.1",    # Host: localhost
                     user="root",         # root
                     passwd="",  # empty
                     db="letsunite2")        # letsunite2

cur = db.cursor()

# database_command=[]


#This class will handles any incoming request from
#the browser 
class myHandler(BaseHTTPRequestHandler):	
	
	#Handler for the POST requests
	def do_POST(self):

		'''
		=====================================================================
		                  ***** This is the Login  *****
		=====================================================================
		'''
		if self.path.endswith("/login/"):

			# Send the html message
			#self.wfile.write("'REQUEST_METHOD':'POST',")
			#self.wfile.write(self.headers)


			loginList = (self.rfile.read(int(self.headers['Content-Length'])))

			user_dict = json.loads(loginList)
			email_login = user_dict['email']
			password_login = user_dict['password']

			cur.execute("SELECT email, password FROM login")
			loginTable = list(cur)
			t = 0

			try:
				for i, j in loginTable:
					#print "i = ", i, "j=",j
					if i == email_login and j == password_login:
						t = t + 1
						print "\n\n ===> Welcome dude !! I know you <=== \n"
						print " ===> ",email_login, password_login, "\n\n"
						#return self.wfile.write("200");
						self.send_response(200)
				if t == 0:
						print "\n\nBaaad Get out !! I don't know who you are!! \n\n"
						self.send_response(400)
				self.send_header('Content-type','text/html')
				self.end_headers()

			except MySQLdb.IntegrityError:
				print"something gone fuck =-=-=-=-=-=-=-> ?? "
			return



		'''
		=====================================================================
		                  ***** This is the signup  *****
		=====================================================================
		'''
		if self.path.endswith("/signup/"):

			signupList = (self.rfile.read(int(self.headers['Content-Length'])))

			user_dict = json.loads(signupList)
			firstNameSignup = user_dict['firstname']
			lastnameSignup = user_dict['lastname']
			emailSignup = user_dict['email']
			passwordSignup = user_dict['password']

			print signupList

			try:
				cur.execute("INSERT INTO login "
	               "(f_name, l_name, email, password) "
	               "VALUES (%s, %s, %s, %s)", [firstNameSignup, lastnameSignup, emailSignup, passwordSignup])

				db.commit()
				self.send_response(200)

				print "\nfirst == >",firstNameSignup,"\nLast== >", lastnameSignup, "\nemail== >", emailSignup, "\npassword == >",passwordSignup
				
			except MySQLdb.IntegrityError:
				self.send_response(400)

			self.send_header('Content-type','text/html')
			self.end_headers()

			return


		'''
		=====================================================================
		              ***** This is the Location  *****
		=====================================================================
		'''
		if self.path.endswith("/location/"):

			locationList = (self.rfile.read(int(self.headers['Content-Length'])))

			print "\n\nlocation ==>", locationList

			location_dict = json.loads(locationList)
			locationLat = location_dict['lat']
			locationLong = location_dict['long']

			try:
				cur.execute("INSERT INTO location "
	               "(l_lat, l_long) "
	               "VALUES (%s, %s)", [locationLat, locationLong])

				db.commit()

				self.send_response(200)

			except MySQLdb.IntegrityError:


				print locationLat, locationLong

			self.send_header('Content-type','text/html')
			self.end_headers()
			#self.wfile.write("hello world!");
			return 

	'''
	=====================================================================
	              ***** Tweets *****
	=====================================================================
	'''

	def do_PUT(self):
		print("PUT called2")
		
		if self.path.endswith("/events/"):
			t = self.rfile.read(int(self.headers['Content-Length']))
			print("send response")
			if("token"!=t):
				self.send_response(400)
				self.send_header('Content-type','text/html')
				return 
			self.send_response(200);
			self.send_header('Content-type','text/html')
			self.end_headers()

			cur.execute("SELECT user_id, lat , longt FROM test_location")

			try:
				usersLocation = list(cur)
				index=0
				group=[]
				re=[]
				for i in usersLocation:
				        if(index>6):
				        	x = int(abs(i[1]*1000000))
				        	y = int(abs(i[2]*1000000))
				        	group.append((i[0],(x,y)))
				        index+=1
				my_thread=EventGen(1,group,args=[re])
				my_thread.start()
				#print("----")
				#print("----")
				my_thread.join()
				print(re)
				print("Exiting Main Thread")



			except MySQLdb.IntegrityError:
				print"something gone =-=-=-=-=-=-=-> ?? "
			return
			



		if self.path.endswith("/"):

			consumer_key = 'yL0iWUkIh8X2UddhU24sJas53'
			consumer_secret = 'vsojVKCKFIuyhyP9LcL1aCRBZHHoy0EtUeN6B9bOWaRJA5J5NL'
			access_token = '254286536-LRLyCSoU8JI0KBV9pVZwJTQJSfr5o0ZQWaplPFIP'
			access_token_secret = 'dLriHb9z9Fi7WKN1xRYwHfoechCcvxJ0isGZLe6oTr0o7'

			auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
			auth.set_access_token(access_token, access_token_secret)
			api = tweepy.API(auth)

			try:
				search_text = "#events"
				search_number = 900
				search_result = api.search(search_text, rpp = search_number)


				for i in search_result:
					tweet = " -=-=-=- Says -=-=-=-:" + i.text 
					print tweet
					return self.json.dumps(tweet)

				return json.dumps(text)
			except IntegrityError:
				return "Fucked up"

			self.send_header('Content-type','text/html')
			self.end_headers()

		return 200




'''
=====================================================================
                 *****  Run the stuff here  *****
=====================================================================
'''
try:
        #Create a web server and define the handler to manage the
        #incoming request
        print '\n\nServer is taking off ...'

        server = HTTPServer(('', PORT_NUMBER), myHandler)

        print '\n\n Server is running on port => (__' , PORT_NUMBER, '__) <=\n\n'

        #Wait forever for incoming htto requests
        server.serve_forever()

except KeyboardInterrupt:
	print '\n\n\n===> ^C <=== Detected, shutting down the server\n'
	print 'Bey !! Sya soon ;) \n\n\n'
	server.socket.close()


