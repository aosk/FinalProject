class Event():

	def __init__(self,event_name,lan,longt):
		self.__event_name = event_name
		self.__lan = lan
		self.__long = longt
		return
	def getName(self):

		return 

	def getLan(self):
		return
	def getLong(self):
		return
	def setName(self,name):
		self.__event_name = name

class EventCaller(Thread):
	
	def __init__(self,group_id,group):

		self.__group_id = group_id
		#item = name:(lan,long)
		self.__group = group
		self.__MAX_X_DISTANCE = 0.000005
		self.__MAX_Y_DISTANCE = 0.000005
		#totoal will be 5*sqrt2

	def __sortManager(self):
		x_list=[]
		#create list of x_axises
		for i in self.__group:
			x_list.append(i[1][0])
		#sort the group based on the list of x_axises
		radixsort(x_list,self.__group)
		j=1
		result_list=[]
		for i in range(len(self.__group)):
			small_list=[]
			small_list.append(self.__group[i])
			# --------- !! 
			if(j==len(self.__group)):
				break
			while(abs(self.__group[i][1][0]-self.__group[j][1][0])<self.__MAX_X_DISTANCE):
				small_list.append(self.__group[j])
				if(j==(len(self.__group)-1)):
					break
				j+=1

			result_list.append(small_list)
			i=j+1
			j+=1

		final_result = result_list.copy()
		#Creating Smaller List
		for i in range(len(result_list)):
			y_list=[]
			for j in range(len(i)):
				y_list.append(result_list[i][j][1][1])
			radixsort(y_list,final_result[i])

		# this is the final result list
		result_list=[]
		j2=1
		for i in range(len(final_result)):
			for j in range(len(i)):
				small_list=[]
				small_list.append(final_result[i][j])
				# for every sorted small list, we get the items whose y axis difference is smaller than MAX
				while(abs(final_result[i][j][1][1]-final_result[i][j2][1][1])<self.__MAX_Y_DISTANCE):
					#
					small_list.append(final_result[i][j2])
					j2+=1
					j+=1
				result_list.append(small_list)
				j=j2
				j2+=1

		for i in result_list:
			if(len(i)>2):
				#create event
				print("Event Creation")
			print(i)
	#radix sort the group member's distance
	def radixsort( aList,bList ):
		RADIX = 10
        maxLength = False
        tmp , placement = -1, 1
        while not maxLength:
            maxLength = True
            # declare and initialize buckets
            buckets = [list() for _ in range( RADIX )]
            buckets2= [list() for _ in range( RADIX )]
            bi=0
            # split aList between lists
            for  i in aList:
                tmp = i / placement
                buckets[int(tmp % RADIX)].append( i )
                buckets2[int(tmp % RADIX)].append(bList[bi])
                bi+=1
                if maxLength and tmp > 0:
                    maxLength = False

            # empty lists into aList array
            a = 0
            for b in range( RADIX ):
                buck = buckets[b]
                bi=0
                for i in buck:
                        aList[a] = i
                        bList[a] = buckets2[b][bi]
                        a += 1
                        bi+=1

            # move to next digit
            placement *= RADIX
            return

		#confrim event with all users within 30m, 
	def __calc(self):
		#do the calculation of the list, and extract those within range
		event_group=[]

		#create Event();
		return

	#send each group member the event
	def __report(self,event):

		return

# calculate the distance between
#test_group = [("D",(53.336426,-6.257412)),("C",(53.336438,-6.257239)),("E",(53.336361,-6.257539)),("B",(53.339870,-6.260456)),("F",(53.339819,-6.260142)),("G",(53.339798,-6.260002)), ("A",(53.337657,-6.262672))]  

test_group = [("D",(10,11)),("C",(3,4)),("E",(12,13)),("B",(2,45)),("F",(100,101)),("G",(500,501)), ("A",(1,50))]  
#list_x_axis=[10,3,12,2,100,500,1]
#sort(test_group)
#test_group = ["A":(1,50),"B":(2,45),"C":(3,4),"D":(10,11),"E":(12,13),"F":(100:101),"G":(500,501)] 
#take difference
#small_group[["A":(1,50),"B":(2,45),"C":(3,4)],["D":(10,11),"E":(12,13)],["F":(100:101)],["G":(500,501)]
#lists_y_axis=[[50,45,4],[11,13],[101],[501]]
#smaller_group[{"C":(3,4)},{"A":(1,50),"B":(2,45)},{"D":(10,11),"E":(12,13)},{"F":(100:101)},{"G":(500,501)}]
#for i in smaller_group:
#Generate event.....



